package com.pachkhede.wallpaperapp;

public class Wallpaper {

    private String id;
    private String url;
    private String path;
    private Thumbs thumbs;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Thumbs getThumbs() {
        return thumbs;
    }

    public static class Thumbs {

        private String large;
        private String original;
        private String small;

        public String getLarge() {
            return large;
        }

        public String getOriginal() {
            return original;
        }
        public String getSmall() {
            return small;
        }
    }
}