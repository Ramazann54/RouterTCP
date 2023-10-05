package com.example.routerTCP.di

import android.app.Application

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        //service = Service()
    }

    companion object{
        //lateinit var service: IService
    }
}