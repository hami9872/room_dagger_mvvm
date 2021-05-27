package com.post.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "post")
class PostModel() : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "desc")
    var desc: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "fav")
    var fav: Int? = null
}