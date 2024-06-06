package com.pemrogmobile.kalenderlari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvEvents: RecyclerView
    private val list = ArrayList<Event>()

    private fun showSelectedHero(hero: Event) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvEvents = findViewById(R.id.rv_run_event)
        rvEvents.setHasFixedSize(true)
        list.addAll(getListEvents())
        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun selectMenu(selectedMenu: Int) {
        when (selectedMenu) {
            R.id.about -> {
                val intentAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
    }

    private fun getListEvents(): ArrayList<Event> {
        val dataName = resources.getStringArray(R.array.event_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataDate = resources.getStringArray(R.array.event_date)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listEvent = ArrayList<Event>()
        for (i in dataName.indices) {
            val event = Event(dataName[i], dataDescription[i], dataDate[i], dataLink[i], dataPhoto.getResourceId(i, -1))
            listEvent.add(event)
        }
        return listEvent
    }

    private fun showRecyclerList() {
        rvEvents.layoutManager = LinearLayoutManager(this)
        val listEventAdapter = ListEventAdapter(list)
        rvEvents.adapter = listEventAdapter

        listEventAdapter.setOnItemClickCallback(object : ListEventAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Event) {
                showSelectedHero(data)
                val item = Event(data.name, data.description, data.date, data.link, data.photo)
                val moveObjIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveObjIntent.putExtra(DetailActivity.EXTRA_ITEM, item)
                startActivity(moveObjIntent)
            }
        })
    }
}