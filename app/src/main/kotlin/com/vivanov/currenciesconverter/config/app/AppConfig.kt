package com.vivanov.currenciesconverter.config.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.vivanov.currenciesconverter.extensions.IMMLeaks
import okhttp3.OkHttpClient
import timber.log.Timber

class AppConfig(
    private val application: Application,
    private val okHttpClient: OkHttpClient
) : IAppConfig {

    override fun initAppConfig() {
        setupFresco()
        setupActivityLifeCycleLogs()
        IMMLeaks.fixFocusedViewLeak(application)
    }

    private fun setupFresco() {
        val diskCacheConfig = DiskCacheConfig.newBuilder(application)
            .setBaseDirectoryName("cache")
            .setMaxCacheSizeOnVeryLowDiskSpace((1024 * 1024 * 8).toLong())
            .setMaxCacheSizeOnLowDiskSpace((1024 * 1024 * 16).toLong())
            .setMaxCacheSize((1024 * 1024 * 64).toLong())
            .build()
        val imagePipelineConfig =
            OkHttpImagePipelineConfigFactory.newBuilder(application, okHttpClient)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build()
        Fresco.initialize(application, imagePipelineConfig)
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