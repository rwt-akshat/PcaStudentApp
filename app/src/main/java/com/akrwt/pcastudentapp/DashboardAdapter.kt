package com.akrwt.pcastudentapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardAdapter(var context: Context,private var mList:MutableList<ModelClass>) :
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val title = itemView.findViewById<TextView>(R.id.titleTV)
        internal val summary = itemView.findViewById<TextView>(R.id.summaryTV)
        internal val date = itemView.findViewById<TextView>(R.id.dateTV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.announcement_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = mList[position]
        holder.title.text = current.title
        holder.summary.text = current.message
        holder.date.text = current.date
    }
}