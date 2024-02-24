package com.example.routerTCP.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.routerTCP.R
import com.example.routerTCP.presentation.QrcodePresenter

class QRcodeView : AppCompatActivity(), IQRcodeView, OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_activity)

        qrImage = findViewById(R.id.QRImageView)

        galleryButton = findViewById(R.id.GalleryButton)
        galleryButton.setOnClickListener(this)

        cameraButton = findViewById(R.id.CameraButton)
        cameraButton.setOnClickListener(this)

        scanQRButton = findViewById(R.id.ScanButton)
        scanQRButton.setOnClickListener(this)

        resultText = findViewById(R.id.ResultText)
        presenter.onViewCreated(this)
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

    override fun showScanResult(result: String?){
        resultText.text = result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Прикрутить иф, что если это с камеры, то нужно еще создать изображение
        if ((requestCode == GALLERY_REQUEST_CODE || requestCode == CAMERA_REQUEST_CODE) && resultCode == Activity.RESULT_OK) {
            val imageUri =  data?.data
            imageUri?.let {
                this.qrImage.setImageURI(imageUri) //замена икнонки на фото
                presenter.onPictureChanged(it) } //отдача презентеру, чтоб обновил информацию
        }
    }

    override fun startCameraActivity(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
    }

    override fun startGalleryActivity(){
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    private lateinit var galleryButton: Button
    private lateinit var cameraButton: Button
    private lateinit var scanQRButton: Button
    private lateinit var qrImage: ImageView
    private lateinit var resultText: TextView
    private val presenter = QrcodePresenter()

    companion object{
        private const val PERMISSION_REQUEST_CODE = 0
        private const val GALLERY_REQUEST_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

}
