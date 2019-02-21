package com.vivanov.currenciesconverter.data.network.services

import android.content.Context
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient

private const val FLAG_API_BASE_URL: String = "https://www.countryflags.io/"
private const val FLAG_API_POSTFIX: String = "/flat/64.png"

class ImageLoaderService(
    context: Context,
    okHttpClient: OkHttpClient
) : IImageLoaderService {

    init {
        val diskCacheConfig = DiskCacheConfig.newBuilder(context)
            .setBaseDirectoryName("cache")
            .setMaxCacheSizeOnVeryLowDiskSpace((1024 * 1024 * 8).toLong())
            .setMaxCacheSizeOnLowDiskSpace((1024 * 1024 * 16).toLong())
            .setMaxCacheSize((1024 * 1024 * 64).toLong())
            .build()
        val imagePipelineConfig =
            OkHttpImagePipelineConfigFactory.newBuilder(context, okHttpClient)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build()
        Fresco.initialize(context, imagePipelineConfig)
    }

    /**
     * It's better to use ImageView, for better agility. But fresco requires SimpleDraweeView.
     */
    override fun loadImage(simpleDraweeView: SimpleDraweeView, url: String) {
        simpleDraweeView.setImageURI(url)
    }

    /**
     * It's better to use ImageView, for better agility. But fresco requires SimpleDraweeView.
     */
    override fun loadFlagIcon(simpleDraweeView: SimpleDraweeView, iconCode: String) {
        simpleDraweeView.setImageURI("$FLAG_API_BASE_URL$iconCode$FLAG_API_POSTFIX")
    }
}