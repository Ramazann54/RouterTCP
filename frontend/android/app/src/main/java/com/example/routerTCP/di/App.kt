package com.example.routerTCP.di

import android.app.Application
import android.content.Context
import com.example.routerTCP.model.abstractions.routes.IRoutesService
import com.example.routerTCP.model.services.RoutesService

class App : Application(){
    override fun onCreate() {
        //test
        context = applicationContext
        super.onCreate()
        routesService = RoutesService()
    }

    companion object{
        //test
        private lateinit var context: Context
        fun getContext(): Context {
            return this.context
        }
        //lateinit var service: IService
        lateinit var routesService: IRoutesService
    }
}