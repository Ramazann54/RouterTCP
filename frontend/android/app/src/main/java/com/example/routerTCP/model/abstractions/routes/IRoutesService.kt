package com.example.routerTCP.model.abstractions.routes

import com.example.routerTCP.model.objects.Route


interface IRoutesService {
    val routes: List<Route>
    var currentClickedData: Int
}