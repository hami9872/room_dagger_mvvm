package com.animal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.animal.database.dao.AnimalDao
import com.animal.models.AnimalModel

@Database(entities = [AnimalModel::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){
    abstract fun getDao(): AnimalDao?
}