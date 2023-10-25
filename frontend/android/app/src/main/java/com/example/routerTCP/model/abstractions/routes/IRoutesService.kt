package com.example.routerTCP.model.abstractions.routes


interface IRoutesService {
    var ipAddress: List<String>
    var tcpPort: List<Int>
    var serialNumber: List<Long>
    var routesCount: Int
}