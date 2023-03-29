package com.shahin.weathphotoerapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


interface ICoroutineDispatchers {
    val IO: CoroutineDispatcher
    val MAIN: CoroutineDispatcher
    val DEFAULT: CoroutineDispatcher
}

/**
 * Wrapper class , wraps the [Dispatchers]
 * for the real usage inside the code
 */
class AppCoroutineDispatchers(
    override val IO: CoroutineDispatcher = Dispatchers.IO,
    override val MAIN: CoroutineDispatcher = Dispatchers.Main,
    override val DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : ICoroutineDispatchers

