package com.post.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.post.models.PostModel
import com.post.repository.PostRepository
import com.post.utils.BaseViewModel


class MainActivityViewModel
    (var postRepositry: PostRepository) : BaseViewModel() {
    val postList = MutableLiveData<List<PostModel>>().apply {
        value = emptyList()
    }

    val dataUpdated = MutableLiveData<Boolean>()


    fun insertData(ctx: Context, model: PostModel) {
        postRepositry.insertData(model)
        dataUpdated.postValue(true)
    }

    fun updateData(context: Context, postModel: PostModel) {
        postRepositry.updateData(postModel)
        dataUpdated.postValue(true)
    }

    fun getPosts(context: Context): LiveData<List<PostModel>> {
        var list = postRepositry.getPostData()!!
        return list
    }


    fun deletePost(post : PostModel) {
       postRepositry.deletePost(post)!!

    }

}