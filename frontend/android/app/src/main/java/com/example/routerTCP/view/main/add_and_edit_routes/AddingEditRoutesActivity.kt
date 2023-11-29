package com.example.routerTCP.view.main.add_and_edit_routes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.routerTCP.R
import com.example.routerTCP.presentation.main.add_and_edit_routes.AddingEditRoutesPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView

class AddingEditRoutesActivity : AppCompatActivity(), OnClickListener, IAddingEditRoutesView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_routes)

        changeableButton = findViewById(R.id.changeable_button)
        changeableButton.setOnClickListener(this)

        cancelButton = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener(this)

        ipAddressEditText = findViewById(R.id.ip_address)
        ipAddressTextView = findViewById(R.id.ip_address_text_view)
        tcpPortEditText = findViewById(R.id.tcp_port)
        tcpPortTextView = findViewById(R.id.tcp_port_text_view)
        serialNumberEditText = findViewById(R.id.serial_number)
        serialNumberTextView = findViewById(R.id.serial_number_text_view)
        invalidTextView = findViewById(R.id.invalidTextView)

        presenter.setActivityParam("Add")
        presenter.onViewCreated(this)
    }

    override fun onClick(p0: View?) {
        if(p0 == ipAddressEditText){
            presenter.onIPTextChanged(ipAddressEditText.text.toString())
        }
        // TODO: try?
        if(p0 == tcpPortEditText){
            presenter.onTCPPortTextChanged(tcpPortEditText.text.toString().toInt())
        }
        if(p0 == serialNumberEditText){
            presenter.onSerialNumberTextChanged(serialNumberEditText.text.toString())
        }
        if (p0 == changeableButton) {
            presenter.onChangeableButtonClick()
        }
        if (p0 == cancelButton) {
            presenter.onCancelButtonClick()
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

    override fun setInvalidTextVisibility(boolean: Boolean) {
        if(boolean){
            invalidTextView.visibility = View.VISIBLE
            return
        }
        invalidTextView.visibility = View.GONE
    }

    override fun setIPColor(color: Int) {
        ipAddressTextView.setTextColor(color)
    }

    override fun setTCPColor(color: Int) {
        tcpPortTextView.setTextColor(color)
    }

    override fun setSNColor(color: Int) {
        serialNumberTextView.setTextColor(color)
    }

    override fun finishActivity() {
        this.finish()
    }

    /**
     * кнопка которая будет изменяемой
     */
    private lateinit var changeableButton: AppCompatButton

    private lateinit var ipAddressEditText: EditText
    private lateinit var ipAddressTextView: TextView
    private lateinit var tcpPortEditText: EditText
    private lateinit var tcpPortTextView: TextView
    private lateinit var serialNumberEditText: EditText
    private lateinit var serialNumberTextView: TextView
    private lateinit var invalidTextView: TextView

    private lateinit var cancelButton: AppCompatButton

    private val presenter = AddingEditRoutesPresenter()
}