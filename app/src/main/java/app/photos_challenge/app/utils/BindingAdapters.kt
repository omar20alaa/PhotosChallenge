package app.photos_challenge.app.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import app.photos_challenge.R
import app.photos_challenge.data.models.FlickrPhoto
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("photosList")
    fun bindPhotosList(recyclerView: RecyclerView, photos: LiveData<List<FlickrPhoto>>?) {
        if (photos == null || photos.value.isNullOrEmpty()) {
            recyclerView.visibility = View.GONE
        } else {
            recyclerView.adapter?.let { adapter ->
                if (adapter is app.photos_challenge.app.adapter.PhotosWithBannerAdapter) {
                    adapter.submitList(photos.value)
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        if (imageUrl.isNullOrEmpty()) {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    imageView.context,
                    R.drawable.place_holder
                )
            )
        } else {
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error_place_holder)
                .into(imageView)
        }
    }
}