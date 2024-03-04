package com.example.routerTCP.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import com.example.routerTCP.R
import com.example.routerTCP.presentation.abstractions.IDialogPresenter
import com.example.routerTCP.view.abstractions.IDialogView

open class MyDialogFragment<TDialogView : IDialogView>(private val presenter: IDialogPresenter<TDialogView>) :
    DialogFragment(), View.OnClickListener, IDialogView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.delete_dialog, container, false)
        contentDialogLayout = view.findViewById(R.id.dialog_content_layout)
        deleteButton = view.findViewById(R.id.deleteBtn)
        cancelButton = view.findViewById(R.id.cancelBtn)
        deleteButton?.setOnClickListener(this)
        cancelButton?.setOnClickListener(this)
        return view
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

 var contentDialogLayout: LinearLayoutCompat? = null
private var titleTextView: TextView? = null
private var deleteButton: Button? = null
private var cancelButton: Button? = null


}