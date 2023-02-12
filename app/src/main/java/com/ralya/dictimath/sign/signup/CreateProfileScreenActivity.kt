package com.ralya.dictimath.sign.signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.ralya.dictimath.R
import com.ralya.dictimath.home.dashboard.HomeActivity
import com.ralya.dictimath.utils.Preference
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_signin.*
import java.util.*

class CreateProfileScreenActivity : AppCompatActivity(), PermissionListener{

    val REQUEST_IMAGE_CAPTURE  = 1
    private var statusAdd:Boolean = false
    lateinit var filePath:Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageReferensi : StorageReference
    private lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        preference = Preference(this)
        storage = FirebaseStorage.getInstance()
        storageReferensi =storage.getReference()

        tvProfileDesc.text ="Selamat Datang\n"+intent.getStringExtra("nama")

        ivAddButton.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                btnMasuk.visibility = View.VISIBLE
                ivAddButton.setImageResource(R.drawable.add_ic)
                ivProfilePicture.setImageResource(R.drawable.avatar_ic)
            } else {
//                Dexter.withActivity(this)
//                    .withPermission(android.Manifest.permission.CAMERA)
//                    .withListener(this)
//                    .check()
                ImagePicker.with(this)
                    .galleryOnly()	//User can only select image from Gallery
                    .crop()
                    .start()	//Default Request Code is ImagePicker.REQUEST_CODE
            }
        }

        btnLewati.setOnClickListener {
            finishAffinity()

            var goHome = Intent(this@CreateProfileScreenActivity, HomeActivity::class.java)
            startActivity(goHome)

        }

        btnMasuk.setOnClickListener {
            if (filePath != null) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle(("uploading..."))
                progressDialog.show()

                var ref = storageReferensi.child("Images/"+UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preference.setValues("fotoprofil", it.toString())
                        }

                        finishAffinity()
                        var goHome = Intent(this@CreateProfileScreenActivity, HomeActivity::class.java)
                        startActivity(goHome)
                    }
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 + taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("upload" + progress.toInt()+ " %")
                    }
            }else {
                finishAffinity()

                var goHome = Intent(this@CreateProfileScreenActivity, HomeActivity::class.java)
                startActivity(goHome)
            }
        }
    }


    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak bisa menambahkan Foto profile", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Ingin melanjutkan? Silahkan upload nanti saja", Toast.LENGTH_LONG).show()
    }


//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
//            var bitmap = data?.extras?.get("data") as Bitmap
//            statusAdd = true
//
//            filePath = data.getData()!!
//            Glide.with(this)
//                .load(bitmap)
//                .apply(RequestOptions.circleCropTransform())
//                .into(avatar)
//
//            btn_start.visibility = View.VISIBLE
//            btn_add.setImageResource(R.drawable.ic_delete)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            statusAdd = true
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfilePicture)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Upload Foto Gagal", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
    }
}