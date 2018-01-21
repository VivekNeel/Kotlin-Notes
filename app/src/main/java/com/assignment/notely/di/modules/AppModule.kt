package com.assignment.notely.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vivek on 21/01/18.
 */
@Module
class AppModule(var app: Application) {

    @Provides
    @Singleton
    fun provideApp(): Application = app

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPref(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
}