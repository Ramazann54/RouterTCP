package com.example.routerTCP.view.main.main_screen

import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.routerTCP.R
import com.example.routerTCP.model.objects.ConnectionStatus
import com.example.routerTCP.model.objects.Route
import com.example.routerTCP.presentation.main.MainScreenPresenter


// @Suppress("UNUSED_EXPRESSION")
class RoutesViewHolder(private val presenter: MainScreenPresenter, view: View) :
    RecyclerView.ViewHolder(view) {
    fun onBind(route: Route) {
        ipAddressTextView = itemView.findViewById(R.id.ipAddress)
        tcpPortTextView = itemView.findViewById(R.id.tcpPort)
        serialNumberTextView = itemView.findViewById(R.id.serialNumber)
        connectWithDevice = itemView.findViewById(R.id.connectWithDevice)
        noConnectWithDevice = itemView.findViewById(R.id.noConnectWithDevice)
        connectWithDCS = itemView.findViewById(R.id.connectWithDCS)
        noConnectWithDCS = itemView.findViewById(R.id.noConnectWithDCS)
        setRoutes(route)

        itemView.setOnLongClickListener {
            showPopup(itemView)
            true
        }
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(itemView.context, view)
        popup.inflate(R.menu.popupmenu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.delete -> {
                    Toast.makeText(itemView.context, item.title, Toast.LENGTH_SHORT).show()
                    //Dialog
                }
                R.id.edit -> {
                    presenter.onItemClick(adapterPosition)
                }
            }
            return@OnMenuItemClickListener true
        })
        popup.show()
    }




    private fun setRoutes(route: Route) {
        ipAddressTextView.text = route.ipAddress
        tcpPortTextView.text = route.tcpPort.toString()
        serialNumberTextView.text = route.serialNumber.toString()
        if (route.connectionStatus === ConnectionStatus.ConnectWithDevice) {
            connectWithDevice.visibility = View.VISIBLE
        }
        if (route.connectionStatus === ConnectionStatus.NoConnectWithDevice) {
            noConnectWithDevice.visibility = View.VISIBLE
        }
        if (route.connectionStatus === ConnectionStatus.ConnectWithDCS) {
            connectWithDCS.visibility = View.VISIBLE
        }
        if (route.connectionStatus === ConnectionStatus.NoConnectWithDCS) {
            noConnectWithDCS.visibility = View.VISIBLE
        }
    }



    fun onCleanUp() {
        itemView.setOnClickListener(null)
    }




    private lateinit var ipAddressTextView: TextView
    private lateinit var tcpPortTextView: TextView
    private lateinit var serialNumberTextView: TextView

    // ImageView - connection status
    private lateinit var connectWithDevice: ImageView
    private lateinit var noConnectWithDevice: ImageView
    private lateinit var connectWithDCS: ImageView
    private lateinit var noConnectWithDCS: ImageView
    }


