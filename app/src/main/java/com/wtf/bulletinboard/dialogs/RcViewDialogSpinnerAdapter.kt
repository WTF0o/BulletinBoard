package com.wtf.bulletinboard.dialogs

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wtf.bulletinboard.R


class RcViewDialogSpinnerAdapter(val dialog: AlertDialog, val selectTextView: TextView): RecyclerView.Adapter<RcViewDialogSpinnerAdapter.SpViewHolder>() {

    val mainList = ArrayList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sp_list_item , parent, false)
        return SpViewHolder(view, dialog, selectTextView)
    }

    override fun onBindViewHolder(holder: SpViewHolder, position: Int) {
        holder.setData(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    class SpViewHolder(itemView: View, val dialog: AlertDialog, val selectTextView: TextView) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvSpItem = itemView.findViewById<TextView>(R.id.tvSpItem)
        fun setData(text: String){
            tvSpItem.text = text
            tvSpItem.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectTextView.text = tvSpItem.text
            dialog.dismiss()

        }
    }

    fun updateAdapter(list: ArrayList<String>){
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }

}