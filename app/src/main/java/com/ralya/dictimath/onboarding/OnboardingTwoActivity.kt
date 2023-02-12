package com.ralya.dictimath.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ralya.dictimath.R
import com.ralya.dictimath.sign.singin.SignInScreenActivity
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two )

        btnNext.setOnClickListener {
            val intent = Intent(this@OnboardingTwoActivity, OnboardingThreeActivity ::class.java)
            startActivity(intent)
        }

        btnSkip.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingTwoActivity, SignInScreenActivity::class.java)
            startActivity(intent)
        }
    }
}