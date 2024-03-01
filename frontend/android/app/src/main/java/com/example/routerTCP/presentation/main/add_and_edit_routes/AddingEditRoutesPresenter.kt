package com.example.routerTCP.presentation.main.add_and_edit_routes

import com.example.routerTCP.R
import com.example.routerTCP.di.App
import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView

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
        if(currentActivityState == 1){
            //Если добавляем
            var success = editTextsCheck()
            if(serialNumber.isBlank()){
                success = false
                view?.setSNColor(R.color.red)
            }else{
                view?.setSNColor(R.color.black)
            }
            if(success){
                if(currentActivityState == 0){
                    //App.routesService.editRoute()
                }else{
                    //App.routesService.addRoute()
                }
                // TODO: добавляем в сервис новый путь с такими данными
                // TODO: suspend будет вызываться здесь (+сделать делэй пока что)

            }else{
                view?.setInvalidTextVisibility(true)
            }
        }else{
            //Если редактируем
            if(editTextsCheck()){
                // TODO: изменять в сервисе путь на такие данные
                // TODO: сделать делэй
            }else{
                view?.setInvalidTextVisibility(true)
            }
        }
    }

    private fun editTextsCheck(): Boolean {
        var success = true
        if(ipAddress.isBlank() || !isValidIP(ipAddress)){
            success = false
            view?.setIPColor(R.color.red)
        }else{
            view?.setIPColor(R.color.black)
        }
        if(tcpPort.isBlank() || tcpPort.toInt() !in 1 .. 65535){
            success = false
            view?.setTCPColor(R.color.red)
        }else{
            view?.setTCPColor(R.color.black)
        }
        return success
    }

    private fun isValidIP(ip: String): Boolean =
        ip.toRegex() ==
                Regex(
                    """/(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)${'$'}/"""
                )


    fun onSerialNumberTextChanged(sn: String){
        serialNumber = sn
        view?.setInvalidTextVisibility(false)
        view?.setSNColor(R.color.black)
    }

    fun onIPTextChanged(ip: String){
        ipAddress = ip
        view?.setInvalidTextVisibility(false)
        view?.setIPColor(R.color.black)
    }

    fun onTCPPortTextChanged(tcp: String){
        //todo тут проверять уже правильно ли стринг или нет
        tcpPort = tcp
        view?.setInvalidTextVisibility(false)
        view?.setTCPColor(R.color.black)
    }


    fun setActivityState(currentState: Int){
        if(currentActivityState != currentState){
            currentActivityState = currentState
        }
    }
    fun onSaveCurrentActivityState(): Int = currentActivityState

    fun onRestoreCurrentActivityState(currentState: Int){
        if(currentActivityState != currentState){
            currentActivityState = currentState
            setupState()
        }
    }

    private fun setupState(){
        if (currentActivityState == EDIT_ROUTES_STATE){
            view?.setChangeableButtonText("Редактировать")
            view?.setEnabledSerialNumberEditText(false)
            view?.setSerialNumber("06062002")
            view?.setHeader("Редактирование пути")
        }else{
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
    //String
    private var serialNumber: String = ""
    private var ipAddress: String = ""
    private var tcpPort: String = ""

    private var currentActivityState = EDIT_ROUTES_STATE

    companion object{
        private const val EDIT_ROUTES_STATE = 0 //Редактирование пути
        private const val ADD_ROUTES_STATE = 1 //Добавление пути
    }
}