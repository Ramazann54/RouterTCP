package com.example.routerTCP.presentation.main

import com.example.routerTCP.di.App
import com.example.routerTCP.model.objects.Route
import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.IMainScreenView

class MainScreenPresenter(): IPresenter<IMainScreenView> {


    fun onBindViewItem(){

    }

    fun onItemClick(position: Int){
        currentClickedPosition = position
        App.routesService.currentClickedData = position
        view?.startAddEditRouteActivity(routes[position])
    }

    override fun onViewCreated(view: IMainScreenView) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }


    private var view: IMainScreenView? = null
    var routes: List<Route> = App.routesService.routes
    var routesCount : Int = routes.size

    private var currentClickedPosition: Int = -1



}