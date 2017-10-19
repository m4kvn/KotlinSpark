import spark.Spark.get

fun main(args: Array<String>) {
    get("/hello") { request, response ->
        val name = request.queryParams("name")
        "Hello, ${name ?: "world"}!"
    }
}
