package com.example.routerTCP.presentation


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.routerTCP.di.App
import com.example.routerTCP.view.IQRcodeView
import com.example.routerTCP.view.QRcodeView
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

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
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        //startActivityForResult(galleryIntent, galleryIntent)
    }

    fun onCameraClick(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
    }

    fun onScanClick(){
        //presenter.onScanButtonClicked()
    }

    fun onScanButtonClicked(){
        val image = InputImage.fromFilePath(App.getContext(), pictureURI)
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for(barcode in barcodes){ //Вроде как по настройкам должен быть список из одного qr кода
                    //val rawValue = barcode.rawValue
                    this.scannedResult = barcode.rawValue
                }
                view?.showScanResult(scannedResult)
            }
            .addOnFailureListener {e->
                // Task failed with an exception
            }
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