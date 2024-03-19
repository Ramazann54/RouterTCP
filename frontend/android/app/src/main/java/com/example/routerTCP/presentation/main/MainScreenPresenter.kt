package com.example.routerTCP.presentation.main

import com.example.routerTCP.di.App
import com.example.routerTCP.model.objects.Route
import com.example.routerTCP.presentation.abstractions.ISuspendPresenter
import com.example.routerTCP.view.abstractions.IMainScreenView

class MainScreenPresenter : ISuspendPresenter<IMainScreenView> {


    fun onBindViewItem(){

    }

    fun onItemClick(position: Int){
        currentClickedPosition = position
        view?.startAddEditRouteActivity(routes[position])
    }

    fun onAddButtonClick(){

    }

    fun onDeleteClick(position: Int){
        currentClickedPosition = position
        view?.showDeleteDialog()
    }

    suspend fun deleteRoute(){
        routeService.deleteRoute(routes[currentClickedPosition].serialNumber.toString())
        routes.remove(routes[currentClickedPosition])
        view?.notifyDataCh()
    }

    override suspend fun onViewCreated(view: IMainScreenView) {
        this.view = view
        val r = routeService.loadRoutes(0u, 10u)
        routes.addAll(r)
        view.notifyDataCh()
    }

    override suspend fun onDestroy() {
        view = null
    }



    val routes: MutableList<Route> = mutableListOf()
    val routesCount : Int
        get() = routes.size

    private var view: IMainScreenView? = null
    private val routeService = App.routesService
    private var currentClickedPosition: Int = -1
}