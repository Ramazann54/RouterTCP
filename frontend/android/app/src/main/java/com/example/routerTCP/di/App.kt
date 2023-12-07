package com.example.routerTCP.di

import android.app.Application
import android.content.Context

class App : Application(){

    override fun onCreate() {
        //test
        context = applicationContext
        super.onCreate()
        //service = Service()
    }

    companion object{
        //test
        private lateinit var context: Context
        fun getContext(): Context {
            return this.context
        }
        //lateinit var service: IService
    }
}