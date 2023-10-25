package com.example.routerTCP.view.main_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.routerTCP.R
import com.example.routerTCP.presenter.MainScreenPresenter
import com.example.routerTCP.view.abstractions.IMainScreenView

class MainScreenWithTable: AppCompatActivity(), IMainScreenView, OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen_with_table)
        recyclerView = findViewById<RecyclerView?>(R.id.recyclerViewRouteTable).apply {
            adapter = this@MainScreenWithTable.adapter
        }
        addButton = findViewById(R.id.addRouteButton)
        addButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view === addButton){
            //todo: переход на следующую активити
        }
    }

   /* override fun startAddRoutesActivity(){
        val intent = Intent(this, )
    }*/

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: AppCompatButton
    private val presenter = MainScreenPresenter()
    private val adapter = RoutesAdapter(presenter)

}


