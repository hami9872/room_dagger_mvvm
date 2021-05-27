package com.post.views.activities


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.dhaval2404.imagepicker.ImagePicker
import com.post.MyApplication
import com.post.R
import com.post.models.PostModel
import com.post.databinding.ActivityAddPostBinding
import com.post.utils.BaseActivity
import com.post.viewModels.MainActivityFactory
import com.post.viewModels.MainActivityViewModel
import javax.inject.Inject


class AddPostActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddPostBinding
    private var isUpdate = false
    private var imageUri: Uri? = null
    private var postModel: PostModel? = null

    @Inject
    lateinit var mainActivityFactory: MainActivityFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_post)
        init()
    }

    private fun init() {
        initializeData()
        injectDagger()
        initializeViewModel()
        setListener()
        setData()
        initializeObserver()
    }

    private fun initializeData() {
        postModel = intent.getSerializableExtra(singleton.SELECTED_MODEL) as? PostModel
        if (postModel != null)
            isUpdate = true
    }

    private fun injectDagger() {
        MyApplication.app().appComponent.injectAddFields(this)
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, mainActivityFactory)[MainActivityViewModel::class.java]

    }

    private fun setListener() {
        binding.imageCL.setOnClickListener(this)
        binding.addBT.setOnClickListener(this)
        binding.imageView.setOnClickListener(this)
    }

    private fun setData() {
        if (isUpdate) {
            binding.titleTV.setText(postModel?.title)
            binding.descTV.setText(postModel?.desc)
            binding.imageView.setImageURI(Uri.parse(postModel?.image))
            imageUri = Uri.parse(postModel?.image)
            binding.addBT.text = "Update"
        }
    }

    private fun initializeObserver() {
        viewModel.dataUpdated.observe(this, Observer {
            if (it) {
                singleton.finishActivity(mAct)
            }
        })
    }

    override fun onClick(v: View?) {
        if (v == binding.imageCL || v == binding.imageView) {
            ImagePicker.with(this)
                .crop()
                .compress(400)
                .maxResultSize(300, 300)
                .crop(3f, 2f)
                .start()
        } else if (v == binding.addBT) {
            validation()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        singleton.finishActivity(mAct)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            imageUri = data?.data!!
//            imageUri = Uri.parse(compressImage(data?.data!!.toString()))
            binding.imageView.setImageURI(imageUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validation() {
        if (binding.titleTV.text.toString().isEmpty()) {
            binding.titleTV.error = "Please enter the title"
        } else if (binding.descTV.text.toString().isEmpty()) {
            binding.descTV.error = "Please enter the Description"
        } else if (imageUri == null) {
            Toast.makeText(mAct, "Please Add the image", Toast.LENGTH_SHORT).show()
        } else {
            if (postModel == null)
                postModel = PostModel()
            postModel?.title = binding.titleTV.text.toString()
            postModel?.desc = binding.descTV.text.toString()
            postModel?.image = imageUri.toString()
            addOrUpdate(postModel)
        }
    }

    private fun addOrUpdate(postModel: PostModel?) {
        if (!isUpdate) {
            viewModel.insertData(mAct, postModel!!)
        } else {
            viewModel.updateData(mAct, postModel!!)
        }
    }

}



