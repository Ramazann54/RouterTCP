package com.example.routerTCP.presenter.abstractions

import com.example.routerTCP.view.abstractions.IDialogView

interface IDialogPresenter<TDialogView : IDialogView> {

    fun onDialogCreate(view : TDialogView)

    fun onDialogDestroy()

    fun onDeleteButtonClick()

    fun onCancelButtonClick()
}