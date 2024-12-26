data class HTTPRequest(
    var method: String = "",
    var uri: String = "",
    val httpVersion: String = "1.1"
) {
    constructor(data: List<String>) : this() {
        data.firstOrNull()?.split(" ")?.let { parts ->
            if (parts.size >= 2) {
                method = parts[0]
                uri = parts[1]
            }
        }
    }
}