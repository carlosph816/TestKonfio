package com.example.testlistdogskonfio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testlistdogskonfio.R
import com.example.testlistdogskonfio.data.model.ListDogsModel
import com.example.testlistdogskonfio.data.model.response.Dog
import com.example.testlistdogskonfio.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class ListDogsAdapter(private val listDogs : ListDogsModel) : RecyclerView.Adapter<ListDogsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
       val layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= listDogs.listDogs[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listDogs.listDogs.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding =ItemDogBinding.bind(view)

        fun bind(dog: Dog){
            binding.textViewTitle.text = dog.dogName
            binding.textViewDescription.text = dog.description
            "Almost  ${dog.age} years".also { binding.textViewYears.text = it }
            Picasso.get()
                .load(dog.image)
                .into(binding.imageViewDog);
        }
    }

}