package com.example.routerTCP.view.abstractions.add_and_edit_routes

interface IAddingEditRoutesView {
    // TODO: добавить методы которые будут в активити
    fun setChangeableButtonText(text: String)

    fun setHeader(header: String)

    fun setEnabledSerialNumberEditText(boolean: Boolean)

    fun finishActivity()

    fun setInvalidTextVisibility(boolean: Boolean)

    fun setIPColor(color: Int)

    fun setTCPColor(color: Int)

    fun setSNColor(color: Int)

    fun setSerialNumber(sn: String)
}