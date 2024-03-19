package com.example.routerTCP.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.routerTCP.R
import com.example.routerTCP.presentation.main.MainScreenPresenter
import kotlinx.coroutines.launch

open class MyDialogFragment(private val presenter: MainScreenPresenter) : DialogFragment(), View.OnClickListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.delete_dialog, container, false)

        deleteButton = view.findViewById(R.id.deleteBtn)
        cancelButton = view.findViewById(R.id.cancelBtn)

        deleteButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)

        progressBar = ButtonLoading(deleteButton)
        return view
    }


    override fun onClick(view: View?) {
        if(view === deleteButton){
            lifecycleScope.launch{
                progressBar.setLoading()
                presenter.deleteRoute()
                dismiss()
            }
        }
        if (view === cancelButton){
            dismiss()
        }
    }


    private lateinit var deleteButton: LinearLayoutCompat
    private lateinit var cancelButton: AppCompatButton
    private lateinit var progressBar: ButtonLoading




}