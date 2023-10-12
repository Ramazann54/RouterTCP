package com.example.routerTCP.view.main_screen

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.routerTCP.R
import com.example.routerTCP.presenter.MainScreenPresenter

class MainScreenWithTable: AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen_with_table)
        recyclerView = findViewById<RecyclerView?>(R.id.recyclerViewRouteTable).apply {
            adapter = this@MainScreenWithTable.adapter
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    private lateinit var recyclerView: RecyclerView
    private val presenter = MainScreenPresenter()
    private val adapter = RoutesAdapter(presenter)

}


