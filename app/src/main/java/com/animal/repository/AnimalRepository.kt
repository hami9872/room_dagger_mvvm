package com.animal.repository

import androidx.lifecycle.LiveData
import com.animal.database.dao.AnimalDao
import com.animal.models.AnimalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalRepository @Inject constructor(animalDao: AnimalDao) {
    var postDao = animalDao
    var animalList: LiveData<List<AnimalModel>>? = null

    fun insertData(animalModel: AnimalModel) {
        CoroutineScope(IO).launch {
            postDao?.insertAnimal(animalModel)
        }
    }

    fun updateData(animalModel: AnimalModel) {
        CoroutineScope(IO).launch {
            postDao?.updateAnimal(animalModel)
        }
    }

    fun updateData(animalID : Int , isFav : Boolean  ) {
        CoroutineScope(IO).launch {
            postDao?.updateAnimal(animalID , isFav)
        }
    }

    fun getPostData(): LiveData<List<AnimalModel>> {
        animalList = postDao?.loadAllAnimal()
        return animalList!!
    }

    fun deletePost(animal: AnimalModel) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao?.deleteAnimal(animal)
        }
    }

}