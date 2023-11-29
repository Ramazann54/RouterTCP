package com.example.routerTCP.di

import android.app.Application
import com.example.routerTCP.model.abstractions.routes.IRoutesService
import com.example.routerTCP.model.services.RoutesService

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        routesService = RoutesService()
    }

    companion object{
        lateinit var routesService: IRoutesService
    }
}