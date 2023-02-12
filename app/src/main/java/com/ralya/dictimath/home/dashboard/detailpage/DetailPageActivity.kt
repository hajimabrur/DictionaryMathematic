package com.ralya.dictimath.home.dashboard.detailpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.HomeActivity
import com.ralya.dictimath.home.dashboard.model.KelasSatu
import kotlinx.android.synthetic.main.activity_detail_page_dashboard.*


class DetailPageActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var desclist = ArrayList<KelasSatu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page_dashboard)

        var data = intent.getParcelableExtra<KelasSatu>("data")

        if (data != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference("Kelas")
                .child(data.namakelas.toString())
                .child("desc")
        }


        if (data != null) {
            tvTitleClass.text = data.namakelas
        }

        ivBack.setOnClickListener {
            var intent = Intent(this@DetailPageActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        rvClassVideo.layoutManager = LinearLayoutManager(this)
        getData()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                desclist.clear()
                for (getdataSnapshot in snapshot.children) {
                    var kelas = getdataSnapshot.getValue(KelasSatu::class.java)
                    desclist.add(kelas!!)
                }

                rvClassVideo.adapter = ClassVideoAdapter(desclist){

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailPageActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}