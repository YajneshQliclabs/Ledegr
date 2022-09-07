package com.example.ledger


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*
class CustomAdapter(
    private var context: Context,
    private var Date: ArrayList<String>,
    private var Particular: ArrayList<String>,
    private var Amount: ArrayList<String>,
    private var Dr: ArrayList<String>,
    private var Cr: ArrayList<String>,

    ) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return  MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        holder.Date.text = Date[position]
        holder.Particular.text = Particular[position]
        holder.Amount.text = Amount[position]
        holder.Dr.text = Dr[position]
        holder.Cr.text = Cr[position]

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener { // display a toast with person name on item click
            Toast.makeText(context, Particular[position], Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return Particular.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var Date: TextView = itemView.findViewById(R.id.tvdate) as TextView
        var Particular: TextView = itemView.findViewById(R.id.tvParticular) as TextView
        var Amount: TextView = itemView.findViewById(R.id.tvAmount) as TextView
        var Dr: TextView = itemView.findViewById(R.id.tvDR) as TextView
        var Cr: TextView = itemView.findViewById(R.id.tvCR) as TextView

    }
}