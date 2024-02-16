package com.example.routerTCP.model

import android.net.Uri

class QRcodeModel() : IQRcodeModel {
    private lateinit var pictureURI: Uri
    private lateinit var scannedResult: String

    fun setUri(uri: Uri) {
        this.pictureURI = uri
    }

    fun setResult(scannedResult: String) {
       this.scannedResult = scannedResult
    }

    fun getUri(): Uri? {
        return this.pictureURI
    }
    fun getResult(): String {
        return this.let{scannedResult}
    }
}