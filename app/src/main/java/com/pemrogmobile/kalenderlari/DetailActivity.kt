package com.pemrogmobile.kalenderlari

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val item = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_ITEM, Event::class.java)
        }else {
            @Suppress("DEPRECATED")
            intent.getParcelableExtra(EXTRA_ITEM)
        }

        val tvItemName: TextView = findViewById(R.id.item_name)
        val tvItemDescription: TextView = findViewById(R.id.item_description)
        val tvItemDate: TextView = findViewById(R.id.item_date)
        val imgItemPhoto: ImageView = findViewById(R.id.item_photo)

        if (item != null) {
            tvItemName.text = item.name
            tvItemDescription.text = item.description
            tvItemDate.text = item.date
            imgItemPhoto.setImageResource(item.photo)
        }
        val shareButton: Button = findViewById(R.id.action_share)

        shareButton.setOnClickListener {
            val intentShare = Intent()
            intentShare.action = Intent.ACTION_SEND
            intentShare.putExtra(Intent.EXTRA_TEXT, "${item?.link}")
            intentShare.type = "text/plain"
            startActivity(Intent.createChooser(intentShare, "Bagikan link"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}