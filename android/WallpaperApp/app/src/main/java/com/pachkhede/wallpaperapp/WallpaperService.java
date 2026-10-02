package com.pachkhede.wallpaperapp;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import java.io.File;

public class WallpaperService extends Service {
    private SharedPreferences preferences;



    private Handler handler = new Handler(Looper.getMainLooper());

    private Runnable wallpaperRunnable = new Runnable() {
        @Override
        public void run() {
            if (isHomeWallpaperEnabled()) {
                changeWallpaper(WallpaperManager.FLAG_SYSTEM);
            }
            handler.postDelayed(this, 1000 * getHomeWallpaperInterval());
        }
    };


    private final BroadcastReceiver receiver = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {

            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {

                if (isLockWallpaperEnabled()) {
                    changeWallpaper(WallpaperManager.FLAG_LOCK);
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences(
                "wallpaper_settings",
                MODE_PRIVATE
        );


        startForegroundServiceNotification();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            registerReceiver(
                    receiver,
                    filter,
                    Context.RECEIVER_EXPORTED
            );
        } else {
            registerReceiver(receiver, filter);
        }

        handler.post(wallpaperRunnable);

    }

    private void startForegroundServiceNotification() {

        String channelId = "wallpaper_changer_channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Wallpaper Service",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager =
                    getSystemService(NotificationManager.class);

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        Notification notification =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("Wallpaper Changer Active")
                        .setContentText("Changing wallpapers automatically")
                        .setSmallIcon(android.R.drawable.ic_menu_gallery)
                        .setOngoing(true)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .build();

        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(wallpaperRunnable);
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void changeWallpaper(int flag) {

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "wallpaper_database"
        ).build();

        WallpaperDao dao = db.wallpaperDao();

        new Thread(() -> {

            WallpaperEntity wallpaper =
                    dao.getRandomWallpaper();

            if (wallpaper == null) {
                return;
            }

            File file =
                    new File(wallpaper.getLocal_path());

            if (!file.exists()) {
                return;
            }

            try {

                Bitmap bitmap =
                        BitmapFactory.decodeFile(
                                file.getAbsolutePath()
                        );

                WallpaperManager manager =
                        WallpaperManager.getInstance(
                                getApplicationContext()
                        );

                manager.setBitmap(
                        bitmap,
                        null,
                        true,
                        flag
                );

                bitmap.recycle();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    private boolean isHomeWallpaperEnabled() {

        return preferences.getBoolean(
                "home_wallpaper",
                false
        );
    }

    private boolean isLockWallpaperEnabled() {

        return preferences.getBoolean(
                "lock_wallpaper",
                false
        );
    }

    private int getHomeWallpaperInterval()
    {
        return preferences.getInt(
            "interval",
            60
    );

    }




}
