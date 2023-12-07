package com.example.routerTCP.presentation

interface IPresenter<TView>{
    fun onViewCreated(view : TView)

    fun onDestroy()
}