package com.blogapps.ui.createupdate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.blogapps.R
import com.blogapps.utils.NetworkState
import com.blogapps.utils.toast
import com.core.model.data.BlogPost
import kotlinx.android.synthetic.main.activity_create_update.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.blogapps.utils.ObjectWrapperForBinder
import com.core.model.data.BlogDataItem


class CreateUpdateActivity : AppCompatActivity() {
    private val viewModel:CreateUpdateViewModel by viewModel()
    var idBlog:Int = 0
    var updateBlog:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update)

         updateBlog = intent.getBooleanExtra("UPDATE", false)
        if (updateBlog) {
            val blogDataItem: BlogDataItem = (intent.extras?.getBinder("object_value") as ObjectWrapperForBinder?)?.data as BlogDataItem
            initView(blogDataItem)
        }
        observerLiveData()
        initOnClick()
    }

    private fun initOnClick() {
        btnSubmit.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val content = edtContent.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {
                toast("Lengkapi data")
            } else {
                if (updateBlog)viewModel.update(idBlog,BlogPost(content,title))
                else viewModel.create(BlogPost(content,title))
            }
        }
    }
    private fun initView(data:BlogDataItem) {
        edtTitle.setText(data.title)
        edtContent.setText(data.content)
        idBlog = data.id
    }

    private fun observerLiveData() {
        viewModel.networkState.observe(this, Observer {
            when (it) {
                NetworkState.LOADED ->{
                    finish()
                }
                NetworkState.LOADING -> {
                    stateButtonSubmit("Loading",false)
                }
                NetworkState.ERROR -> {
                    stateButtonSubmit("Submit",true)
                    toast(it.message)
                }
            }
        })
    }

    private fun stateButtonSubmit(title:String,enable:Boolean) {
        btnSubmit.isEnabled = enable
        btnSubmit.text = title
    }
}