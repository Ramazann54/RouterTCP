package com.example.routerTCP.view.abstractions.add_and_edit_routes

interface IAddingEditRoutesView {
    // TODO: добавить методы которые будут в активити
    fun setChangeableButtonText(text: String)

    fun setHeader(header: String)

    fun setEnabledSerialNumberEditText(boolean: Boolean)
}