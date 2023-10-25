package com.example.routerTCP.presenter

import com.example.routerTCP.presenter.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.IMainScreenView

class MainScreenPresenter: IPresenter<IMainScreenView> {


    fun onBindViewItem(){

    }

    fun onItemClick(position: Int){

    }

    override fun onViewCreated(view: IMainScreenView) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }


    private var view: IMainScreenView? = null
}