package com.bearslovebeer.tifitiappp.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bearslovebeer.tifitiappp.R
import com.squareup.picasso.Picasso

class ObjectsInfoActivity : AppCompatActivity() {

    private lateinit var titleTV: TextView
    private lateinit var imgUrlIV: ImageView
    private lateinit var descriptionTV: TextView
    private lateinit var cerrarBT: Button
    private lateinit var passiveTV: TextView
    private lateinit var subItem1IV: ImageView
    private lateinit var subItem2TV: ImageView
    private lateinit var bonus1IV: ImageView
    private lateinit var bonus2IV: ImageView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objectsinfo)

        // Initi Views
        initViews()

        titleTV.text = intent.getStringExtra("TITLE")
        Picasso.get().load(intent.getStringExtra("IMG_URL")).into(imgUrlIV)
        descriptionTV.text = intent.getStringExtra("DESCRIPTION")
        passiveTV.text = intent.getStringExtra("PASSIVE")
        Picasso.get().load(intent.getStringExtra("ITEM1_URL")).into(subItem1IV)
        Picasso.get().load(intent.getStringExtra("ITEM2_URL")).into(subItem2TV)
        if (intent.getStringExtra("BONUS_1") != "") Picasso.get()
            .load(intent.getStringExtra("BONUS_1")).into(bonus1IV)
        if (intent.getStringExtra("BONUS_2") != "") Picasso.get()
            .load(intent.getStringExtra("BONUS_2")).into(bonus2IV)

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
        subItem1IV = findViewById(R.id.subItem_1)
        subItem2TV = findViewById(R.id.subItem_2)
        bonus1IV = findViewById(R.id.bonus1_img)
        bonus2IV = findViewById(R.id.bonus2_img)
    }
}
