package app.photos_challenge.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.app.presentation.view.FullScreenPhotoActivity
import app.photos_challenge.databinding.ItemAdBannerBinding
import app.photos_challenge.databinding.ItemPhotoBinding

private enum class ViewType { PHOTO, AD_BANNER }

class PhotosWithBannerAdapter :
    ListAdapter<FlickrPhoto, RecyclerView.ViewHolder>(PhotoDiffCallback()) {

    private companion object {
        private const val AD_BANNER_INTERVAL = 5
        private const val NO_POSITION = RecyclerView.NO_POSITION
    }

    private var clickedPosition: Int =
        NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.PHOTO.ordinal -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemPhotoBinding.inflate(inflater, parent, false)
                PhotoViewHolder(binding)
            }

            ViewType.AD_BANNER.ordinal -> {
                val inflater = LayoutInflater.from(parent.context)
                val adBannerBinding = ItemAdBannerBinding.inflate(inflater, parent, false)
                AdBannerViewHolder(adBannerBinding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ViewType.PHOTO.ordinal -> {
                val photoPosition = getPhotoPosition(position)
                if (photoPosition != -1) {
                    val photo = getItem(photoPosition) as FlickrPhoto
                    (holder as PhotosWithBannerAdapter.PhotoViewHolder).bind(photo)
                }
            }

            ViewType.AD_BANNER.ordinal -> {
                val photo = getPhotoPosition(position)
                if (photo != -1) {
                    val photoBanner = getItem(photo) as FlickrPhoto
                    (holder as PhotosWithBannerAdapter.AdBannerViewHolder).bind(photoBanner)
                }
            }
        }
    }

    private fun getPhotoPosition(position: Int): Int {
        val photoCount = currentList.filterIsInstance<FlickrPhoto>().size
        return if (position < photoCount) {
            position
        } else {
            val adjustedPosition = position - (position / (AD_BANNER_INTERVAL + 1))
            if (adjustedPosition < photoCount) {
                adjustedPosition
            } else {
                -1
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % (AD_BANNER_INTERVAL + 1) == 0) {
            ViewType.AD_BANNER.ordinal
        } else {
            ViewType.PHOTO.ordinal
        }
    }

    override fun getItemCount(): Int {
        val photoCount = currentList.filterIsInstance<FlickrPhoto>().size
        val adBannerCount = photoCount / AD_BANNER_INTERVAL
        return photoCount + adBannerCount
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: FlickrPhoto) {
            binding.photo = photo
            binding.root.setOnClickListener {
                clickedPosition = adapterPosition
                val clickedPosition = getPhotoPosition(adapterPosition)
                clickedPosition.let { position ->
                    val clickedPhoto = getItem(position)
                    val intent = Intent(binding.root.context, FullScreenPhotoActivity::class.java)
                    intent.putExtra("photoUrl", clickedPhoto.imageUrl)
                    intent.putExtra("position", position)
                    binding.root.context.startActivity(intent)
                }
            }
            binding.executePendingBindings()
        }
    }

    inner class AdBannerViewHolder(private val binding: ItemAdBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: FlickrPhoto) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    class PhotoDiffCallback : DiffUtil.ItemCallback<FlickrPhoto>() {
        override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem == newItem
        }
    }
}