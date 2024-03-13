package com.example.routerTCP.model


interface IQRcodeModel {
    fun validate(rawValue: String): Boolean
    fun connect(rawValue: String): Boolean
}
