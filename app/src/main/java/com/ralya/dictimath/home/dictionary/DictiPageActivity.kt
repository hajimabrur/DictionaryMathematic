package com.ralya.dictimath.home.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ralya.dictimath.R
import com.ralya.dictimath.home.exercise.LatihanFragment
import com.ralya.dictimath.home.dashboard.fragment.DashboardFragment
import com.ralya.dictimath.home.dictionary.fragment.KamusFragment
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.activity_dicti_page.ivMenuDictionary
import kotlinx.android.synthetic.main.activity_dicti_page.ivMenuExercise
import kotlinx.android.synthetic.main.activity_dicti_page.ivMenuHome
import kotlinx.android.synthetic.main.activity_dicti_page.ivProfilePicture
import kotlinx.android.synthetic.main.activity_dicti_page.tvNama
import kotlinx.android.synthetic.main.activity_dicti_page.tvNamasekolah

class DictiPageActivity : AppCompatActivity() {

    private lateinit var preference : Preference
    private lateinit var mDatabase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicti_page)

        preference = Preference(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Dictionary")

        tvNama.text = preference.getValues("nama")
        tvNamasekolah.text = preference.getValues("namasekolah")

        Glide.with(this)
            .load(preference.getValues("fotoprofil"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfilePicture)

        val fragmentKamus = KamusFragment()
        val fragmentHome = DashboardFragment()
        val fragmentLatihan = LatihanFragment()

        setFragment(fragmentKamus)

        ivMenuHome.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(ivMenuHome, R.drawable.home_active)
            changeIcon(ivMenuDictionary, R.drawable.dictionary_primary)
            changeIcon(ivMenuExercise, R.drawable.exercise_primary)
        }

        ivMenuDictionary.setOnClickListener {
            setFragment(fragmentKamus)

            changeIcon(ivMenuHome, R.drawable.home_primary)
            changeIcon(ivMenuDictionary, R.drawable.dictionary_active)
            changeIcon(ivMenuExercise, R.drawable.exercise_primary)
        }

        ivMenuExercise.setOnClickListener {
            setFragment(fragmentLatihan)

            changeIcon(ivMenuHome, R.drawable.home_primary)
            changeIcon(ivMenuDictionary, R.drawable.dictionary_primary)
            changeIcon(ivMenuExercise, R.drawable.exercise_active)
        }
    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame, fragment)
        fragmentTransacion.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int) {
        imageView.setImageResource(int)
    }
}