package com.example.newyorktimes.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.newyorktimes.data.models.News
import com.example.newyorktimes.data.models.NewsResult
import com.example.newyorktimes.data.network.repository.MostPopularNewsRepository
import com.example.newyorktimes.utlis.Resource
import com.example.newyorktimes.utlis.Status
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MostPopularNewsViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val mostPopularNewsRepository = mockk<MostPopularNewsRepository>(relaxed = true)
    private val newsResultObserver = mockk<Observer<Resource<List<News>>>>(relaxed = true)
    private val sut = MostPopularNewsViewModel(mostPopularNewsRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut.newsResult.observeForever(newsResultObserver)
    }

    @After
    fun tearDown() {
        sut.newsResult.removeObserver(newsResultObserver)
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }


    @Test
    fun `When getMostPopularNews returns success then live data must be updated with non null value`() = testScope.runBlockingTest {
        //Given
        val newsList = mockk<News>(relaxed = true) {
            every { uri } returns "uri"
            every { subsection } returns "subsection"
        }
        val newsResult = mockk<NewsResult>() {
            every { results } returns listOf(newsList)
        }
        coEvery { mostPopularNewsRepository.getMostPopularNews("7") } returns newsResult

        //When
        sut.getMostPopularNews()

        //Then
        verify(exactly = 1) { newsResultObserver.onChanged(match { it.data?.isNotEmpty() == true }) }
    }

    @Test
    fun `When getMostPopularNews returns empty list then live data must be updated with error state`() = testScope.runBlockingTest {
        //Given
        val newsResult = mockk<NewsResult>() {
            every { results } returns emptyList()
        }
        coEvery { mostPopularNewsRepository.getMostPopularNews("7") } returns newsResult

        //When
        sut.getMostPopularNews()

        //Then
        verify(exactly = 1) { newsResultObserver.onChanged(match {
            it.status == Status.ERROR
        }) }
    }
}