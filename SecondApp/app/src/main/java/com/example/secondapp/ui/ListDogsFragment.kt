package com.example.secondapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.secondapp.R
import com.example.secondapp.data.DogAdapter
import com.example.secondapp.data.DogModel
import com.example.secondapp.data.DogViewModel
import com.google.android.material.snackbar.Snackbar


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

        adapter = DogAdapter(dogs.value!!, requireContext())
        dogsRecyclerView.adapter = adapter
        dogsRecyclerView.layoutManager = LinearLayoutManager(this.context)

        newDogButton.setOnClickListener(){
            Toast.makeText(context, "Dog added", Toast.LENGTH_LONG).show()

            val uri = "https://dog.ceo/api/breeds/image/random/fetch"
            val request = JsonObjectRequest(
                Request.Method.GET,
                uri,
                null,
                {
                    val dog = DogModel(
                        "Perro ${dogs.value!!.size + 1}",
                        it.getJSONArray("message").getString(0)
                    )
                    dogsViewModel.addDog(dog)
                    adapter.notifyItemInserted(dogs.value!!.size)

                },
                {
                    Snackbar.make(newDogButton, "That didn't work...", Snackbar.LENGTH_LONG).show()
                }
            )
            val queue = Volley.newRequestQueue(requireContext())
            queue.add(request)
        }
    }
}