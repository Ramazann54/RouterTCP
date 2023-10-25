package com.example.routerTCP.model.services

import android.location.Address
import com.example.routerTCP.model.abstractions.routes.IRoutesService

class RoutesService : IRoutesService {
    override var ipAddress: List<String>
        get() = addressIp
        set(value) {
            addressIp = value
        }
    override var tcpPort: List<Int>
        get() = portTCP
        set(value) {portTCP}
    override var serialNumber: List<Long>
        get() = deviceSerialNumber
        set(value) {deviceSerialNumber = value}
    override var routesCount: Int
        get() = countOfRoutes
        set(value) {
            countOfRoutes = value
        }


    var addressIp: List<String> = listOf(
        "123.123.123.123",
        "123.123.123.124", "123.123.123.125"
    )
    var countOfRoutes: Int = 5
    var deviceSerialNumber: List<Long> = listOf(127897, 1235589, 6564654)
    var portTCP: List<Int> = listOf(1234, 6543, 7894)
}