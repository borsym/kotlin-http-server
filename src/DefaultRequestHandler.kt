class DefaultRequestHandler : RequestHandler {
    override fun handleRequest(request: HTTPRequest): Response {
        return when (request.method.uppercase()) {
            "GET" -> handleGet(request)
            "POST" -> handlePost(request)
            else -> handleNotFound()
        }
    }

    private fun handleGet(request: HTTPRequest): Response =
        Response.Ok("GET request received for ${request.uri}")

    private fun handlePost(request: HTTPRequest): Response =
        Response.Ok("POST request received for ${request.uri}")

    private fun handleNotFound(): Response =
        Response.NotFound("404 Not Found")
}