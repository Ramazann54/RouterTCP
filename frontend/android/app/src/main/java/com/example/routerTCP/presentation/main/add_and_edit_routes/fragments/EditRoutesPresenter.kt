package com.example.routerTCP.presentation.main.add_and_edit_routes.fragments

import com.example.routerTCP.presentation.abstractions.IPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.fragments.IEditRoutesView

class EditRoutesPresenter : IPresenter<IEditRoutesView> {
    override fun onViewCreated(view: IEditRoutesView) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

    private var view: IEditRoutesView? = null
}