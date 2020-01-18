package com.rhm.mysubmission03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val mData = ArrayList<MovieItems>()

    fun setData(items: ArrayList<MovieItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MovieViewHolder {
        val mView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return MovieViewHolder(mView)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        movieViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieItems: MovieItems) {
            with(itemView) {
                tv_item_title.text = movieItems.title
                tv_item_date.text = movieItems.date_release
                tv_item_description.text = movieItems.description
            }
        }
    }
}