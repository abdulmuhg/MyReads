package com.abdulmughni.personal.myreads.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulmughni.personal.myreads.R
import com.abdulmughni.personal.myreads.core.domain.model.Book
import com.abdulmughni.personal.myreads.databinding.AdapterBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BookAdapter (
    private val actionIsRead: (Book) -> Unit,
    private val actionCurrentRead: (Book) -> Unit,
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var data = ArrayList<Book>()

    fun setData(popularMovies: List<Book>?) {
        if (popularMovies == null) return
        data.clear()
        data.addAll(popularMovies)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvType.text = data[position].category
            tvTitle.text = data[position].title
            tvRating.text = data[position].rating.toString()
            tvTotalEpisode.text = data[position].releaseDate
            Glide.with(holder.itemView.context)
                .load(data[position].poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imgCover)

            btnRead.setOnClickListener {
                actionIsRead(data[position])
            }
            btnCurrentRead.setOnClickListener {
                actionCurrentRead(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterBookBinding) : RecyclerView.ViewHolder(view.root)


}