package com.example.routerTCP.presentation.main.add_and_edit_routes

import com.example.routerTCP.R
import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView

class AddingEditRoutesPresenter :
    IPresenter<IAddingEditRoutesView> {

    fun setActivityParam(param: String) {
        activityParam = param
    }

    override fun onViewCreated(view: IAddingEditRoutesView) {
        this.view = view
        if (activityParam == "Add") {
            view.setChangeableButtonText("Добавить")
            view.setEnabledSerialNumberEditText(true)
            view.setHeader("Добавление пути")
        } else {
            //выставление надписи на кнопке, блокировка серийного номера и изменение хедера
            view.setChangeableButtonText("Редактировать")
            view.setEnabledSerialNumberEditText(false)
            view.setHeader("Редактирование пути")
        }
    }

    fun onCancelButtonClick() {
        view?.finishActivity()
    }

    fun onChangeableButtonClick() {
        if(activityParam == "Add"){
            //Если добавляем
            var success = editTextsCheck()
            if(serialNumber.isBlank()){
                success = false
                view?.setSNColor(R.color.red)
            }else{
                view?.setSNColor(R.color.black)
            }
            if(success){
                // TODO: добавляем в сервис новый путь с такими данными
            }else{
                view?.setInvalidTextVisibility(true)
            }
        }else{
            //Если редактируем
            if(editTextsCheck()){
                // TODO: изменять в сервисе путь на такие данные
            }else{
                view?.setInvalidTextVisibility(true)
            }
        }
    }

    private fun editTextsCheck(): Boolean {
        var success = true
        // TODO: добавить проверку ip
        if(ipAddress.isBlank()){
            success = false
            view?.setIPColor(R.color.red)
        }else{
            view?.setIPColor(R.color.black)
        }
        if(tcpPort !in 1 .. 65535){
            success = false
            view?.setTCPColor(R.color.red)
        }else{
            view?.setTCPColor(R.color.black)
        }
        return success
    }

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

    fun onTCPPortTextChanged(tcp: Int){
        tcpPort = tcp
        view?.setInvalidTextVisibility(false)
        view?.setTCPColor(R.color.black)
    }
    private fun setupState(){
        if (){

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
    private var tcpPort: Int = 0

    private var currentActivityState = EDIT_ROUTES_STATE

    companion object{
        private const val EDIT_ROUTES_STATE = 0 //Редактирование пути
    }
}