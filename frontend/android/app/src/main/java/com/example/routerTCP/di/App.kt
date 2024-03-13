package com.example.routerTCP.di

import android.app.Application
import android.content.Context
import com.example.routerTCP.model.IQRcodeModel
import com.example.routerTCP.model.QRcodeModel
import com.example.routerTCP.model.abstractions.routes.IRoutesService
import com.example.routerTCP.model.services.RoutesService

class App : Application(){
    override fun onCreate() {
        //test
        context = applicationContext
        super.onCreate()
        routesService = RoutesService()
        qrCodeModel = QRcodeModel()
    }

    companion object{
        //test
        private lateinit var context: Context
        fun getContext(): Context {
            return this.context
        }
        //lateinit var service: IService
        lateinit var routesService: IRoutesService
        lateinit var qrCodeModel: IQRcodeModel
    }
}