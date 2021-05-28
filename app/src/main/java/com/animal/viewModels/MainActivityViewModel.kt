package com.animal.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.animal.models.AnimalModel
import com.animal.repository.AnimalRepository
import com.animal.utils.BaseViewModel


class MainActivityViewModel
    (var animalRepositry: AnimalRepository) : BaseViewModel() {

    val dataUpdated = MutableLiveData<Boolean>()

    fun insertData(model: AnimalModel) {
        animalRepositry.insertData(model)
        dataUpdated.postValue(true)
    }

    fun updateData(animalModel: AnimalModel) {
        animalRepositry.updateData(animalModel)
        dataUpdated.postValue(true)
    }

    fun updateData(animalID: Int, isFav: Boolean) {
        animalRepositry.updateData(animalID, isFav)
        dataUpdated.postValue(true)
    }

    fun getData(): LiveData<List<AnimalModel>> {
        var list = animalRepositry.getPostData()!!
        return list
    }

    fun deleteData(animal: AnimalModel) {
        animalRepositry.deletePost(animal)!!
        dataUpdated.postValue(true)
    }


}