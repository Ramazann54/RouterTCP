package com.example.routerTCP.model.services

import com.example.routerTCP.model.abstractions.routes.IRoutesService
import com.example.routerTCP.model.objects.ConnectionStatus
import com.example.routerTCP.model.objects.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt

class RoutesService() : IRoutesService {
    override val routes: List<Route> = listOf(
        Route("12345678", 1234, 5689784, ConnectionStatus.ConnectWithDCS),
        Route("12345678", 1234, 5689784, ConnectionStatus.NoConnectWithDCS),
        Route("12345678", 1234, 5689784, ConnectionStatus.ConnectWithDevice),
        Route("12345678", 1234, 5689784, ConnectionStatus.NoConnectWithDevice)
        )

    override var currentClickedData: Int
        get() = _currentClickedDate
        set(value) {
            _currentClickedDate = value
        }

    override suspend fun addRoute() {
        withContext(Dispatchers.IO) {
            delay(delayInterval)
        }
    }

    override suspend fun editRoute() {
        withContext(Dispatchers.IO) {
            delay(delayInterval)
        }
    }

    var _currentClickedDate = 0
    private val delayInterval = Random.nextInt(1000..2500).toLong()

    //todo load suspend fun, подписка на обновления, события с новыми данными - обновить ui


}