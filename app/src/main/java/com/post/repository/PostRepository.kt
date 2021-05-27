package com.post.repository

import androidx.lifecycle.LiveData
import com.post.database.MyDatabase
import com.post.database.dao.PostDao
import com.post.models.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostRepository @Inject constructor(postDao: PostDao) {
    var postDao = postDao
    var postList: LiveData<List<PostModel>>? = null

    fun insertData( postModel: PostModel) {
        CoroutineScope(IO).launch {
            postDao?.insertPerson(postModel)
        }
    }

    fun updateData(postModel: PostModel) {
        CoroutineScope(IO).launch {
            postDao?.updatePerson(postModel)
        }
    }

    fun getPostData(): LiveData<List<PostModel>> {
        postList = postDao?.loadAllPosts()
        return postList!!
    }

    fun deletePost(post: PostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao?.delete(post)
        }
    }

}