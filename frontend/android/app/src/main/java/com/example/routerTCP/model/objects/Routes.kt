package com.example.routerTCP.model.objects

class Route(
    val ipAddress: String,
    val tcpPort: Int,
    val serialNumber: Long,
    val connectionStatus: ConnectionStatus
)