package com.example.android.architecture.blueprints.statistics

/** @DrStart
 * write unit tests for the getActiveAndCompletedStats() function.
 * write a test for each of the following cases:
 * 1. If there's no tasks and one active task, then there are 0% completed tasks and 100% active tasks
 * 2. If there's 2 completed tasks and 3 active tasks, then there are 40% completed tasks and 60% active tasks
 * 3. If there's 1 completed tasks and 3 active tasks, then there are 25% completed tasks and 75% active tasks
 * 4. If there's 1 completed tasks and 0 active tasks, then there are 100% completed tasks and 0% active tasks
 * 5. If there's 0 completed tasks and 1 active tasks, then there are 0% completed tasks and 100% active tasks
 * 6. If there's null completed tasks and 1 active tasks, then there are 0% completed tasks and 100% active tasks
 * 7. If there's null completed tasks and null active tasks, then there are 0% completed tasks and 0% active tasks
 * 8. If there's 0 completed tasks and 0 active tasks, then there are 0% completed tasks and 0% active tasks
 * */

import com.example.android.architecture.blueprints.MainCoroutineRule
import org.junit.Assert.*
import org.junit.Test
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.statistics.getActiveAndCompletedStats
import org.hamcrest.core.Is.`is`
import org.junit.Rule

class StatisticsUtilsTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    //    If there is an empty list (emptyList()), then both percentages should be 0f.
    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZeroZero() {
        val tasks = emptyList<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    //    If there was an error loading tasks, the list will be null, then both percentages should be 0f.
    @Test
    fun getActiveAndCompletedStats_error_returnsZeroZero() {
        val tasks = null
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    //    if empty list of tasks
    @Test
    fun getActiveAndCompletedStats_error_returnsZeroHundred() {
        val tasks = null
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    //    if there's no tasks and one active task, then there are 100% active tasks and 0% completed tasks
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }

    //    if there's 2 completed tasks and 3 active tasks, then there are 40% completed tasks and 60% active tasks
    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

}