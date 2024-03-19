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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.routerTCP.R
import com.example.routerTCP.databinding.QrcodeScanActivityBinding
import com.example.routerTCP.di.App
import com.example.routerTCP.presentation.QrcodePresenter
import com.example.routerTCP.view.main.main_screen.MainScreenWithTableActivity
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.Executors


class QRcodeView : AppCompatActivity(), IQRcodeView, OnClickListener{
    private val presenter = QrcodePresenter(App.qrCodeModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(NEEDED_PERMISSIONS)

        setContentView(R.layout.qrcode_scan_activity)

        binding = QrcodeScanActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        galleryButton = findViewById(R.id.GalleryButton)
        galleryButton.setOnClickListener(this)

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener(this)

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
            backButton -> presenter.onBackButtonClick()
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
        imageAnalysis = ImageAnalysis.Builder().setTargetRotation(binding.cameraPreview.display.rotation).build()
        val cameraExecutor = Executors.newSingleThreadExecutor()
        imageAnalysis.setAnalyzer(cameraExecutor){imageProxy ->
            presenter.onScanClick(imageProxy)
        }
        processCameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis)
    }


    @SuppressLint("RestrictedApi")
    override fun endScanProcess(){
            imageAnalysis.clearAnalyzer()
            processCameraProvider.shutdown()
            startMainScreenActivity()
    }

    override fun startGalleryActivity(){
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun startMainScreenActivity() {
        val intent = Intent(this, MainScreenWithTableActivity::class.java)
        startActivity(intent)
    }

    override fun startBackActivity() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun toastMessage(code: Int){
        when(code){
            0 -> Toast.makeText(this, "Некорректное содержание QR кода, попробуйте снова", Toast.LENGTH_SHORT).show()
            -1 -> Toast.makeText(this, "Ошибка при сканировании QR кода, попробуйте снова", Toast.LENGTH_SHORT).show()
        }
    }



    private lateinit var backButton: Button

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
        private val NEEDED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES
        )

    }


}

