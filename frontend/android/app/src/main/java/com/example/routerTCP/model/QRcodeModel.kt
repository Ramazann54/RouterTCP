package com.example.routerTCP.model

import java.net.Socket

class QRcodeModel() : IQRcodeModel {
    private val IPADDRESS_REGEX_PATTERN = Regex(
        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5]):((6553[0-5])|(655[0-2][0-9])|" +
                "(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})|([0-5]{0,5})|([0-9]{1,4}))\$"
    ) //регулярка для проверка того, что это ip

    private val END_POINT: String = "вставить ip:port"
    override fun validate(rawValue: String): Boolean {
        return (rawValue.matches(IPADDRESS_REGEX_PATTERN) && rawValue.compareTo(END_POINT) == 0)
    }

    override fun connect(rawValue: String): Boolean {
        if (validate(rawValue)) {
            val (ip, port) = rawValue.split(":")
            try {
                val socket = Socket(ip, port.toInt())
                if (socket.isConnected) return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    //TODO: разграничить/переименовать модель в service
}