package com.example.routerTCP.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.routerTCP.R
import com.example.routerTCP.databinding.QrcodeScanActivityBinding
import com.example.routerTCP.presentation.QrcodePresenter
import com.example.routerTCP.view.main.main_screen.MainScreenWithTableActivity
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors


class QRcodeView : AppCompatActivity(), IQRcodeView, OnClickListener{
    private val presenter = QrcodePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(NEEDED_PERMISSIONS)
        binding = QrcodeScanActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        galleryButton = findViewById(R.id.GalleryButton)
        galleryButton.setOnClickListener(this)

        previewView = findViewById(R.id.cameraPreview)

        cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(
            {
                processCameraProvider = cameraProviderFuture.get()
                bindCameraPreview()
                bindInputAnalyser()
            }, ContextCompat.getMainExecutor(this)
        )
    }

    override fun onClick(view: View?) {
        when (view){
            galleryButton -> presenter.onGalleryClick()
            cameraButton -> presenter.onCameraClick()
            scanQRButton -> presenter.onScanClick()
        }
    }

    override fun requestPermissions(permissions: Array<String>) {
        if (permissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
            }) {
            presenter.onPermissionsGranted()
        } else {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.all { gr -> gr == PackageManager.PERMISSION_GRANTED }) {
                    presenter.onPermissionsGranted()
                } else {
                    presenter.onPermissionsGrantFail()
                }
            }
        }
    }


    private fun bindCameraPreview(){
        cameraPreview = Preview.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9).build()

        cameraPreview.setSurfaceProvider(binding.cameraPreview.surfaceProvider)

        processCameraProvider.bindToLifecycle(this, cameraSelector, cameraPreview)
    }

    private fun bindInputAnalyser(){
         val options = BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()
         val scanner = BarcodeScanning.getClient(options)

        imageAnalysis = ImageAnalysis.Builder().setTargetRotation(binding.cameraPreview.display.rotation).build()
        val cameraExecutor = Executors.newSingleThreadExecutor()
        imageAnalysis.setAnalyzer(cameraExecutor){imageProxy ->
            processImageProxy(scanner, imageProxy)
        }
        processCameraProvider.bindToLifecycle(this,cameraSelector, imageAnalysis)
    }

    //Перенести потом в презентор
    @SuppressLint("UnsafeOptInUsageError", "RestrictedApi")
    private fun processImageProxy(barcodeScanner: BarcodeScanner, imageProxy: ImageProxy){
        val inputImage = InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)

        barcodeScanner.process(inputImage).addOnSuccessListener { barcodes->
            if (barcodes.isNotEmpty()){
                for(barcode in barcodes) {
                    val rawValue = barcode.rawValue
                    if (rawValue.toString().contains("НПО МИР")) {
                        //TODO: учесть при рефакторинге кода
                        //Добавил эту строку чтобы не открывалось несколько главных экранов
                        imageAnalysis.clearAnalyzer()
                        processCameraProvider.shutdown()
                        startMainScreenActivity()
                    }
                }
            }
        }.addOnFailureListener{
            it.printStackTrace()
        }.addOnCompleteListener{
            imageProxy.close()
        }
    }

    override fun showScanResult(result: String?){
        resultText.text = result
    }

    override fun startCameraActivity() {
        TODO("Not yet implemented")
    }


    override fun startGalleryActivity(){
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
        Toast.makeText(this, "asdasdasd", Toast.LENGTH_SHORT).show()
    }

    override fun startMainScreenActivity() {
        val intent = Intent(this, MainScreenWithTableActivity::class.java)
        startActivity(intent)
    }


    private lateinit var cameraButton: Button
    private lateinit var scanQRButton: Button
    private lateinit var resultText: TextView
    private lateinit var buttonTEST: Button

    private val OPEN_FILE = 300


    private lateinit var galleryButton: Button
    private lateinit var previewView: PreviewView
    private lateinit var binding: QrcodeScanActivityBinding
    private lateinit var cameraSelector: CameraSelector
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var cameraPreview: Preview
    private lateinit var imageAnalysis: ImageAnalysis

    companion object{
        private const val PERMISSION_REQUEST_CODE = 0
        const val GALLERY_REQUEST_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
        private val NEEDED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES
        )

    }


}

