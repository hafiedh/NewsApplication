package com.hafidh.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafidh.core.domain.model.Article
import com.hafidh.newsapp.databinding.ItemDataArticleBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private var listArticle = ArrayList<Article>()
    var onItemClick: ((Article) -> Unit)? = null
    fun setData(newArticle: List<Article>?) {
        if (newArticle == null) return
        listArticle.clear()
        listArticle.addAll(newArticle)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemDataArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.urlToImage)
                    .apply(RequestOptions.overrideOf(150, 150))
                    .centerCrop()
                    .into(ivArticleImage)

                tvSource.text = data.source
                tvTitle.text = data.title
                tvDescription.text = data.description
                tvPublishedAt.text = data.publishedAt
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listArticle[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDataArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listArticle[position])

    override fun getItemCount(): Int = listArticle.size


}