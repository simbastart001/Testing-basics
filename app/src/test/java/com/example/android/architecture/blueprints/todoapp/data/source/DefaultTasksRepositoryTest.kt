package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test


class DefaultTasksRepositoryTest {
    //    add variables for the repository and the fake data source
    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }
    private val newTask = Task("New Title", "New Description")
    private val activeTask = Task("Active Task", "Active Description")
    private val completedTask = Task("Completed Task", "Completed Description", true)


    private lateinit var tasksRepository: DefaultTasksRepository

    @Before
    fun createRepository() {
        //        initialize the repository with remote and local data sources
        val tasksRemoteDataSource = FakeDatasource(remoteTasks.toMutableList())
        val tasksLocalDataSource = FakeDatasource(localTasks.toMutableList())
        //        get a reference to the class under test
        tasksRepository = DefaultTasksRepository(
            tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined
        )

    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTasks_requestsAllTasksFromRemoteDataSource() = runBlockingTest {
        // When tasks are requested from the tasks repository
        val tasks = tasksRepository.getTasks(true) as Result.Success
        // Then tasks are loaded from the remote data source
        assertThat(tasks.data, IsEqual(remoteTasks))
    }
}
