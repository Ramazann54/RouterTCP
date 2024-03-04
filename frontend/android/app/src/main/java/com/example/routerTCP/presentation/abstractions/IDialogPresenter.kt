package com.example.routerTCP.presentation.abstractions

interface IDialogPresenter<TDialogView : IDialogView> {

    fun onDialogCreate(view : TDialogView)

    fun onDialogDestroy()

    fun onDeleteButtonClick()

    fun onCancelButtonClick()
}