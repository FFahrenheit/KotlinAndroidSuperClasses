package com.example.secondapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondapp.R

class DogAdapter(
    private val source : ArrayList<DogModel>
) : RecyclerView.Adapter<DogAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.dogName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val dogView = inflater.inflate(R.layout.dog_card, parent, false)
        return ViewHolder(dogView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = source[position]
        val textView = holder.nameTextView
        textView.text = dog.name
    }

    override fun getItemCount(): Int {
        return source.size
    }
}