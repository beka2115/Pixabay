package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabay.databinding.ItemSearchBinding
import com.example.pixabay.models.ImageModel

class PixabayAdapter(var list: ArrayList<ImageModel>) :
    RecyclerView.Adapter<PixabayAdapter.PixabayViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        return PixabayViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        holder.onBind(list.get(position))

    }

    override fun getItemCount(): Int {
       return list.size
    }


    class PixabayViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.pixaImage.load(model.largeImageURL)
        }
    }

}