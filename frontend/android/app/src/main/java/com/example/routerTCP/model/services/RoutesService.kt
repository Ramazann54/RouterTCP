package com.example.routerTCP.model.services

import com.example.routerTCP.model.abstractions.routes.IRoutesService
import com.example.routerTCP.model.objects.ConnectionStatus
import com.example.routerTCP.model.objects.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.UnsupportedOperationException
import kotlin.random.Random
import kotlin.random.nextInt

class RoutesService() : IRoutesService {

    override suspend fun addRoute(route: Route) {
        withContext(Dispatchers.IO) {
            delay(delayInterval)
            routes[route.serialNumber.toString()] = route
        }
    }

    override suspend fun deleteRoute(serialNumber: String) {
        withContext(Dispatchers.IO) {
            delay(delayInterval)
            routes.remove(serialNumber)
        }
    }

    override suspend fun editRoute(route: Route) {
        withContext(Dispatchers.IO) {
            delay(delayInterval)
            routes[route.serialNumber.toString()] = route
        }
    }

    override suspend fun getRoute(serialNumber: String): Route {
        return withContext(Dispatchers.IO) {
            delay(delayInterval)
            return@withContext routes[serialNumber]!!
        }
    }

    override suspend fun getUpdates(): Array<Route> {
        TODO("Not yet implemented")
    }

    override suspend fun loadRoutes(from: UInt, count: UInt): Array<Route> {
        return withContext(Dispatchers.IO) {
            delay(delayInterval)
            return@withContext routes.values.drop(from.toInt()).take(count.toInt()).toTypedArray()
        }
    }


    private val routes: MutableMap<String, Route> = buildMap {
        val offset = 10000
        for (i in 0..50) {
            put(
                (offset + 100 + i).toString(),
                Route(
                    (offset + 1000 + i).toString(),
                    50,
                    (offset + 100 + i).toLong(),
                    ConnectionStatus.values()[i % 4]
                )
            )
        }
    }.toMutableMap()


    private val delayInterval
        get() = Random.nextInt(200..5000).toLong()
}
