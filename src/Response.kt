sealed class Response(
    val statusCode: Int,
    val statusMessage: String,
    val body: String
) {
    class Ok(body: String) : Response(200, "OK", body)
    class NotFound(body: String) : Response(404, "Not Found", body)

    fun toByteArray() = buildString {
        append("HTTP/1.1 $statusCode $statusMessage\n")
        append("Server: Kotlin Server\n")
        append("Content-Type: text/plain\n")
        append("Content-Length: ${body.toByteArray().size}\n")
        append("Connection: keep-alive\n")
        append("\n")
        append(body)
    }.toByteArray()
}