package com.post.views.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.post.MyApplication
import com.post.R
import com.post.databinding.ActivityMainBinding

import com.post.interfaces.OnItemClickListener
import com.post.models.PostModel
import com.post.utils.BaseActivity
import com.post.utils.Constants
import com.post.viewModels.MainActivityFactory
import com.post.viewModels.MainActivityViewModel
import com.post.views.adapters.PostAdapter
import javax.inject.Inject


class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainActivityFactory: MainActivityFactory
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    private fun init() {
        injectDagger()
        initializeViewModel()
        setListener()
        initializeObserver()
    }

    private fun injectDagger() {
        MyApplication.app().appComponent.injectFields(this)
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, mainActivityFactory)[MainActivityViewModel::class.java]
    }

    private fun initializeObserver() {
        viewModel?.getPosts(mAct)!!.observe(this, Observer {
            setAdapter(it)
        })
    }

    private fun setListener() {
        binding.addPost.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == binding.addPost) {
            singleton.startActivity(mAct, AddPostActivity::class.java, singleton.startActivity)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setAdapter(list: List<PostModel>) {
        var adpater = PostAdapter(mAct, list, object : OnItemClickListener {
            override fun OnClick(model: PostModel?, status: Int) {
                if (status == Constants.NORMAL) {
                    var bundle = Bundle()
                    bundle.putSerializable(singleton.SELECTED_MODEL, model)
                    singleton.startActivityWithDataBundle(
                        mAct,
                        AddPostActivity::class.java, bundle,
                        singleton.startActivity
                    )
                } else if (status == Constants.DELETE) {
                    viewModel.deletePost(model!!)
                }
            }
        })
        binding.recyclerView.adapter = adpater
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                if (swipeDir == ItemTouchHelper.LEFT) {
                    if (viewHolder is PostAdapter.Holder) {
                        viewModel.deletePost(viewHolder.binding.model as PostModel)

                    }
                }
            }
        }

}
