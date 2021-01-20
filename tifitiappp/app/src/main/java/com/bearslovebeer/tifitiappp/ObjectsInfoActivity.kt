package com.bearslovebeer.tifitiappp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class ObjectsInfoActivity : AppCompatActivity() {

    private lateinit var titleTV: TextView
    private lateinit var imgUrlIV: ImageView
    private lateinit var descriptionTV: TextView
    private lateinit var cerrarBT: Button
    private lateinit var passiveTV: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objectsinfo)

        // Initi Views
        initViews()

        val webPage = intent.getStringExtra("WEB_PAGE")

        titleTV.text = intent.getStringExtra("TITLE")
        Picasso.get().load(intent.getStringExtra("IMG_URL")).into(imgUrlIV)
        descriptionTV.text = intent.getStringExtra("DESCRIPTION")
        passiveTV.text = intent.getStringExtra("PASSIVE")

        cerrarBT.setOnClickListener {
            finish()
        }
    }


    private fun initViews() {
        titleTV = findViewById(R.id.title_tv)
        imgUrlIV = findViewById(R.id.url_img)
        descriptionTV = findViewById(R.id.description_tv)
        cerrarBT = findViewById(R.id.cerrar_bt)
        passiveTV = findViewById(R.id.pasiva_tv)
    }
}
