package com.post.di

import com.post.di.module.PostRepositoryModule
import com.post.di.module.RoomDatabaseModule
import com.post.views.activities.AddPostActivity
import com.post.views.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomDatabaseModule::class , PostRepositoryModule::class])
interface AppComponent {
    fun injectFields(activity: MainActivity)
    fun injectAddFields(activity: AddPostActivity)
}