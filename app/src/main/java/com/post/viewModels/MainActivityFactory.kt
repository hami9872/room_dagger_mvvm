package com.post.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.post.repository.PostRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainActivityFactory @Inject constructor(var postRepositry : PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            @Suppress("UNCHECKED_CAST")
            when {
                isAssignableFrom(MainActivityViewModel::class.java) ->
                    MainActivityViewModel(postRepositry) as T
                else -> throw IllegalArgumentException("Unknown View Model Class")
            }
        }

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MainActivityFactory(postRepositry) as T
//    }
}