package com.ralya.dictimath.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.HomeActivity
import com.ralya.dictimath.sign.singin.SignInScreenActivity
import com.ralya.dictimath.sign.singin.User
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.activity_signupscreen.*

class SignUpScreenActivity : AppCompatActivity() {
    private lateinit var sNamaPengguna:String
    private lateinit var sEmail:String
    private lateinit var sUsername:String
    private lateinit var sPassword:String
    private lateinit var sSekolah: String
    private lateinit var sRePassword: String

    lateinit var mFirebaseDatabaseReference: DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupscreen)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase =FirebaseDatabase.getInstance().reference
        mFirebaseDatabaseReference = mFirebaseInstance.getReference("User")
        preference = Preference(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")) {
            finishAffinity()

            var goHome = Intent(this@SignUpScreenActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btnMasuk.setOnClickListener {
            sNamaPengguna  = ipName.hint.toString()
            sEmail  = ipEmail.hint.toString()
            sUsername  = ipUsername.hint.toString()
            sPassword  = ipPassword.hint.toString()
            sSekolah = ipNamaSekolah.hint.toString()


            if(sNamaPengguna == ""){
                ipName.error ="Silahkan masukkan nama Anda"
                ipName.requestFocus()
            } else if (sEmail == ""){
                ipEmail.error = "Silahkan masukkan email Anda"
                ipEmail.requestFocus()
            } else if (sUsername == ""){
                ipUsername.error ="Silahkan masukkan username Anda"
                ipUsername.requestFocus()
            } else if (sPassword == "") {
                ipPassword.error = "Silahkan masukkan password anda"
                ipPassword.requestFocus()
            }  else if (sSekolah == "") {
                ipNamaSekolah.error = "Silahkan masukkan nama sekolah Anda"
                ipNamaSekolah.requestFocus()
            } else {
                saveUsername(sNamaPengguna, sEmail, sUsername, sPassword, sSekolah,)
            }
        }



        tvSignIn.setOnClickListener {

            var intent = Intent(this@SignUpScreenActivity, SignInScreenActivity::class.java)
            startActivity(intent)
        }


    }
    private fun saveUsername(
        sNamaPengguna: String,
        sEmail: String,
        sUsername: String,
        sPassword: String,
        sSekolah: String,
    ) {
        var user =User()
        user.nama = sNamaPengguna
        user.email = sEmail
        user.username  = sUsername
        user.password = sPassword
        user.namasekolah = sSekolah

        if (sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mFirebaseDatabaseReference.child(sUsername).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user =snapshot.getValue(User::class.java)
                if (user == null){
                    mFirebaseDatabaseReference.child(sUsername).setValue(data)

                    var goCreateProfileScreen = Intent(this@SignUpScreenActivity, CreateProfileScreenActivity::class.java).putExtra("nama", data?.nama)
                    startActivity(goCreateProfileScreen)
                }else {
                    Toast.makeText(this@SignUpScreenActivity, "Username sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpScreenActivity, "" +error.message, Toast.LENGTH_LONG).show()
            }
        })

    }


}