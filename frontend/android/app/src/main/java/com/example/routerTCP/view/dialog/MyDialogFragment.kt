package com.example.routerTCP.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.routerTCP.R
import com.example.routerTCP.presentation.main.MainScreenPresenter
import kotlinx.coroutines.launch

open class MyDialogFragment : DialogFragment(), View.OnClickListener{

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


    override fun onClick(view: View?) {
        if(view === deleteButton){
            lifecycleScope.launch{
                presenter.deleteRoute()
            }
        }
        if (view === cancelButton){
            dismiss()
        }
    }

    private var contentDialogLayout: LinearLayoutCompat? = null
    private var deleteButton: Button? = null
    private var cancelButton: Button? = null
    private val presenter = MainScreenPresenter()


}