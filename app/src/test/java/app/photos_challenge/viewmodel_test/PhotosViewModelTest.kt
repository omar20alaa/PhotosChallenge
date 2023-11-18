package app.photos_challenge.viewmodel_test

import android.net.ConnectivityManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.photos_challenge.app.presentation.viewmodel.PhotosViewModel
import app.photos_challenge.domain.usecase.FetchAndCachePhotosUseCase
import app.photos_challenge.domain.usecase.GetCachedPhotosUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotosViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val fetchAndCachePhotosUseCase: FetchAndCachePhotosUseCase = mockk()
    private val getCachedPhotosUseCase: GetCachedPhotosUseCase = mockk()

    private lateinit var photosViewModel: PhotosViewModel
    private val fetchedPhotos = FlickrPhotoMockData.getMockFlickrPhotos()

    @Before
    fun setUp() {
        photosViewModel = PhotosViewModel(fetchAndCachePhotosUseCase, getCachedPhotosUseCase)
        coEvery { fetchAndCachePhotosUseCase.invoke(any()) } returns Result.success(fetchedPhotos)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun fetchPhotosWhenInternetIsAvailable() = coroutineRule.run {

        // Given
        val mockConnectivityManager = mockk<ConnectivityManager>()
        mockConnectivityManager.setActiveNetworkInfoForTest(true)

        // When
        photosViewModel.fetchPhotos(mockConnectivityManager)

        // Then
        assertEquals(fetchedPhotos, photosViewModel.photos.value)
    }

    @Test
    fun fetchPhotosWhenInternetIsUnavailable() = coroutineRule.run {

        // Given
        val cachedPhotos = FlickrPhotoMockData.getMockFlickrPhotos()
        coEvery { getCachedPhotosUseCase.invoke() } returns flowOf(cachedPhotos)

        val mockConnectivityManager = mockk<ConnectivityManager>()
        mockConnectivityManager.setActiveNetworkInfoForTest(false)

        // When
        photosViewModel.fetchPhotos(mockConnectivityManager)

        // Then
        assertEquals(cachedPhotos, photosViewModel.photos.value)
    }

}

