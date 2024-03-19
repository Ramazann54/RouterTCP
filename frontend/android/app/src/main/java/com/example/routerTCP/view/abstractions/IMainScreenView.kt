package com.example.routerTCP.view.abstractions

interface IMainScreenView {

    fun showDeleteDialog()

    fun startAddEditRouteActivity(route: Route)

    fun startEditingActivity(routeSN: String)

    fun startAddingActivity()

    fun notifyDataCh()
}