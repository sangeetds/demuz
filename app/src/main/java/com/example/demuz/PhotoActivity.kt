package com.example.demuz
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.squareup.picasso.Picasso
//
//class PhotoActivity : AppCompatActivity() {
//
//    private var selectedPhoto: Question? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_photo)
//
//        selectedPhoto = intent.getSerializableExtra(PHOTO_KEY) as Question
//        Picasso.get().load(selectedPhoto?.url).into(photoImageView)
//
//        photoDescription.text = selectedPhoto?.explanation
//    }
//
//    companion object {
//        private val PHOTO_KEY = "PHOTO"
//    }
//}
