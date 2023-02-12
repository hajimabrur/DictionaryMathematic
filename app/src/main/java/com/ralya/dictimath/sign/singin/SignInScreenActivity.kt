package com.ralya.dictimath.sign.singin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.HomeActivity
import com.ralya.dictimath.sign.signup.SignUpScreenActivity
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.activity_signin.*

class SignInScreenActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String

    lateinit var mDatabase: DatabaseReference
    lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preference(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")) {
            finishAffinity()

            var goHome = Intent(this@SignInScreenActivity, HomeActivity::class.java)
            startActivity(goHome)
        }
c 
        btnMasuk.setOnClickListener {
            iUsername = ipUsername.hint.toString()
            iPassword = ipPassword.hint.toString()

            if (iUsername == "") {
                ipUsername.error = "Silahkan masukkan username anda"
                ipUsername.requestFocus()
            } else if (iPassword == "") {
                ipPassword.error = "Silahkan masukkan password anda"
                ipPassword.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }
        tvSignUp.setOnClickListener {
            var goSignup = Intent(this@SignInScreenActivity, SignUpScreenActivity::class.java)
            startActivity(goSignup)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInScreenActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInScreenActivity, "Username tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {

                    if (user.password.equals(iPassword)) {

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("password", user.password.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("url", user.fotoprofil.toString())
                        preference.setValues("namasekolah", user.namasekolah.toString())
                        preference.setValues("Status", "1")

                        var intent = Intent(this@SignInScreenActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInScreenActivity, "Password Anda salah",
                            Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
    }
}