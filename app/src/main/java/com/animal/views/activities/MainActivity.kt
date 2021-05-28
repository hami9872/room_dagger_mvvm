package com.animal.views.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.animal.MyApplication
import com.animal.R
import com.animal.databinding.ActivityMainBinding
import com.animal.interfaces.OnItemClickListener
import com.animal.models.AnimalModel
import com.animal.utils.BaseActivity
import com.animal.utils.Constants
import com.animal.viewModels.MainActivityFactory
import com.animal.viewModels.MainActivityViewModel
import com.animal.views.adapters.AnimalAdapter
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
        initializeValue()
        injectDagger()
        initializeViewModel()
        setListener()
        initializeObserver()
    }

    private fun initializeValue() {
        binding.headerV.backIV.visibility = View.GONE
        binding.headerV.titleTV.text = getString(R.string.my_animals)
    }

    private fun injectDagger() {
        MyApplication.app().appComponent.injectFields(this)
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, mainActivityFactory)[MainActivityViewModel::class.java]
    }

    private fun initializeObserver() {
        viewModel?.getData()!!.observe(this, Observer {
            setAdapter(it)
        })
    }

    private fun setListener() {
        binding.addPost.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == binding.addPost) {
            singleton.startActivity(
                mAct,
                AddOrUpdateAnimaleActivity::class.java,
                singleton.startActivity
            )
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setAdapter(list: List<AnimalModel>) {
        var adpater = AnimalAdapter(mAct, list, object : OnItemClickListener {
            override fun OnClick(model: AnimalModel?, status: Int) {
                if (status == Constants.NORMAL) {
                    var bundle = Bundle()
                    bundle.putSerializable(singleton.SELECTED_MODEL, model)
                    singleton.startActivityWithDataBundle(
                        mAct,
                        AddOrUpdateAnimaleActivity::class.java, bundle,
                        singleton.startActivity
                    )
                } else if (status == Constants.DELETE) {
                    viewModel.deleteData(model!!)
                } else if (status == Constants.FAV) {
                    if (model?.fav!!) {
                        model?.fav = false
                    } else {
                        model?.fav = true
                    }
                    viewModel.updateData(model?.id!!, model.fav!!)
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
                    if (viewHolder is AnimalAdapter.Holder) {
                        viewModel.deleteData(viewHolder.binding.model as AnimalModel)

                    }
                }
            }
        }

}
