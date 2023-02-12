package com.ralya.dictimath.home.dashboard


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.fragment.DashboardFragment
import com.ralya.dictimath.home.dictionary.fragment.KamusFragment
import com.ralya.dictimath.home.exercise.LatihanFragment
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var preference : Preference
    private lateinit var mDatabase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        preference = Preference(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        tvNama.text = preference.getValues("nama")
        tvNamasekolah.text = preference.getValues("namasekolah")

        Glide.with(this)
            .load(preference.getValues("fotoprofil"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfilePicture)

        val fragmentHome = DashboardFragment()
        val fragmentKamus = KamusFragment()
        val fragmentLatihan = LatihanFragment()

        setFragment(fragmentHome)
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

    private fun setFragment (fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame, fragment)
        fragmentTransacion.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int){
        imageView.setImageResource(int)
    }
}