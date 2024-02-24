package com.example.routerTCP.view

interface IQRcodeView {
    fun requestPermissions(permissions: Array<String>)
    fun showScanResult(result: String?)
    fun startCameraActivity()
    fun startGalleryActivity()
    fun startMainScreenWithTableActivity()
}