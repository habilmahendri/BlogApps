package com.blogapps.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogapps.R
import com.blogapps.ui.adapter.HomeAdapter
import com.blogapps.ui.createupdate.CreateUpdateActivity
import com.blogapps.utils.NetworkState.Companion.ERROR
import com.blogapps.utils.NetworkState.Companion.LOADED
import com.blogapps.utils.NetworkState.Companion.LOADING
import com.blogapps.utils.toast
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        observerLiveData()
        initOnClick()
    }

    override fun onResume() {
        super.onResume()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.home()
    }

    private fun observerLiveData() {
        viewModel.home.observe(this, Observer {
            rvHome.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = HomeAdapter(it, context){
                    viewModel.deleteBlog(it.id)
                }
            }
        })

        viewModel.deleteBlog.observe(this, Observer {
            initViewModel()
        })
        viewModel.networkState.observe(this, Observer {
            when (it) {
                LOADED ->{
                    swipeRefresh.isRefreshing = false
                }
                LOADING -> {
                    swipeRefresh.isRefreshing = true

                }
                ERROR -> {
                    toast(it.message)
                    swipeRefresh.isRefreshing = false
                }
            }
        })
    }

    private fun initOnClick() {
        fab.setOnClickListener {
            startActivity(Intent(this,CreateUpdateActivity::class.java))
        }

        swipeRefresh.setOnRefreshListener {
            initViewModel()
        }
    }
}