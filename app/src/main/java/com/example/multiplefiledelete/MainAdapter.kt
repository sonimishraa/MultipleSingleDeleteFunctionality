package com.example.multiplefiledelete


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


abstract class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList: ArrayList<DataModel> = ArrayList()
    var selectedList: ArrayList<DataModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.item_main, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
         val item = arrayList.get(position)
        itemViewHolder.text_view.text = item.name
        Log.i("isSelected is working", "${item.isSelected}")
        if (item.isSelected) {
            itemViewHolder.checkBox.visibility = View.VISIBLE
            selectedList.add(item)
        } else {
            itemViewHolder.checkBox.visibility = View.GONE
        }

        /* itemViewHolder.text_view.setOnLongClickListener {
             itemViewHolder.checkBox.visibility = View.VISIBLE
             true
         }*/
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setData(it: ArrayList<DataModel>) {
        arrayList.clear()
        arrayList.addAll(it)
        selectedList.clear()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_view = itemView.findViewById<TextView>(R.id.text_view)
        var checkBox = itemView.findViewById<ImageView>(R.id.checkbox)
        var tvEmpty = itemView.findViewById<TextView>(R.id.tv_empty)

        init {
            text_view.setOnLongClickListener {
                val modelItem =  arrayList.get(adapterPosition)
                modelItem.isSelected = true
                notifyItemChanged(adapterPosition)
                onTextLongClick(adapterPosition)
                true
            }
        }
    }

    abstract fun onTextLongClick(adapterPosition: Int)

}

