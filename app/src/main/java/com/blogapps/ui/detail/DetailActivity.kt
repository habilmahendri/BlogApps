package com.blogapps.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.blogapps.R
import com.blogapps.utils.NetworkState
import com.blogapps.utils.hide
import com.blogapps.utils.show
import com.blogapps.utils.toast
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewModel:DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val idBlog = intent.getIntExtra("ID",0)
        initViewModel(idBlog)
        observerLiveData()

    }

    private fun initViewModel(id:Int) {
        viewModel.detail(id)
    }

    private fun observerLiveData() {
        viewModel.detail.observe(this, Observer {
            tvDesc.text = it.content
            tvTitle.text = it.title
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                NetworkState.LOADED ->{
                    pg.hide()
                }
                NetworkState.LOADING -> {
                    pg.show()
                }
                NetworkState.ERROR -> {
                    toast(it.message)
                    pg.hide()
                }
            }
        })
    }
}