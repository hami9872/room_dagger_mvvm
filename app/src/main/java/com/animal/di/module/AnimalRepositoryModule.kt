package com.animal.di.module

import com.animal.database.MyDatabase
import com.animal.repository.AnimalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AnimalRepositoryModule{

    @Singleton
    @Provides
    fun providesPostRepository(myDatabase: MyDatabase): AnimalRepository{
        return AnimalRepository(myDatabase.getDao()!!)
    }
}