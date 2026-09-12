package com.pachkhede.assignment4

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StringMethod::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun methodDao() : MethodDao

}