package com.example.routerTCP.view.main.main_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routerTCP.R
import com.example.routerTCP.model.objects.Route
import com.example.routerTCP.presentation.main.MainScreenPresenter
import com.example.routerTCP.view.abstractions.IMainScreenView
import com.example.routerTCP.view.dialog.MyDialogFragment
import com.example.routerTCP.view.main.add_and_edit_routes.AddingEditRoutesActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainScreenWithTableActivity : AppCompatActivity(), IMainScreenView, OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen_with_table)
        recyclerView = findViewById<RecyclerView?>(R.id.recyclerViewRouteTable).apply {
            adapter = this@MainScreenWithTableActivity.adapter
        }
        recyclerView.layoutManager = LinearLayoutManager(this@MainScreenWithTableActivity)

        addButton = findViewById(R.id.addRouteButton)
        addButton.setOnClickListener(this)
        lifecycleScope.launch {
            presenter.onViewCreated(this@MainScreenWithTableActivity)
        }
    }

    override fun startAddEditRouteActivity(route: Route) {
        startAddEditActivity(0)
        // TODO: добавлять рут в сервис
    }

    override fun onClick(view: View?) {
        if (view === addButton) {
            presenter.onAddButtonClick()
            startAddEditActivity(1)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun notifyDataCh() {
        adapter.notifyDataSetChanged()
    }

    private fun startAddEditActivity(value: Int) {
        val intent = Intent(this, AddingEditRoutesActivity::class.java)
        intent.putExtra("STATE", value)
        startActivity(intent)
    }

    override fun showDeleteDialog(){
        dialog.show(supportFragmentManager, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            presenter.onDestroy()
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: AppCompatButton
    private val presenter = MainScreenPresenter()
    private val dialog = MyDialogFragment(presenter)
    private val adapter = RoutesAdapter(presenter)
}


