package com.example.routerTCP.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.routerTCP.R
import com.example.routerTCP.view.main.main_screen.MainScreenWithTableActivity

class StartActivity: AppCompatActivity(), OnClickListener {

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


     private fun startMainScreenActivity() {
        val intent = Intent(this, MainScreenWithTableActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        when (view){
            test -> startMainScreenActivity()
        }
    }

}