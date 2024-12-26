import java.net.Socket
import java.util.*

class HTTPServer(
    ip: Int = 1,
    port: Int,
    private val requestHandler: RequestHandler = DefaultRequestHandler()
) : AbstractServer(port) {

    override fun handleClient(client: Socket) {
        try {
            val input = client.getInputStream()
            val output = client.getOutputStream()

            val request = parseRequest(input)
            println("Parsed request: $request")

            val response = requestHandler.handleRequest(request)
            println("Generated response: $response")

            output.write(response.toByteArray())
            output.flush()

            client.close()
        } catch (e: Exception) {
            println("Error handling client: ${e.message}")
            e.printStackTrace()
        } finally {
            println("Client disconnected.")
        }
    }

    private fun parseRequest(input: java.io.InputStream): HTTPRequest {
        val lines = mutableListOf<String>()
        Scanner(input).use { scanner ->
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                if (line.isBlank()) break
                lines.add(line)
            }
        }
        return HTTPRequest(lines)
    }
}