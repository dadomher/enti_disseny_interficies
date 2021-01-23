package com.bearslovebeer.tifitiappp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.adapter.ObjectsAdapter
import com.bearslovebeer.tifitiappp.models.ListItems_v2
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_objects.*
import java.io.InputStream

class ObjectsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_objects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = loadJson(context!!)

        val items = Gson().fromJson(jsonString, ListItems_v2::class.java)

        initRecycler(items)
    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            // Create InputStream
            input = context.assets.open("json_objectsData.json")

            val size = input.available()

            // Create a buffer with the size
            val buffer = ByteArray(size)

            // Read data from InputStream into the Buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)

            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        } finally {
            // Must close the stream
            input?.close()
        }
    }

    fun initRecycler(items: ListItems_v2) {

        sword_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var adapter = ObjectsAdapter(items, 0)
        sword_recyclerView.adapter = adapter

        bow_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 1)
        bow_recyclerView.adapter = adapter

        rod_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 2)
        rod_recyclerView.adapter = adapter

        tear_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 3)
        tear_recyclerView.adapter = adapter

        vest_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 4)
        vest_recyclerView.adapter = adapter

        coat_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 5)
        coat_recyclerView.adapter = adapter

        belt_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 6)
        belt_recyclerView.adapter = adapter

        gloves_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 7)
        gloves_recyclerView.adapter = adapter

        spatula_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ObjectsAdapter(items, 8)
        spatula_recyclerView.adapter = adapter
    }
}