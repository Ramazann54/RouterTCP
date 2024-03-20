package com.example.routerTCP.presentation.main

import com.example.routerTCP.di.App
import com.example.routerTCP.model.objects.Route
import com.example.routerTCP.presentation.abstractions.ISuspendPresenter
import com.example.routerTCP.view.abstractions.IMainScreenView

class MainScreenPresenter : ISuspendPresenter<IMainScreenView> {
    override suspend fun onViewCreated(view: IMainScreenView) {
        this.view = view
        val r = routeService.loadRoutes(0u, 10u)
        routes.addAll(r)
        view.notifyDataCh()
    }

    fun onBindViewItem() {

    }

    fun onItemClick(position: Int) {
        currentClickedPosition = position
        view?.startEditingActivity(routes[position].serialNumber.toString())
    }

    fun onAddButtonClick() {
        view?.startAddingActivity()
    }

    fun onDeleteClick(position: Int) {
        currentClickedPosition = position
        view?.showDeleteDialog()
    }

    fun setCurrentSerialNumber(serialNumber: String) {
        currentClickedSerialNumber = serialNumber
    }

    fun setLastState(state: Int) {
        lastState = state
    }

    suspend fun deleteRoute() {
        routeService.deleteRoute(routes[currentClickedPosition].serialNumber.toString())
        val r = App.routesService.loadRoutes(0u, 10u)
        routes.clear()
        routes.addAll(r)
        view?.notifyDataCh()
    }

    override suspend fun onResume() {
        val r = App.routesService.loadRoutes(0u, 10u)
        when (lastState) {
            1 -> {//добавляли
                routes.clear()
                routes.addAll(r)
                view?.notifyDataCh()
            }

            0 -> {//редактировали
                val editablePresenterRoute = routes[currentClickedPosition]
                val editableServiceRoute = App.routesService.getRoute(currentClickedSerialNumber)
                routes[currentClickedPosition] = Route(
                    editableServiceRoute.ipAddress,
                    editableServiceRoute.tcpPort,
                    editablePresenterRoute.serialNumber,
                    editablePresenterRoute.connectionStatus
                )
                routes.clear()
                routes.addAll(r)
                view?.notifyDataCh()
            }

            else -> {
                return
            }
        }
    }


    override suspend fun onDestroy() {
        view = null
    }

    val routes: MutableList<Route> = mutableListOf()
    val routesCount: Int
        get() = routes.size

    private var lastState = -10
    private var currentClickedSerialNumber = ""
    private var view: IMainScreenView? = null
    private val routeService = App.routesService
    private var currentClickedPosition: Int = -1
}