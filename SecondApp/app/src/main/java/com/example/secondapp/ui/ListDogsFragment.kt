package com.example.secondapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondapp.R
import com.example.secondapp.data.DogAdapter
import com.example.secondapp.data.DogModel

class ListDogsFragment : Fragment(R.layout.fragment_list_dogs) {
    private lateinit var newDogButton : Button
    private lateinit var dogsRecyclerView : RecyclerView
    var dogList = ArrayList<DogModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newDogButton = view.findViewById(R.id.newDogButton)
        dogsRecyclerView = view.findViewById(R.id.listDogRecyclerView)

        val adapter = DogAdapter(dogList)
        dogsRecyclerView.adapter = adapter
        dogsRecyclerView.layoutManager = LinearLayoutManager(this.context)

        newDogButton.setOnClickListener(){
            Toast.makeText(context, "Dog added", Toast.LENGTH_LONG).show()
            dogList.add(DogModel("Perro #${dogList.size + 1}"))
            adapter.notifyItemInserted(dogList.size)
        }
    }
}