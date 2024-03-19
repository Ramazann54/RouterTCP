package com.example.routerTCP.presentation.main.add_and_edit_routes

import com.example.routerTCP.R
import com.example.routerTCP.di.App
import com.example.routerTCP.model.objects.ConnectionStatus
import com.example.routerTCP.model.objects.Route
import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView
import java.util.regex.Pattern


class AddingEditRoutesPresenter :
    IPresenter<IAddingEditRoutesView> {

    override fun onViewCreated(view: IAddingEditRoutesView) {
        this.view = view
        setupState()
    }

    fun onCancelButtonClick() {
        view?.finishActivity()
    }

    suspend fun onChangeableButtonClick() {
        if (success) {//введенные данные прошли проверку
            if (currentActivityState == 1) {//в данный момент экран добавления
                App.routesService.addRoute(
                    Route(
                        ipAddress,
                        tcpPort.toInt(),
                        serialNumber.toLong(),
                        ConnectionStatus.ConnectWithDevice
                    )
                )
            } else {//в данный момент экран редактирования
                App.routesService.editRoute(
                    Route(
                        ipAddress,
                        tcpPort.toInt(),
                        serialNumber.toLong(),
                        ConnectionStatus.ConnectWithDCS
                    )
                )
            }
            view?.finishActivity()
        } else {//введенные данные не прошли проверку
            success = true
        }
    }

    private fun isValidIP(ip: String): Boolean = Pattern.compile(
        "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                + "|[1-9][0-9]|[0-9]))"
    ).matcher(ip).matches()


    fun onIPTextChanged(ip: String) {
        ipAddress = ip
        if (ipAddress.isNotBlank()) {
            if (isValidIP(ipAddress)) {
                view?.setInvalidIPTextVisibility(false)
                view?.setIPColor(R.color.black)
                return
            }
        }
        success = false
        view?.setInvalidIPTextVisibility(true)
        view?.setIPColor(R.color.red)
        return
    }

    fun onTCPPortTextChanged(tcp: String) {
        tcpPort = tcp
        if (tcpPort.isBlank() || tcpPort.toInt() !in 1..65535) {
            success = false
            view?.setInvalidTCPTextVisibility(true)
            view?.setTCPColor(R.color.red)
        } else {
            view?.setInvalidTCPTextVisibility(false)
            view?.setTCPColor(R.color.black)
        }
    }

    fun onSerialNumberTextChanged(sn: String) {
        serialNumber = sn
        if (serialNumber.isBlank()) {
            success = false
            view?.setInvalidSNTextVisibility(true)
            view?.setSNColor(R.color.red)
        } else {
            view?.setInvalidSNTextVisibility(false)
            view?.setSNColor(R.color.black)
        }
    }


    fun setActivityState(currentState: Int) {
        if (currentActivityState != currentState) {
            currentActivityState = currentState
        }
    }

    fun setRouteSerialNumber(currentRouteSerialNumber: String) {
        if (selectedRouteSN != currentRouteSerialNumber) {
            selectedRouteSN = currentRouteSerialNumber
        }
    }

    fun onSaveCurrentActivityState(): Int = currentActivityState

    fun onRestoreCurrentActivityState(currentState: Int) {
        if (currentActivityState != currentState) {
            currentActivityState = currentState
            setupState()
        }
    }

    private fun setupState() {
        if (currentActivityState == EDIT_ROUTES_STATE) {
            view?.setChangeableButtonText("Редактировать")
            view?.setEnabledSerialNumberEditText(false)
            view?.setSerialNumber(selectedRouteSN)
            view?.setHeader("Редактирование пути")
        } else {
            view?.setChangeableButtonText("Добавить")
            view?.setEnabledSerialNumberEditText(true)
            view?.setHeader("Добавление пути")
        }
    }

    override fun onDestroy() {
        view = null
        activityParam = ""
    }

    private var view: IAddingEditRoutesView? = null
    private var activityParam = ""
    private var success = true
    private var serialNumber = ""
    private var ipAddress = ""
    private var tcpPort = ""
    private var selectedRouteSN = ""
    private var currentActivityState = EDIT_ROUTES_STATE

    companion object {
        private const val EDIT_ROUTES_STATE = 0 //Редактирование пути
        private const val ADD_ROUTES_STATE = 1 //Добавление пути
    }
}