package com.post.views.adapters

import android.app.Activity
import android.net.Uri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.post.R
import com.post.models.PostModel
import com.post.databinding.ItemPostBinding
import com.post.interfaces.OnItemClickListener
import com.post.utils.Constants

class PostAdapter(
    val mAct: Activity,
    val list: List<PostModel>,
    var listener: OnItemClickListener,

    ) : RecyclerView.Adapter<PostAdapter.Holder>() {
    var selectedPos = 0;
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
//        var view=LayoutInflater.from(parent.context).inflate(R.layout.menu_item,parent,false)
        val binding: ItemPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post, parent, false
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
        holder.binding.root.setOnClickListener(View.OnClickListener {
            listener.OnClick(model,Constants.NORMAL)
        })

        holder.binding.deleteIV.setOnClickListener(View.OnClickListener {
            listener.OnClick(model,Constants.DELETE)
        })
    }

    inner class Holder(itemView: ItemPostBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }
}