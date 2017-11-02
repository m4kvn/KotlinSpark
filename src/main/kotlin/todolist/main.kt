package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import spark.Spark.*
import todolist.controller.TaskController

fun main(args: Array<String>) {
    val objectMapper = ObjectMapper().registerKotlinModule()
    val jsonTransFormer = JsonTransFormer(objectMapper)
    val taskRepository = TaskRepository()
    val taskController = TaskController(objectMapper, taskRepository)

    path("/tasks") {
        get("", taskController.index(), jsonTransFormer)
        post("", taskController.create(), jsonTransFormer)
        get("/:id", taskController.show(), jsonTransFormer)
        delete("/:id", taskController.destroy(), jsonTransFormer)
    }
}
