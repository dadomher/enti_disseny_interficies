package com.bearslovebeer.tifitiappp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val newsList: List<News>):RecyclerView.Adapter<NewsAdapter.NewsHolder>() { //El ADAPTER es quien se encarga de coger la información y distribuirla en los items para que el recycleview los pinte.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int =  newsList.size // Le pregunta al recyclerview: ¿Cuantos items tiene?


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.render(newsList[position])
    }

    class NewsHolder(val view: View):RecyclerView.ViewHolder(view) {

        fun render(newsList: News) {
            view.newsTitle.text = newsList.title
            view.newsDescription.text = newsList.description
            Picasso.get().load(newsList.imgUrl).into(view.newsBanner)
        }
    }
}