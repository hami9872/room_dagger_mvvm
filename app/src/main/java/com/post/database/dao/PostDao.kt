package com.post.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.post.models.PostModel

@Dao
interface PostDao {
    @Query("SELECT * FROM Post ORDER BY ID")
    fun loadAllPersons(): List<PostModel?>?

    @Query("SELECT * FROM Post ORDER BY ID")
    fun loadAllPosts() : LiveData<List<PostModel>>?

    @Insert
    fun insertPerson(postModel: PostModel?)

    @Update
    fun updatePerson(postModel: PostModel?)

    @Delete
    fun delete(postModel: PostModel?)

    @Query("SELECT * FROM Post WHERE id = :id")
    fun loadPersonById(id: Int): PostModel?
}