package com.example.routerTCP.presentation


import android.Manifest
import android.annotation.SuppressLint
import androidx.camera.core.ImageProxy
import com.example.routerTCP.model.IQRcodeModel
import com.example.routerTCP.view.IQRcodeView
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class QrcodePresenter(private val model: IQRcodeModel) : IPresenter<IQRcodeView> {
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


    fun onGalleryClick(){
        view?.startGalleryActivity()
    }

    fun onBackButtonClick() {
        view?.startBackActivity()
    }

    @SuppressLint("UnsafeOptInUsageError", "RestrictedApi")
    fun onScanClick(imageProxy: ImageProxy) {
        view?.startMainScreenActivity()
        val inputImage =
            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

        barcodeScanner.process(inputImage).addOnSuccessListener { barcodes ->
            if (barcodes.isNotEmpty()) {
                for (barcode in barcodes) {
                    val rawValue = barcode.rawValue
                    if (rawValue != null && model.connect(rawValue)) {
                        view?.endScanProcess()
                    } else {
                        view?.toastMessage(0)
                    }
                }
            }
        }.addOnFailureListener {
            it.printStackTrace()
            view?.toastMessage(-1)
        }.addOnCompleteListener {
            imageProxy.close()
        }
    }

    private var view: IQRcodeView? = null

    //private var model: IQRcodeModel? = null
    private var isPermissionsGranted = false

    private val options = BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()
    private val barcodeScanner = BarcodeScanning.getClient(options)

    companion object{
        private val NEEDED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES
        )
    }
}