package com.example.secondapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondapp.R
import com.example.secondapp.data.DogAdapter
import com.example.secondapp.data.DogModel
import com.example.secondapp.data.DogViewModel

class ListDogsFragment : Fragment(R.layout.fragment_list_dogs) {
    private lateinit var newDogButton : Button
    private lateinit var dogsRecyclerView : RecyclerView
    private lateinit var dogsViewModel : DogViewModel
    private lateinit var dogs : MutableLiveData<ArrayList<DogModel>>
    private lateinit var adapter : DogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogsViewModel = ViewModelProvider(requireActivity())[DogViewModel::class.java]
        dogs = dogsViewModel.getItem()
        dogs.observe(viewLifecycleOwner){
            adapter.source = it
        }

        dogsViewModel.getItem().observe(viewLifecycleOwner){
            adapter.source = it
            Toast.makeText(context, "Data set", Toast.LENGTH_LONG).show()
        }
        newDogButton = view.findViewById(R.id.newDogButton)
        dogsRecyclerView = view.findViewById(R.id.listDogRecyclerView)

        adapter = DogAdapter(dogs.value!!)
        dogsRecyclerView.adapter = adapter
        dogsRecyclerView.layoutManager = LinearLayoutManager(this.context)

        newDogButton.setOnClickListener(){
            Toast.makeText(context, "Dog added", Toast.LENGTH_LONG).show()
            dogsViewModel.addDog(DogModel("Perro ${dogs.value!!.size + 1}"))
            adapter.notifyItemInserted(dogs.value!!.size)
        }
    }
}