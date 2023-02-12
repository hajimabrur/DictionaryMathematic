package com.ralya.dictimath.home.dashboard.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ralya.dictimath.R
import com.google.firebase.database.*
import com.ralya.dictimath.home.dashboard.adapter.KelasSatuAdapter
import com.ralya.dictimath.home.dashboard.adapter.KelasDuaAdapter
import com.ralya.dictimath.home.dashboard.detailpage.DetailPageActivity
import com.ralya.dictimath.home.dashboard.model.KelasSatu
import com.ralya.dictimath.home.dashboard.model.KelasDua
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private lateinit var preference: Preference
    private lateinit var mDatabase : DatabaseReference

    private var datalist = ArrayList<KelasSatu>()
    private var datacoming = ArrayList<KelasDua>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvPopularClass.layoutManager = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        rvAvalaibleClass.layoutManager = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                datalist.clear()
                for (getsnapshot in snapshot.children){
                    var popular = getsnapshot.getValue(KelasSatu::class.java)
                    datalist.add(popular!!)

                }

                datacoming.clear()
                for(getsnapshot in snapshot.children){
                    var avalaible = getsnapshot.getValue(KelasDua::class.java)
                    datacoming.add(avalaible!!)
                }

                rvPopularClass.adapter = KelasSatuAdapter(datalist) {
                    var intent = Intent(context, DetailPageActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                rvAvalaibleClass.adapter = KelasDuaAdapter(datacoming) {
                    var intent = Intent(context, DetailPageActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,""+error.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}

