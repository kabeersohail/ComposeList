package com.example.composelist

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * 0, 1, 1,
 *
 */
class ExampleUnitTest {

    private fun getFibonacci(limit: Int): List<Int> {

        if(limit == 0) return emptyList()

        if(limit == 1) return listOf(0)

        if(limit == 2) return listOf(0, 1)

        val list: MutableList<Int> = mutableListOf(0,1)

        for(i in 2 until limit) {
            list.add(i, list[i-1] + list[i-2])
        }

        return list
    }

    private fun getListFromRecursion(limit: Int): List<Int> {
        val mutableList: MutableList<Int> = mutableListOf()
        for(i in 0 .. limit) {
            mutableList.add(getNthFibonacciRecursion(i))
        }
        return mutableList
    }

    private fun getNthFibonacciRecursion(limit: Int): Int =
        when (limit) {
            0 ->  0
            1 -> 1
            else -> getNthFibonacciRecursion(limit - 1) + getNthFibonacciRecursion(limit - 2)
        }

    private fun addition(x: Int, y: Int) = x + y

    @Test
    fun `should list from fibonacci series recursion`() {
        /**
         * Given
         */
        val limit = 25
        val expectedResult = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025)

        /**
         * When
         */
        val actualResult = getListFromRecursion(limit)

        /**
         * Then
         */

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return nth fibonacci series`() {
        /**
         * Given
         */
        val limit: Int = 25
        val expectedResult = 75025

        /**
         * When
         */
        val actualResult = getNthFibonacciRecursion(limit)

        /**
         * Then
         */

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return first n fibonacci using recursion`() {
        /**
         * Given
         */
        val limit: Int = 4
        val expectedResult = 3

        /**
         * When
         */
        val actualResult = getNthFibonacciRecursion(limit)

        /**
         * Then
         */

        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun `should return first n fibonacci series using normal logic`() {
        /**
         * Given
         */

        val limit: Int = 0
        val expectedResult = emptyList<Int>()

        /**
         * When
         */
        val actualResponse: List<Int> = getFibonacci(limit)

        /**
         * Then
         */

        assertEquals(expectedResult, actualResponse)
    }

    @Test
    fun addition_isCorrect() {

        /**
         * Given
         */
        val firsValue = 10

        val secondValue = 20

        /**
         * When
         */
        val actualResponse: Int = addition(firsValue, secondValue)

        /**
         * Then
         */
        assertEquals(30, actualResponse)
    }
}