package com.example.routerTCP.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.routerTCP.R
import com.example.routerTCP.presentation.abstractions.IDeleteDialogPresenter
import com.example.routerTCP.view.abstractions.IDeleteDialogView
import com.example.routerTCP.view.abstractions.IDialogView

class DeleteDialog (presenter: IDeleteDialogPresenter) : MyDialogFragment<IDeleteDialogView>(presenter), IDialogView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val dialogView = layoutInflater.inflate(R.layout.delete_dialog_fragment, contentDialogLayout)
        return view
    }

}