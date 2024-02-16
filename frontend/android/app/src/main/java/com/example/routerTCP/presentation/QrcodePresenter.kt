package com.example.routerTCP.presentation


import android.Manifest
import android.net.Uri
import com.example.routerTCP.view.IQRcodeView
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode

class QrcodePresenter : IPresenter<IQRcodeView> {
    override fun onViewCreated(view: IQRcodeView) {
        this.view = view
        view.requestPermissions(NEEDED_PERMISSIONS)
    }

    override fun onDestroy() {
        this.view = null
    }

    fun onPermissionsGranted(){
        isPermissionsGranted = true
    }

    fun onPermissionsGrantFail(){
        isPermissionsGranted = false
    }

    fun onPictureChanged(uri: Uri) {
        this.pictureURI = uri
    }

    fun onGalleryClick(){
        view?.startGalleryActivity()
    }

    fun onCameraClick(){
        view?.startCameraActivity()
    }

    fun onScanClick(){
        view?.startMainScreenWithTableActivity()

        /*
        val image = InputImage.fromFilePath(App.getContext(), pictureURI)
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for(barcode in barcodes){ //Вроде как по настройкам должен быть список из одного qr кода
                    val rawValue = barcode.rawValue
                    this.scannedResult = barcode.rawValue
                }
                view?.showScanResult(scannedResult)
            }
            .addOnFailureListener {e->
                // Task failed with an exception
            }
            */
    }

    private var view: IQRcodeView? = null
    private lateinit var pictureURI: Uri
    private var isPermissionsGranted = false
    private var scannedResult: String? = null

    private val options = BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()
    private val scanner = BarcodeScanning.getClient(options)

    companion object{
        private val NEEDED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        private const val GALLERY_REQUEST_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }
}