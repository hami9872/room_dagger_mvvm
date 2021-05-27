package com.post.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.post.database.dao.PostDao
import com.post.models.PostModel

@Database(entities = [PostModel::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){
    abstract fun getDao(): PostDao?
}