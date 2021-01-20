package com.bearslovebeer.tifitiappp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.WebViewActivity
import com.bearslovebeer.tifitiappp.models.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val newsList: List<News>):RecyclerView.Adapter<NewsAdapter.NewsHolder>() { //El ADAPTER es quien se encarga de coger la información y distribuirla en los items para que el recycleview los pinte.

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context

        return NewsHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int =  newsList.size // Le pregunta al recyclerview: ¿Cuantos items tiene?

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.render(newsList[position], context)
    }

    class NewsHolder(val view: View):RecyclerView.ViewHolder(view) {

        fun render(newsList: News, context: Context) {
            view.newsTitle.text = newsList.title
            view.newsDescription.text = newsList.description
            Picasso.get().load(newsList.imgUrl).into(view.newsBanner)
            view.newsDate.text = newsList.date


            view.setOnClickListener{
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("WEB_PAGE", newsList.url);
                context.startActivity(intent)

            }

        }


    }
}

