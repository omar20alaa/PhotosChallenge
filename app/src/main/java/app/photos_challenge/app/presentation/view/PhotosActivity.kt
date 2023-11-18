package app.photos_challenge.app.presentation.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.photos_challenge.R
import app.photos_challenge.app.adapter.PhotosWithBannerAdapter
import app.photos_challenge.app.presentation.viewmodel.PhotosViewModel
import app.photos_challenge.databinding.ActivityPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosBinding
    private lateinit var photosAdapter: PhotosWithBannerAdapter
    private val viewModel: PhotosViewModel by viewModels()
    private lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        displayData()
    }

    private fun setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private fun displayData() {
        setupRecyclerView()
        observeViewModel()
        setupScrolling()
    }


    private fun setupScrolling() {
        binding.rvPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    viewModel.fetchNextPage(connectivityManager)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvPhotos.layoutManager = layoutManager
        binding.rvPhotos.itemAnimator = null;
        photosAdapter = PhotosWithBannerAdapter()
        binding.rvPhotos.adapter = photosAdapter
    }

    private fun observeViewModel() {
        viewModel.fetchPhotos(connectivityManager)

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.photos.observe(this) { photos ->
            photosAdapter.submitList(photos)
        }

    }

}