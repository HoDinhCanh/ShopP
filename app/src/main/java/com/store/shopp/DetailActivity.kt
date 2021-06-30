package com.store.shopp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val mdetail= intent.getParcelableExtra<MovieModel>(MainActivity.INTENT_PARCELABLE)

        val name : TextView = findViewById(R.id.title1)
        val detail : TextView = findViewById(R.id.detail)
        val color : TextView = findViewById(R.id.color)
        val price : TextView = findViewById(R.id.price)

        if (mdetail != null) {
            name.text=mdetail.title
            detail.text=mdetail.detail
            color.text=mdetail.color
            price.text= mdetail.price

        }
    }
}