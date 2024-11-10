package com.pakbras.dicodingevents.di

import android.content.Context
import com.pakbras.dicodingevents.data.local.room.EventDatabase
import com.pakbras.dicodingevents.data.remote.EventRepository
import com.pakbras.dicodingevents.data.remote.retrofit.ApiConfig
import com.pakbras.dicodingevents.helper.AppExecutors

object Injection {
    fun provideRepository(context: Context) : EventRepository {
        val apiservice = ApiConfig.getApiService()
        val db = EventDatabase.getDatabase(context)
        val dao = db.eventDao()
        val executor = AppExecutors()
        return EventRepository.getInstance(apiservice, dao, executor)
    }
}