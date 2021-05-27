package com.post.di.module

import com.post.database.MyDatabase
import com.post.repository.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PostRepositoryModule{

    @Singleton
    @Provides
    fun providesPostRepository(myDatabase: MyDatabase): PostRepository{
        return PostRepository(myDatabase.getDao()!!)
    }
}