package com.example.routerTCP.model.objects

enum class ConnectionStatus {
    ConnectWithDevice, NoConnectWithDevice, ConnectWithDCS, NoConnectWithDCS
}
// 1)Есть связь с устройством
// 2)Нет связи с устройством
// 3)Есть связь с ССД
// 4)Нет связи с ССД.
// DCS - data connection service, служба сбора данных