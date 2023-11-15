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

        changeableButton = findViewById(R.id.add_button)
        changeableButton.setOnClickListener(this)

        cancelButton = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener(this)

        ipAddressEditText = findViewById(R.id.ip_address)
        tcpPortEditText = findViewById(R.id.tcp_port)
        serialNumberEditText = findViewById(R.id.serial_number)

        presenter.setActivityParam("Add")
        presenter.onViewCreated(this)
    }

    override fun onClick(p0: View?) {
        if(p0 == changeableButton){
            presenter.onChangeableButtonClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setChangeableButtonText(text: String) {
        changeableButton.text = text
    }

    override fun setHeader(header: String) {
        supportActionBar?.title = header
    }

    override fun setEnabledSerialNumberEditText(boolean: Boolean) {
        serialNumberEditText.isEnabled = boolean
    }

    /**
     * кнопка которая будет изменяемой
     */
    private lateinit var changeableButton: AppCompatButton

    private lateinit var ipAddressEditText: EditText
    private lateinit var tcpPortEditText: EditText
    private lateinit var serialNumberEditText: EditText

    private lateinit var cancelButton: AppCompatButton

    private val presenter = AddingEditRoutesPresenter()
}