package com.ralya.dictimath.home.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.ralya.dictimath.R
import com.ralya.dictimath.home.exercise.model.Latihan
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.fragment_latihan.*


class LatihanFragment : Fragment() {

    private lateinit var preference: Preference
    private lateinit var mDatabase: DatabaseReference

    private var datalist = ArrayList<Latihan>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latihan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preference =Preference(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Latihan")

        rvExercise.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (getsnapshot in snapshot.children){
                    var latihan = getsnapshot.getValue(Latihan::class.java)
                    datalist.add(latihan!!)
                }

                rvExercise.adapter = ItemListAdapter(datalist){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

}