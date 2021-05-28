package com.animal.di

import com.animal.di.module.AnimalRepositoryModule
import com.animal.di.module.RoomDatabaseModule
import com.animal.views.activities.AddOrUpdateAnimaleActivity
import com.animal.views.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomDatabaseModule::class, AnimalRepositoryModule::class])
interface AppComponent {
    fun injectFields(activity: MainActivity)
    fun injectAddFields(activity: AddOrUpdateAnimaleActivity)
}