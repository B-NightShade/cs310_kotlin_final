package com.example.cs310_kotlin_final

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val data: MutableList<Workout>, val itemClickListener: ItemClickListener):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(position:Int)
    }

    inner class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewName: TextView = itemView.findViewById(R.id.textViewExcersiseName)
        val textViewReps: TextView = itemView.findViewById(R.id.textViewExcerciseReps)
        val textViewDone: TextView = itemView.findViewById(R.id.textViewDone)
        init{
            itemView.setOnClickListener {
                if(adapterPosition >= 0){
                    itemClickListener.onItemClick(adapterPosition)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.workout_layout, parent, false)
        return ItemViewHolder(listItemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textViewDone.text = data[position].complete
        holder.textViewReps.text = data[position].reps.toString()
        holder.textViewName.text = data[position].name

        if(data[position].complete == "Completed"){
            holder.textViewDone.setBackgroundColor(Color.GREEN)
            holder.textViewDone.setTextColor(Color.BLACK)
        }else if(data[position].complete == "Incomplete"){
            holder.textViewDone.setBackgroundColor(Color.DKGRAY)
            holder.textViewDone.setTextColor(Color.WHITE)
        }

    }
}