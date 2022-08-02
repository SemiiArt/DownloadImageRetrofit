package com.tuyenhoang.callapiretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuyenhoang.callapiretrofit.databinding.ItemImageBinding
import com.tuyenhoang.callapiretrofit.model.Image

class ListImageAdapter(private var iImage: IImage) :
    RecyclerView.Adapter<ListImageAdapter.ListImageViewHolder>() {
    class ListImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
    interface IImage {
        fun getCount(): Int
        fun getItem(position: Int): Image
        fun setClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListImageViewHolder {
        return ListImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListImageViewHolder, position: Int) {
        holder.binding.data = iImage.getItem(position)
        holder.itemView.setOnClickListener {
            iImage.setClick(position)
        }
    }

    override fun getItemCount(): Int {
        return iImage.getCount()
    }

}