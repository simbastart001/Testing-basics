package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.lifecycle.LiveData
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource

class FakeDatasource(var tasks: MutableList<Task>? = mutableListOf()) : TasksDataSource {
    override suspend fun getTasks(): Result<List<Task>> {
        tasks?.let { return Result.Success(ArrayList(it)) }
        return Result.Error(Exception("Tasks not found"))
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        tasks?.firstOrNull { it.id == taskId }?.let { return Result.Success(it) }
        return Result.Error(Exception("Task not found"))
    }

    override suspend fun saveTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun completeTask(task: Task) {
        TODO()
    }

    override suspend fun completeTask(taskId: String) {
        TODO()
    }

    override suspend fun activateTask(task: Task) {
        TODO()
    }

    override suspend fun activateTask(taskId: String) {
        TODO()
    }

    override suspend fun clearCompletedTasks() {
        TODO()
    }

    override suspend fun deleteAllTasks() {
        tasks?.clear()
    }

    override suspend fun deleteTask(taskId: String) {
        TODO()
    }

    override suspend fun refreshTasks() {
        TODO()
    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        TODO()
    }

    override fun observeTask(taskId: String): LiveData<Result<Task>> {
        TODO()
    }

    override suspend fun refreshTask(taskId: String) {
        TODO()
    }
}