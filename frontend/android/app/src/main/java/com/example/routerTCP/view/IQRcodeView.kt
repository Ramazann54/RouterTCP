package com.example.routerTCP.view

interface IQRcodeView {
    fun requestPermissions(permissions: Array<String>)
    fun startGalleryActivity()
    fun startMainScreenActivity()

    fun endScanProcess()

    fun startBackActivity()

    fun toastMessage(code: Int)
}