package com.pakbras.dicodingevents.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pakbras.dicodingevents.data.remote.EventRepository
import com.pakbras.dicodingevents.di.Injection
import com.pakbras.dicodingevents.ui.SettingPreferences
import com.pakbras.dicodingevents.ui.dataStore

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val preference : SettingPreferences,
    private val eventRepository: EventRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(preference, eventRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class data : ${modelClass.name} please create new ViewModel at factory")
        }
    }

    companion object {
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                val repository = Injection.provideRepository(context)
                val preference = SettingPreferences.getInstance(context.dataStore)
                instance ?:ViewModelFactory(preference, repository)
            }.also { instance =  it }
    }
}