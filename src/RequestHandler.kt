interface RequestHandler {
    fun handleRequest(request: HTTPRequest): Response
}
