package com.smartdevservice.mystudiofactory.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smartdevservice.mystudiofactory.R
import com.smartdevservice.mystudiofactory.domain.User


import kotlinx.android.synthetic.main.fragment_user_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [User]
 */
class MyUserRecyclerViewAdapter(
    private val context: Context,
    private val mValues: List<User>?,
    private val listener: OnUserListener?
) : RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues?.get(position)
        holder.nameTextView.text = item?.name
        holder.descriptionTextView.text = item?.description
        holder.hiredTextView.text = item?.hired

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(context)
            .load(item?.picture)
            .placeholder(circularProgressDrawable)
            .into(holder.iconImageView)

        holder.view.setOnClickListener {
            item?.let {
                listener?.onUserClick(item)
            }
        }
    }

    override fun getItemCount(): Int = mValues?.size ?: 0

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.user_picture
        val nameTextView: TextView = view.user_name
        val descriptionTextView: TextView = view.user_description
        val hiredTextView: TextView = view.user_hired

        override fun toString(): String {
            return "Title = ${nameTextView.text}"
        }
    }
}
