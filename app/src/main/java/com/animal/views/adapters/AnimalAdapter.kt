package com.animal.views.adapters

import android.app.Activity
import android.net.Uri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.animal.R
import com.animal.databinding.ItemAnimalBinding
import com.animal.models.AnimalModel
import com.animal.interfaces.OnItemClickListener
import com.animal.utils.Constants

class AnimalAdapter(
    val mAct: Activity,
    val list: List<AnimalModel>,
    var listener: OnItemClickListener,

    ) : RecyclerView.Adapter<AnimalAdapter.Holder>() {
    var selectedPos = 0;
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val binding: ItemAnimalBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_animal, parent, false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        var model = list.get(pos)
        holder.binding.setVariable(BR.model, model)
        holder.binding.imageView.setImageURI(Uri.parse(model.image))
        if (model?.fav!!)
            holder.binding.favIV.isSelected = true
        holder.binding.mainCL.setOnClickListener(View.OnClickListener {
            listener.OnClick(model, Constants.NORMAL)
        })

        holder.binding.deleteIV.setOnClickListener(View.OnClickListener {
            listener.OnClick(model, Constants.DELETE)
        })

        holder.binding.favIV.setOnClickListener(View.OnClickListener {
            if (holder.binding.favIV.isSelected) {
                holder.binding.favIV.isSelected = false
            } else {
                holder.binding.favIV.isSelected = true
            }
            listener.OnClick(model, Constants.FAV)
        })
    }

    inner class Holder(itemView: ItemAnimalBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }
}