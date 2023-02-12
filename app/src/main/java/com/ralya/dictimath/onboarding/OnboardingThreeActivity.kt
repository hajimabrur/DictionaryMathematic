package com.ralya.dictimath.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ralya.dictimath.R
import com.ralya.dictimath.sign.singin.SignInScreenActivity
import kotlinx.android.synthetic.main.activity_onboarding_three.*

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btnNext.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingThreeActivity, SignInScreenActivity::class.java)
            startActivity(intent)
        }
    }
}