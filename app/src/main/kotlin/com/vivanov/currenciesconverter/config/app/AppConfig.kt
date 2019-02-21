package com.vivanov.currenciesconverter.config.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.vivanov.currenciesconverter.extensions.IMMLeaks
import timber.log.Timber

class AppConfig(
    private val application: Application
) : IAppConfig {

    override fun initAppConfig() {
        setupActivityLifeCycleLogs()
        IMMLeaks.fixFocusedViewLeak(application)
    }

    private fun setupActivityLifeCycleLogs() = application.registerActivityLifecycleCallbacks(
        object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Timber.d("${activity.localClassName} : onActivityCreated()")
            }

            override fun onActivityStarted(activity: Activity) {
                Timber.d("${activity.localClassName} : onActivityStarted()")
            }

            override fun onActivityResumed(activity: Activity) {
                Timber.d("${activity.localClassName} : onActivityResumed()")
            }

            override fun onActivityPaused(activity: Activity) {
                Timber.d("${activity.localClassName} : onActivityPaused()")
            }

            override fun onActivityStopped(activity: Activity) {
                Timber.d("${activity.localClassName} : onActivityStopped()")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                Timber.d("${activity.localClassName} : onActivitySaveInstanceState()")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Timber.d("${activity.localClassName} : onActivityDestroyed()")
            }
        })
}