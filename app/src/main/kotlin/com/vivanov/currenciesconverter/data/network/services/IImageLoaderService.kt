package com.vivanov.currenciesconverter.data.network.services

import com.facebook.drawee.view.SimpleDraweeView

interface IImageLoaderService {

    fun loadImage(simpleDraweeView: SimpleDraweeView, url: String)

    fun loadFlagIcon(simpleDraweeView: SimpleDraweeView, iconCode: String)
}