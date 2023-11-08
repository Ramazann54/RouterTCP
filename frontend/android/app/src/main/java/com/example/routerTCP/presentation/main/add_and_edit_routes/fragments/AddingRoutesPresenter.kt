package com.example.routerTCP.presentation.main.add_and_edit_routes.fragments

import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.fragments.IAddingRoutesView

class AddingRoutesPresenter : IPresenter<IAddingRoutesView> {
    override fun onViewCreated(view: IAddingRoutesView) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

    private var view: IAddingRoutesView? = null
}