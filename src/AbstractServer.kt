import java.net.ServerSocket
import java.net.Socket


abstract class AbstractServer(private val port: Int) {
    fun run() {
        try {
            createServerSocket().use { server ->
                println("Server running on port ${server.localPort}")
                while (true) {
                    val client = server.accept()
                    println("Client connected: ${client.inetAddress.hostAddress}")
                    handleClient(client)
                }
            }
        } catch (e: Exception) {
            handleError(e)
        }

    }
    protected open fun createServerSocket() = ServerSocket(port)
    protected abstract fun handleClient(client: Socket)
    protected open fun handleError(e: Exception) = e.printStackTrace()
}