package com.example.secondapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondapp.R
import com.squareup.picasso.Picasso

class DogAdapter(
    var source : ArrayList<DogModel>,
    var context : Context,
    var listener : OnDogCardClick
) : RecyclerView.Adapter<DogAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.dogName)
        val photoImageView = itemView.findViewById<ImageView>(R.id.dogImage)
        val renameButton = itemView.findViewById<Button>(R.id.renameButton)
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
        val imageView = holder.photoImageView
        textView.text = dog.name
        Picasso.with(context).load(dog.image).into(imageView)
        holder.renameButton.setOnClickListener(){
            listener.onEditClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return source.size
    }
}