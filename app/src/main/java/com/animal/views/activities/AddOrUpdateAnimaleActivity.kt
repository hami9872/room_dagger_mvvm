package com.animal.views.activities


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.animal.MyApplication
import com.animal.R
import com.animal.databinding.ActivityAddOrUpdateAnimaleBinding
import com.animal.models.AnimalModel
import com.animal.utils.BaseActivity
import com.animal.viewModels.MainActivityFactory
import com.animal.viewModels.MainActivityViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import java.util.*
import javax.inject.Inject


class AddOrUpdateAnimaleActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddOrUpdateAnimaleBinding
    private var isUpdate = false
    private var imageUri: Uri? = null
    private var animalModel: AnimalModel? = null

    var myCalendar = Calendar.getInstance()

    @Inject
    lateinit var mainActivityFactory: MainActivityFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_or_update_animale)
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
        animalModel = intent.getSerializableExtra(singleton.SELECTED_MODEL) as? AnimalModel
        binding.headerV.tickIV.visibility = View.VISIBLE
        if (animalModel != null) {
            isUpdate = true
            binding.headerV.titleTV.text = getString(R.string.update)
        } else {
            binding.headerV.titleTV.text = getString(R.string.add)
            binding.deleteBT.visibility = View.GONE
        }
    }


    private fun injectDagger() {
        MyApplication.app().appComponent.injectAddFields(this)
    }

    private fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, mainActivityFactory)[MainActivityViewModel::class.java]

    }

    private fun setListener() {
        binding.headerV.backFL.setOnClickListener(this)
        binding.imageRL.setOnClickListener(this)
        binding.headerV.menuFL.setOnClickListener(this)
        binding.deleteBT.setOnClickListener(this)
    }

    private fun setData() {
        if (isUpdate) {
            binding.nameET.setText(animalModel?.name)
            binding.breedET.setText(animalModel?.breed)
            binding.ageET.setText(animalModel?.age)
            binding.imageIV.setImageURI(Uri.parse(animalModel?.image))
            binding.genderSP.setSelection(animalModel?.gender!!)
            binding.speciesSp.setSelection(animalModel?.specie!!)
            imageUri = Uri.parse(animalModel?.image)
        }
    }

    private fun initializeObserver() {
        viewModel.dataUpdated.observe(this, androidx.lifecycle.Observer {
            if (it) {
                singleton.finishActivity(mAct)
            }
        })
    }

    override fun onClick(v: View?) {
        if (v == binding.headerV.backFL) {
            onBackPressed()
        } else if (v == binding.imageRL) {
            ImagePicker.with(this)
                .crop()
                .compress(400)
                .maxResultSize(300, 300)
//                .crop(3f, 2f)
                .start()
        } else if (v == binding.headerV.menuFL) {
            validation()
        } else if (v == binding.deleteBT) {
            showAlertDialog()
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
            binding.imageIV.setImageURI(imageUri)
//            binding.fabChoosePic.visibility = View.GONE
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validation() {
        if (binding.nameET.text.toString().isEmpty()) {
            binding.nameET.error = getString(R.string.please_enter_the_name)
        } else if (binding.ageET.text.toString().isEmpty()) {
            binding.ageET.error = getString(R.string.please_enter_the_age)
        } else if (binding.breedET.text.toString().isEmpty()) {
            binding.ageET.error = getString(R.string.please_enter_the_breed)
        } else if (imageUri == null) {
            Toast.makeText(mAct, getString(R.string.please_enter_the_image), Toast.LENGTH_SHORT).show()
        } else {
            if (animalModel == null)
                animalModel = AnimalModel()
            animalModel?.name = binding.nameET.text.toString()
            animalModel?.age = binding.ageET.text.toString()
            animalModel?.breed = binding.breedET.text.toString()
            animalModel?.gender = binding.genderSP.selectedItemPosition
            animalModel?.specie = binding.speciesSp.selectedItemPosition
            animalModel?.image = imageUri.toString()
            addOrUpdate(animalModel)
        }
    }


    private fun addOrUpdate(animalModel: AnimalModel?) {
        if (!isUpdate) {
            viewModel.insertData(animalModel!!)
        } else {
            viewModel.updateData(animalModel!!)
        }
    }

    private fun showAlertDialog() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(mAct)
        builder1.setMessage(getString(R.string.are_sure_you_want_to_delete))
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener(fun(dialog: DialogInterface, id: Int) {
                viewModel.deleteData(animalModel!!)
                dialog.cancel()
            })
        )

        builder1.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

}



