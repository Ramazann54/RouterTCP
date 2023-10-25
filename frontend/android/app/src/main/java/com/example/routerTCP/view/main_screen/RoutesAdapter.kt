package com.example.routerTCP.view.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routerTCP.R
import com.example.routerTCP.presenter.MainScreenPresenter

class RoutesAdapter(private val presenter: MainScreenPresenter):
    RecyclerView.Adapter<RoutesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.route_fragment, parent)

        return RoutesViewHolder(presenter, view)
    }

    override fun onBindViewHolder(holder: RoutesViewHolder, position: Int) {
        holder.onBind()
        presenter.onBindViewItem()
    }

    override fun onViewRecycled(holder: RoutesViewHolder){
        super.onViewRecycled(holder)
        holder.onCleanUp()
    }

    override fun getItemCount(): Int {
        //todo заглушки для сервера
        return 4;
    }
}