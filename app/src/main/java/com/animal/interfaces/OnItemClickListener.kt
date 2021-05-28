package com.animal.interfaces

import com.animal.models.AnimalModel

interface OnItemClickListener {
    fun OnClick(model: AnimalModel?, status : Int)
}