package com.blogapps.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogapps.R
import com.blogapps.ui.detail.DetailActivity
import com.core.model.data.BlogDataItem
import kotlinx.android.synthetic.main.item_view_home.view.*
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import com.blogapps.ui.createupdate.CreateUpdateActivity
import androidx.core.content.ContextCompat.startActivity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.blogapps.utils.ObjectWrapperForBinder


class HomeAdapter (private val data: List<BlogDataItem>, private val context: Context?,private val delete:(BlogDataItem)->Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_view_home, parent, false
            )
        )

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position],delete)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: BlogDataItem,delete:(BlogDataItem)->Unit) {

            with(itemView) {
                tvTitle.text = data.title
                tvDesc.text = data.content
                containerCv.setOnClickListener {
                    context.startActivity(Intent( context,DetailActivity::class.java).putExtra("ID",data.id))
                }

                imgMenuOption.setOnClickListener {
                    val popup = PopupMenu(context, imgMenuOption)
                    popup.inflate(R.menu.optionmenu)
                    popup.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.update ->  {
                                val bundle = Bundle()
                                bundle.putBinder("object_value", ObjectWrapperForBinder(data))
                                context.startActivity(Intent(context, CreateUpdateActivity::class.java).putExtras(bundle).putExtra("UPDATE",true))
                                true
                            }
                            R.id.delete ->  {
                                delete(data)
                                true
                            }
                            else -> false
                        }
                    }
                    popup.show()
                }
            }


        }
    }

}