package com.assignment.notely.di.components

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.di.modules.AppModule
import com.assignment.notely.di.modules.DBModule
import com.assignment.notely.ui.detail.DetailViewModel
import com.assignment.notely.ui.newnote.NewNoteViewModel
import com.assignment.notely.ui.viewnote.AdapterViewModel
import com.assignment.notely.ui.viewnote.NoteViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vivek on 21/01/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DBModule::class))
interface AppComponent {

    fun app(): Application
    fun context(): Context
    fun preference(): SharedPreferences
    fun database(): NoteDatabase

    fun inject(viewModel: NewNoteViewModel)
    fun inject(viewModel: NoteViewModel)
    fun inject(viewModel: AdapterViewModel)
    fun inject(viewmodel : DetailViewModel)

}