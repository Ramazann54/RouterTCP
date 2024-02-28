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
import androidx.core.content.ContextCompat
import com.example.routerTCP.R

class StartActivity: AppCompatActivity(), OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)


        scanButton = findViewById(R.id.changeable_button)
        scanButton.setOnClickListener(this)

        test = findViewById(R.id.aaaaa)
        test.setOnClickListener(this)
        }


    private lateinit var scanButton: Button
    private lateinit var test: Button

    private fun xd(){
        val intent = Intent(this, QRcodeView::class.java)
        startActivity(intent)
    }

    private fun xdd(){
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, QRcodeView.GALLERY_REQUEST_CODE)
        Toast.makeText(this, "asdasdasd", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when (view){
            scanButton -> xd()
            test -> xdd()
        }
    }

}