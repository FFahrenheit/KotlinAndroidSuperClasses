package com.example.secondapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.secondapp.R
import com.example.secondapp.data.DogModel
import com.example.secondapp.data.DogViewModel
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_POSITION = "position"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewDogModel.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewDogModel : Fragment() {
    private lateinit var textView : TextView
    private var position: Int? = null
    private lateinit var dogsViewModel : DogViewModel
    private lateinit var dogs : MutableLiveData<ArrayList<DogModel>>
    private lateinit var currentDog : DogModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position= it.getInt(ARG_POSITION)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogsViewModel = ViewModelProvider(requireActivity())[DogViewModel::class.java]
        dogs = dogsViewModel.getItem()
        currentDog = dogs.value!![position!!]

        dogs.observe(viewLifecycleOwner){
            currentDog = it[position!!]
        }

        dogsViewModel.getItem().observe(viewLifecycleOwner){
            currentDog = it[position!!]
            Toast.makeText(context, "Data set", Toast.LENGTH_LONG).show()
        }

        textView = view.findViewById(R.id.editDogName)
        textView.text = "${currentDog.name} looks cool"

        textView.setOnClickListener{
            currentDog.name = "Dog ${(position!! + 1)}"
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentComponent, ListDogsFragment())
                .commit()
            Snackbar.make(textView, "Going back!", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_dog_model, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param position Position of the dog in ArrayList
         * @return A new instance of fragment ViewDogModel.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: Int) =
            ViewDogModel().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}