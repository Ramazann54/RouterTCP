package com.example.routerTCP.view.abstractions

import com.example.routerTCP.model.objects.Route

interface IMainScreenView {

    fun startAddEditRouteActivity(route: Route)
}