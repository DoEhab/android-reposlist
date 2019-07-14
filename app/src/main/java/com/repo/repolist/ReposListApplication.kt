package com.repo.repolist

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import java.util.*

/**
 * Created by Doha on 09/07/19.
 */
class ReposListApplication : Application() {

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)
    }

    fun getComponent(): AppComponent = component
}