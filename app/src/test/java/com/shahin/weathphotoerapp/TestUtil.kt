package com.shahin.weathphotoerapp

import com.shahin.weathphotoerapp.common.resource.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert

@ExperimentalCoroutinesApi
fun <T> StateFlow<T>.test(testFunction: (list: List<T>) -> Unit) = runBlockingTest {
    val list = mutableListOf<T>()
    val job = launch {
        toList(list)
    }
    testFunction(list)
    job.cancel()
}


fun List<*>.assertInitializedAndLoading() {
    Assert.assertTrue(this[0] is Resource.Initial<*>)
    Assert.assertTrue(this[1] is Resource.Loading<*>)
}