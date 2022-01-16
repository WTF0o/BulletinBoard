package com.wtf.bulletinboard.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wtf.bulletinboard.R
import com.wtf.bulletinboard.utils.CityHelper

class DialogSpinnerHelper {

    fun showSpinnerDialog(context: Context, list: ArrayList<String>, selectTextView: TextView) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        val rootView = LayoutInflater.from(context).inflate(R.layout.spinner_layout, null)
        val adapter = RcViewDialogSpinnerAdapter(dialog, selectTextView)
        val rcView = rootView.findViewById<RecyclerView>(R.id.rcSpinnerView)
        val rvView = rootView.findViewById<SearchView>(R.id.svSearchSpinner)

        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter
        dialog.setView(rootView)

        adapter.updateAdapter(list)
        setSearchViewListener(adapter, rvView, list)

        dialog.show()
    }

    private fun setSearchViewListener(adapter: RcViewDialogSpinnerAdapter, rvView: SearchView?, list: ArrayList<String>) {
        rvView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val tempArray = CityHelper.filterListData(list, newText)
                adapter.updateAdapter(tempArray)
                return true
            }
        })
    }

}