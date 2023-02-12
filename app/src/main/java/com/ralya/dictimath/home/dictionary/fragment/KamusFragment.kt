package com.ralya.dictimath.home.dictionary.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ralya.dictimath.R
import com.google.firebase.database.*
import com.ralya.dictimath.home.dictionary.detailpage.DetailActivity
import com.ralya.dictimath.home.dictionary.adapter.ItemListAdapter
import com.ralya.dictimath.home.dictionary.model.Dictionary
import kotlinx.android.synthetic.main.fragment_kamus.*
import com.ralya.dictimath.utils.Preference


class KamusFragment : Fragment() {

    private lateinit var preference: Preference
    private lateinit var mDatabase : DatabaseReference

    private var datalist = ArrayList<Dictionary>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kamus, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_itemlist.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                datalist.clear()
                for (getsnapshot in snapshot.children){
                    var istilah = getsnapshot.getValue(Dictionary::class.java)
                    datalist.add(istilah!!)
                }

                rv_itemlist.adapter = ItemListAdapter(datalist) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}