package com.post.interfaces

import com.post.models.PostModel

interface OnItemClickListener {
    fun OnClick( model: PostModel? , status : Int)
}