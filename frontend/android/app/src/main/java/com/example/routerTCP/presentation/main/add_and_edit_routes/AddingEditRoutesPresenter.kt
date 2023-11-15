package com.example.routerTCP.presentation.main.add_and_edit_routes

import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView

class AddingEditRoutesPresenter :
    IPresenter<IAddingEditRoutesView> {

    fun setActivityParam(param : String){
        activityParam = param
    }

    override fun onViewCreated(view: IAddingEditRoutesView) {
        this.view = view
        if(activityParam == "Add"){
            view.setChangeableButtonText("Добавить")
            view.setEnabledSerialNumberEditText(true)
            view.setHeader("Добавление пути")
        }else{
            view.setChangeableButtonText("Редактировать")
            view.setEnabledSerialNumberEditText(false)
            view.setHeader("Редактирование пути")
        }
    }

    fun onChangeableButtonClick(){

    }

    override fun onDestroy() {
        view = null
    }

    private var view: IAddingEditRoutesView? = null
    private var activityParam = ""
}