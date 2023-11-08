package com.example.routerTCP.view.main.add_and_edit_routes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.routerTCP.R
import com.example.routerTCP.presentation.main.add_and_edit_routes.AddingEditRoutesPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView

class AddingEditRoutesActivity : AppCompatActivity(), OnClickListener, IAddingEditRoutesView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_routes)

        presenter.onViewCreated(this)

        addButton = findViewById(R.id.add_button)
        addButton.setOnClickListener(this)

        cancelButton = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener(this)

        ipAddressEditText = findViewById(R.id.ip_address)
        tcpPortEditText = findViewById(R.id.tcp_port)
        serialNumberEditText = findViewById(R.id.serial_number)
    }

    override fun onClick(p0: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
    private lateinit var ipAddressEditText: EditText
    private lateinit var tcpPortEditText: EditText
    private lateinit var serialNumberEditText: EditText
    private lateinit var addButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton
    private val presenter = AddingEditRoutesPresenter()
}