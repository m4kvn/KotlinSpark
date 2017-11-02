package todolist.controller

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Request
import spark.Route
import spark.Spark.halt
import todolist.TaskRepository
import todolist.model.Task
import todolist.model.TaskCreateRequest
import todolist.readValue

class TaskController(private val objectMapper: ObjectMapper,
                     private val taskRepository: TaskRepository) {

    private val Request.task: Task?
        get() {
            val id = params("id").toLongOrNull()
            return id?.let(taskRepository::findById)
        }

    fun index(): Route = Route { _, _ ->
        taskRepository.findAll()
    }

    fun create(): Route = Route { req, res ->
        val request: TaskCreateRequest = objectMapper.readValue(req.bodyAsBytes()) ?: throw halt(400)
        val task = taskRepository.create(request.content)
        res.status(201)
        task
    }

    fun show(): Route = Route { req, _ ->
        req.task ?: throw halt(404)
    }

    fun destroy(): Route = Route { req, res ->
        val task = req.task ?: throw halt(404)
        taskRepository.delete(task)
        res.status(204)
    }
}
