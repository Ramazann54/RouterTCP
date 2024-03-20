package com.example.routerTCP.view.abstractions

interface IMainScreenView {

    fun showDeleteDialog()

    fun startEditingActivity(routeSN: String)

    fun startAddingActivity()

    fun notifyDataCh()

    fun onResume()
}