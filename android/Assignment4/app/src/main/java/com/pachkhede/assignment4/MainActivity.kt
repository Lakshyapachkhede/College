package com.pachkhede.assignment4

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import org.json.JSONArray


class MainActivity : AppCompatActivity() {


    private lateinit var etName: EditText
    private lateinit var etDesc: EditText
    private lateinit var etEx: EditText
    private lateinit var etSearch: EditText
    private lateinit var btnInsert: Button
    private lateinit var btnSearch: Button
    private lateinit var listView: ListView
    private lateinit var db: AppDatabase
    private lateinit var dao: MethodDao
    val listData = mutableListOf<StringMethod>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDB()
        initViews()
        populateDataBase()


    }

    private fun populateDataBase() {
        lifecycleScope.launch {

            val count = dao.getCount()

            if (count == 0) {

                val methods = getMethodsFromJson()

                dao.insertAll(methods)

                Toast.makeText(
                    this@MainActivity,
                    "Data inserted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initDB() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "methods_database"
        ).build()

        dao = db.methodDao()
    }

    private fun initViews() {
        etName = findViewById(R.id.etName)
        etDesc = findViewById(R.id.etDesc)
        etSearch = findViewById(R.id.etSearch)
        etEx = findViewById(R.id.etExample)
        btnSearch = findViewById(R.id.btnSearch)
        btnInsert = findViewById(R.id.btnInsert)
        listView = findViewById(R.id.listViewRes)

        initListView()

        btnInsert.setOnClickListener {
            insert()
        }

        btnSearch.setOnClickListener {
            search()
        }



    }

    private fun insert() {
        val name = etName.text.toString().trim()
        val desc = etDesc.text.toString().trim()
        val ex = etEx.text.toString().trim()

        if (!name.matches(Regex("[a-zA-Z()]+"))) {
            etName.error = "Enter valid function name"
            return
        }

        if (desc.isEmpty()) {
            etDesc.error = "Description cannot be empty"
            return
        }
        if (ex.isEmpty()) {
            etEx.error = "Example cannot be empty"
            return
        }

        val method = StringMethod(name=name, description = desc, ex = ex)

        lifecycleScope.launch {
            dao.insertMethod(method)
            Toast.makeText(this@MainActivity, "Inserted Successfully", Toast.LENGTH_SHORT).show()
        }

    }

    private fun search()
    {
        val query = etSearch.text.toString().trim()

        if(query.isEmpty())
        {
            etSearch.error = "Search Query cannot be empty"
            return
        }
        lifecycleScope.launch {
            val methods: List<StringMethod>
            if(query.equals("0"))
            {
                methods = dao.getAllMethods()

            }
            else
            {
                methods = dao.searchMethod(query)
            }
            if(methods.isEmpty())
            {   listData.clear()
                (listView.adapter as ArrayAdapter<StringMethod>).notifyDataSetChanged()
                Toast.makeText(this@MainActivity, "No Results Found", Toast.LENGTH_SHORT).show()
            }
            else
            {
                listData.clear()
                listData.addAll(methods)
                (listView.adapter as ArrayAdapter<StringMethod>).notifyDataSetChanged()
                Toast.makeText(this@MainActivity, "${methods.count()} Results Found", Toast.LENGTH_SHORT).show()
            }

        }




    }

    fun initListView()
    {
        val listAdapter = object : ArrayAdapter<StringMethod>(this,
            android.R.layout.simple_list_item_2,
            android.R.id.text1,
            listData)
        {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)

                val method = getItem(position)

                view.findViewById<TextView>(android.R.id.text1).text = method?.name
                view.findViewById<TextView>(android.R.id.text2).text = method?.description
                view.findViewById<TextView>(android.R.id.text2).maxLines = 1
                view.findViewById<TextView>(android.R.id.text2).ellipsize =  TextUtils.TruncateAt.END



                return view

            }
        }


        listView.adapter = listAdapter;

        listView.setOnItemClickListener { parent, view, pos, id ->
            val method = listData[pos]
            val intent = Intent(this, MethodActivity::class.java)


            intent.putExtra("name", method.name)
            intent.putExtra("description", method.description)
            intent.putExtra("example", method.ex)

            startActivity(intent)

        }
        listView.setOnItemLongClickListener { parent, view, position, id ->
            val method = listData[position]

            lifecycleScope.launch {
                dao.deleteMethod(method.id)
                listData.removeAt(position)
                (listView.adapter as ArrayAdapter<StringMethod>).notifyDataSetChanged()

                Toast.makeText(this@MainActivity,"Item deleted",Toast.LENGTH_SHORT).show()

            }
            true
        }


    }


    private fun loadJson() : String
    {
        return assets.open("data.json")
            .bufferedReader()
            .use {
                it.readText()
            }
    }

    private fun getMethodsFromJson() : List<StringMethod>
    {
        val jsonString = loadJson()
        val jsonArray = JSONArray(jsonString)

        val methods = mutableListOf<StringMethod>()

        for (i in 0 until jsonArray.length())
        {
            val obj = jsonArray.getJSONObject(i)
            val method = StringMethod(
                name = obj.getString("method_name"),
                description = obj.getString("description_use"),
                ex = obj.getString("example")
            )

            methods.add(method)
        }

        return methods
    }



}