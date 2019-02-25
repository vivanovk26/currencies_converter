package com.vivanov.currenciesconverter.config.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.vivanov.currenciesconverter.BuildConfig
import timber.log.Timber

class AppConfig(
    private val application: Application
) : IAppConfig {

    override fun initAppConfig() {
        setupTimber()
        setupActivityLifeCycleLogs()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.uprootAll()
        }
    }

    private fun setupActivityLifeCycleLogs() = application.registerActivityLifecycleCallbacks(
        object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Timber.d("${activity.javaClass.simpleName} : onActivityCreated()")
            }

            override fun onActivityStarted(activity: Activity) {
                Timber.d("${activity.javaClass.simpleName} : onActivityStarted()")
            }

            override fun onActivityResumed(activity: Activity) {
                Timber.d("${activity.javaClass.simpleName} : onActivityResumed()")
            }

            override fun onActivityPaused(activity: Activity) {
                Timber.d("${activity.javaClass.simpleName} : onActivityPaused()")
            }

            override fun onActivityStopped(activity: Activity) {
                Timber.d("${activity.javaClass.simpleName} : onActivityStopped()")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                Timber.d("${activity.javaClass.simpleName} : onActivitySaveInstanceState()")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Timber.d("${activity.javaClass.simpleName} : onActivityDestroyed()")
            }
        })
}