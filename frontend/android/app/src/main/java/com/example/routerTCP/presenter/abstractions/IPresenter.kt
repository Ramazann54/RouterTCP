package com.example.routerTCP.presenter.abstractions

interface IPresenter<TView> {

    fun onViewCreated(view : TView)

    fun onStart() {

    }

    fun onResume() {

    }

    fun onPause() {

    }

    fun onStop() {

    }

    fun onDestroy()
}