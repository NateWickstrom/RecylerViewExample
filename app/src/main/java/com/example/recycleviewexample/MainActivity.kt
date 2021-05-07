package com.example.recycleviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var list: MutableList<Item> = mutableListOf()
    val adapter = MyAdapter(list)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        
        for (i in 1..30) list.add(Item(text = "Item $i"))
        adapter.notifyDataSetChanged()
    }
    
    private fun onToggle(index: Int, isChecked: Boolean) {
        //list[index] = list[index].copy(isChecked = isChecked)
    }
    
    inner class MyAdapter(private var list: List<Item>) : RecyclerView.Adapter<MyViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(list[position], position)
        }

        override fun getItemCount(): Int = list.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text: TextView = view.findViewById(R.id.text)
        private val switch: Switch = view.findViewById(R.id.check)

        fun bind(item: Item, index: Int) {
            switch.setOnCheckedChangeListener(null)

            text.text = item.text
            switch.isChecked = item.isChecked

            switch.setOnCheckedChangeListener { _, isChecked -> this@MainActivity.onToggle(index, isChecked)}
        }
    }
    
    data class Item (val text: String, val isChecked: Boolean = false)
}
