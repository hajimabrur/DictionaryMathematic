package com.ralya.dictimath.home.dictionary.detailpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dictionary.DictiPageActivity
import com.ralya.dictimath.home.dictionary.model.Dictionary
import kotlinx.android.synthetic.main.activity_detail_page_dictionary.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page_dictionary)

        ivBack.setOnClickListener {
            var intent = Intent(this@DetailActivity, DictiPageActivity::class.java)
            startActivity(intent)
        }

        val data =intent.getParcelableExtra<Dictionary>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Dictionary")
            .child(data!!.Istilah.toString())
            .child(data.makna.toString())

        tvIstilah.text = data.Istilah
        tvDesc.text = data.makna

    }
}