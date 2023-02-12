package com.ralya.dictimath.home.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.fragment.DashboardFragment
import com.ralya.dictimath.home.dictionary.fragment.KamusFragment
import kotlinx.android.synthetic.main.activity_exercise_page.*

class ExercisePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_page)
        val fragmentHome = DashboardFragment()
        val fragmentKamus = KamusFragment()
        val fragmentLatihan = LatihanFragment()

        setFragment(fragmentLatihan)
        ivMenuHome.setOnClickListener{
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

        ivMenuExercise.setOnClickListener{
            setFragment(fragmentLatihan)

            changeIcon(ivMenuHome, R.drawable.home_primary)
            changeIcon(ivMenuDictionary, R.drawable.dictionary_primary)
            changeIcon(ivMenuExercise, R.drawable.exercise_active)
        }
    }

    private fun setFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTrancasion = fragmentManager.beginTransaction()
        fragmentTrancasion.replace(R.id.layout_frame, fragment)
        fragmentTrancasion.commit()
    }

    private fun changeIcon(imageView: ImageView, int:Int){
        imageView.setImageResource(int)
    }

}