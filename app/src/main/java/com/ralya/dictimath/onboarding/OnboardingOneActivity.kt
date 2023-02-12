package com.ralya.dictimath.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ralya.dictimath.R
import com.ralya.dictimath.sign.singin.SignInScreenActivity
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity() {

    private lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preference = Preference(this)

        if (preference.getValues("onboarding").equals("1")) {
            val intent = Intent(this@OnboardingOneActivity, SignInScreenActivity::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            val intent = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(intent)
        }

        btnSkip.setOnClickListener {
            preference.setValues("onboarding", "1")
            if (preference.getValues("status").equals("1")) {
                finishAffinity()

                val intent = Intent(this@OnboardingOneActivity, SignInScreenActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
