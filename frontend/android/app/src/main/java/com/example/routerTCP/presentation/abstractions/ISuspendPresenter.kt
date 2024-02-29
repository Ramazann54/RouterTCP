package com.example.routerTCP.presentation.abstractions

interface ISuspendPresenter <TView> {

    suspend fun onViewCreated(view : TView)

    suspend fun onStart(){}

    suspend fun onResume(){}

    suspend fun onPause(){}

    suspend fun onStop(){}

    suspend fun onDestroy()
}