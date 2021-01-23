package com.bearslovebeer.tifitiappp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bearslovebeer.tifitiappp.ObjectsInfoActivity
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.WebViewActivity
import com.bearslovebeer.tifitiappp.models.ListItems
import com.bearslovebeer.tifitiappp.models.ListItems_v2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_objects.view.*

class ObjectsAdapter (val items: ListItems_v2, val nameObject: Int):RecyclerView.Adapter<ObjectsAdapter.ItemsHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        context = parent.context

        return ItemsHolder(layoutInflater.inflate(R.layout.item_objects, parent, false))
    }


    override fun getItemCount(): Int = items.belt.size

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        when (nameObject) {
            0 -> holder.renderSword(items, position, context)
            1 -> holder.renderBow(items, position, context)
            2 -> holder.renderRod(items, position, context)
            3 -> holder.renderTear(items, position, context)
            4 -> holder.renderVest(items, position, context)
            5 -> holder.renderCoat(items, position, context)
            6 -> holder.renderBelt(items, position, context)
            7 -> holder.renderGloves(items, position, context)
            8 -> holder.renderSpatula(items, position, context)
        }
    }

    class ItemsHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun renderSword(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.sword.get(position).imgUrl).into(view.itemBanner)
            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.sword.get(position).name);
                intent.putExtra("BONUS_1", items.sword.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.sword.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.sword.get(position).passive);
                intent.putExtra("IMG_URL", items.sword.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.sword.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.sword.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.sword.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderBow(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.bow.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.bow.get(position).name);
                intent.putExtra("BONUS_1", items.bow.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.bow.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.bow.get(position).passive);
                intent.putExtra("IMG_URL", items.bow.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.bow.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.bow.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.bow.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderRod(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.rod.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.rod.get(position).name);
                intent.putExtra("BONUS_1", items.rod.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.rod.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.rod.get(position).passive);
                intent.putExtra("IMG_URL", items.rod.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.rod.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.rod.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.rod.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderTear(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.tear.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.tear.get(position).name);
                intent.putExtra("BONUS_1", items.tear.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.tear.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.tear.get(position).passive);
                intent.putExtra("IMG_URL", items.tear.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.tear.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.tear.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.tear.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderVest(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.vest.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.vest.get(position).name);
                intent.putExtra("BONUS_1", items.vest.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.vest.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.vest.get(position).passive);
                intent.putExtra("IMG_URL", items.vest.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.vest.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.vest.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.vest.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderCoat(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.coat.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.coat.get(position).name);
                intent.putExtra("BONUS_1", items.coat.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.coat.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.coat.get(position).passive);
                intent.putExtra("IMG_URL", items.coat.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.coat.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.coat.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.coat.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderBelt(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.belt.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.belt.get(position).name);
                intent.putExtra("BONUS_1", items.belt.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.belt.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.belt.get(position).passive);
                intent.putExtra("IMG_URL", items.belt.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.belt.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.belt.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.belt.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderGloves(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.gloves.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.gloves.get(position).name);
                intent.putExtra("BONUS_1", items.gloves.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.gloves.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.gloves.get(position).passive);
                intent.putExtra("IMG_URL", items.gloves.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.gloves.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.gloves.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.gloves.get(position).description);

                context.startActivity(intent)

            }
        }
        fun renderSpatula(items: ListItems_v2, position: Int, context: Context) {
            Picasso.get().load(items.spatula.get(position).imgUrl).into(view.itemBanner)

            view.setOnClickListener {
                val intent = Intent(context, ObjectsInfoActivity::class.java)
                intent.putExtra("TITLE", items.spatula.get(position).name);
                intent.putExtra("BONUS_1", items.spatula.get(position).bonusImgUrl_1);
                intent.putExtra("BONUS_2", items.spatula.get(position).bonusImgUrl_2);
                intent.putExtra("PASSIVE", items.spatula.get(position).passive);
                intent.putExtra("IMG_URL", items.spatula.get(position).imgUrl);
                intent.putExtra("ITEM1_URL", items.spatula.get(position).subItem_1);
                intent.putExtra("ITEM2_URL", items.spatula.get(position).subItem_2);
                intent.putExtra("DESCRIPTION", items.spatula.get(position).description);

                context.startActivity(intent)

            }
        }
    }
}