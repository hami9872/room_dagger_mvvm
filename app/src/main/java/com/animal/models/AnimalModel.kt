package com.animal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "animal")
class AnimalModel() : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "age")
    var age: String? = null

    @ColumnInfo(name = "breed")
    var breed: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "fav")
    var fav: Boolean? = false

    @ColumnInfo(name = "gender")
    var gender: Int? = 0

    @ColumnInfo(name = "specie")
    var specie: Int? = 0
}