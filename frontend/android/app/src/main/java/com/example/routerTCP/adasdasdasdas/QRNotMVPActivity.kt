package com.example.routerTCP.adasdasdasdas

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.routerTCP.R
import com.google.android.material.button.MaterialButton


private lateinit var cameraPermission: Array<String>
private lateinit var storagePermission: Array<String>

private var imageUri: Uri? = null
class QRNotMVPActivity : AppCompatActivity() {

    private lateinit var cameraButton: MaterialButton
    private lateinit var galleryButton: MaterialButton
    private lateinit var scannButton: MaterialButton
    private lateinit var qrImageView:ImageView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_activity)


        cameraButton = findViewById(R.id.CameraButton)
        galleryButton = findViewById(R.id.GalleryButton)
        scannButton = findViewById(R.id.ScanButton)
        qrImageView = findViewById(R.id.QRImageView)
        resultText = findViewById(R.id.ResultText)


        cameraPermission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        storagePermission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)



        cameraButton.setOnClickListener{
            when {
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                    getQrFromCamera()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, android.Manifest.permission.CAMERA) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected, and what
                    // features are disabled if it's declined. In this UI, include a
                    // "cancel" or "no thanks" button that lets the user continue
                    // using your app without granting the permission.
                    //showInContextUI(...)
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    //requestPermissionLauncher.launch(
                        //Manifest.permission.REQUESTED_PERMISSION)
                }
            }
        }

        galleryButton.setOnClickListener{
            getQrFromGallery()
        }
        scannButton.setOnClickListener{
            scanQRCode()
        }
    }



    private fun getQrFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryActivityResultLauncher.launch(intent)
    }

    private val galleryActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if (result.resultCode == Activity.RESULT_OK){
            val data = result.data
            imageUri = data?.data
            qrImageView.setImageURI(imageUri)
        } else{
            //.....
        }
    }

    private fun getQrFromCamera(){
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, "QrPhoto")
        contentValues.put(MediaStore.Images.Media.TITLE, "Photo of qrCode")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        cameraActivityResultLauncher.launch(intent)
    }

    private val cameraActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if (result.resultCode == Activity.RESULT_OK){
            val data = result.data
            qrImageView.setImageURI(imageUri)
        } else{
            //......
        }
    }

    private fun scanQRCode(){
        //......
    }
}
