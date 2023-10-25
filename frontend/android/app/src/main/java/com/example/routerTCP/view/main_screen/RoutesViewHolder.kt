package com.example.routerTCP.view.main_screen

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.routerTCP.R
import com.example.routerTCP.presenter.MainScreenPresenter

class RoutesViewHolder(val presenter: MainScreenPresenter, view: View) :
    RecyclerView.ViewHolder(view),
    View.OnClickListener{

    fun onBind(){
        ipAddressTextView = itemView.findViewById(R.id.ipAddress)
        tcpPortTextView = itemView.findViewById(R.id.tcpPort)
        serialNumberTextView = itemView.findViewById(R.id.serialNumber)
        itemView.setOnClickListener(this)
    }



    override fun onClick(view: View?) {
        if(view === itemView){
            presenter.onItemClick(adapterPosition)
        }
    }


    fun onCleanUp(){
        itemView.setOnClickListener(null)
    }

    private lateinit var ipAddressTextView: TextView
    private lateinit var tcpPortTextView: TextView
    private lateinit var serialNumberTextView: TextView
    private lateinit var statusImageView: ImageView


}


