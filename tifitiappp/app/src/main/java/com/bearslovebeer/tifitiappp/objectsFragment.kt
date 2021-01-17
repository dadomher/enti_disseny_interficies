package com.bearslovebeer.tifitiappp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bearslovebeer.tifitiappp.adapter.ItemsAdapter
import com.bearslovebeer.tifitiappp.models.Items2
import com.bearslovebeer.tifitiappp.models.ListItems
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_objects.*
import java.io.InputStream

class objectsFragment : Fragment() {

    lateinit var items: ArrayList<Items2>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_objects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        items = arrayListOf<Items2>()

        val jsonString = loadJson(context!!)

        val items2 = Gson().fromJson(jsonString, ListItems::class.java)
        Log.d("MainActivity", "Size: ${items2.data.size}")

        initRecycler()
    }

    private fun loadJson(context: Context): String? {
        var input: InputStream? = null
        var jsonString: String

        try {
            // Create InputStream
            input = context.assets.open("json_data.json")

            val size = input.available()

            // Create a buffer with the size
            val buffer = ByteArray(size)

            // Read data from InputStream into the Buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            //Log.d("MainActivity", jsonString)
            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        } finally {
            // Must close the stream
            input?.close()
        }
    }

    fun initRecycler() {
        item_recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ItemsAdapter(items)
        item_recyclerView.adapter = adapter
    }
}