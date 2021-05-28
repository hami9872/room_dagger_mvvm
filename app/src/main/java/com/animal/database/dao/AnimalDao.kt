package com.animal.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.animal.models.AnimalModel

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal ORDER BY ID")
    fun loadAllAnimals(): List<AnimalModel?>?

    @Query("SELECT * FROM animal ORDER BY ID")
    fun loadAllAnimal(): LiveData<List<AnimalModel>>?

    @Insert
    fun insertAnimal(animalModel: AnimalModel?)

    @Update
    fun updateAnimal(animalModel: AnimalModel?)

    @Query("UPDATE animal SET fav = :isFav WHERE id =:animalID")
    fun updateAnimal(animalID: Int , isFav: Boolean)

    @Delete
    fun deleteAnimal(animalModel: AnimalModel?)

    @Query("SELECT * FROM animal WHERE id = :id")
    fun loadAnimalById(id: Int): AnimalModel?
}