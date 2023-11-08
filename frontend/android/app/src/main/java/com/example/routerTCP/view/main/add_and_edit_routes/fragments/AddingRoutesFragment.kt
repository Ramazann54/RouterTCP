package com.example.routerTCP.view.main.add_and_edit_routes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.routerTCP.R
import com.example.routerTCP.presentation.main.add_and_edit_routes.fragments.AddingRoutesPresenter
import com.example.routerTCP.view.abstractions.add_and_edit_routes.fragments.IAddingRoutesView

class AddingRoutesFragment :
    Fragment(),
    View.OnClickListener,
    IAddingRoutesView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adding_routes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated(this)

        // TODO: реализация элементов
    }

    override fun onClick(p0: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private val presenter = AddingRoutesPresenter()
    // TODO: Добавить кнопки едитТексты с разметки
    
    companion object {
        @JvmStatic
        fun newInstance() = AddingRoutesFragment()
    }

}