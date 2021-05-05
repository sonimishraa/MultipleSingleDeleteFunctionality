package com.example.multiplefiledelete

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var list: ArrayList<DataModel>
    lateinit var recyclerView: RecyclerView
    lateinit var tv_empty: TextView
    lateinit var mainAdapter: MainAdapter
    var arrayList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerview)
        tv_empty = findViewById(R.id.tv_empty)
        mainAdapter = object : MainAdapter() {

            override fun onTextLongClick(adapterPosition: Int) {
                getActionCallback()
            }
        }
        // add value in arrayList
        //arrayList.addAll(arrayListOf("one","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve"))
        list = getDataModelList()
        recyclerView.adapter = mainAdapter
        /* arrayList.let {
             mainAdapter.setData(it)
         }*/
        mainAdapter.setData(list)
    }

    private fun getDataModelList(): ArrayList<DataModel> {
        val dataList = ArrayList<DataModel>()
        for (i in 0..10) {
            val model = DataModel("one $i", false)
            dataList.add(model)
        }
        return dataList
    }

    private fun getActionCallback(): ActionMode? {
        val callback = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu_item, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false

            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val id = item?.itemId
                return when (id) {
                    R.id.delete -> {
                        val selectedList = mainAdapter.selectedList

                        // used for any file directory delete
                        // as a generalized code
                       /* selectedList.forEach {
                            list.remove(it)
                            it.delete()
                        }*/
                        list.removeAll(selectedList)
                        mainAdapter.setData(list)
//                        mode?.title = mainAdapter.selectedList.size.toString() + " Selected"
                        /*  if (mainAdapter.selectedList.size == mainAdapter.arrayList.size){
                             mainAdapter.arrayList.removeAll(mainAdapter.selectedList)
                              mainAdapter.notifyDataSetChanged()
                          }*/
                        true
                    }
                    R.id.select_all -> {
                        list.forEach {
                            it.isSelected = true
                        }
                        mainAdapter.setData(list)
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {

            }
        }
       val actionMode =  startActionMode(callback)
        return actionMode
    }
}