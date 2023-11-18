package app.photos_challenge.app.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.photos_challenge.R
import app.photos_challenge.databinding.ActivityFullScreenPhotoBinding
import app.photos_challenge.app.utils.AppConstants.Companion.PHOTO_URL
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullScreenPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayImage()
        setupEvents()
    }

    private fun setupEvents() {
        binding.closeButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun displayImage() {
        val imageUrl = intent.getStringExtra(PHOTO_URL)
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.error_place_holder)
                .into(binding.fullScreenImageView)
        } else {
            Glide.with(this)
                .load(R.drawable.error_place_holder)
                .placeholder(R.drawable.error_place_holder)
                .into(binding.fullScreenImageView)
        }
    }
}