package com.example.routerTCP.presentation.main.add_and_edit_routes

import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.IAddingEditRoutesView

class AddingEditRoutesPresenter :
    IPresenter<IAddingEditRoutesView> {

    override fun onViewCreated(view: IAddingEditRoutesView) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

    private var view: IAddingEditRoutesView? = null
}