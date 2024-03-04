package com.example.routerTCP.view.abstractions

import com.example.routerTCP.model.objects.Route

interface IMainScreenView {

    fun showDeleteDialog()
    fun startAddEditRouteActivity(route: Route)
}