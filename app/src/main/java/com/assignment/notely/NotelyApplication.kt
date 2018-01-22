package com.assignment.notely

import android.app.Application
import com.assignment.notely.di.components.AppComponent
import com.assignment.notely.di.components.DaggerAppComponent
import com.assignment.notely.di.modules.AppModule
import com.assignment.notely.di.modules.DBModule

/**
 * Created by vivek on 21/01/18.
 */
class NotelyApplication : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).dBModule(DBModule()).build() }

    override fun onCreate() {
        super.onCreate()
    }
}