package websockets

import org.glassfish.grizzly.Grizzly
import org.glassfish.tyrus.server.Server
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import java.util.logging.Level
import javax.websocket.*
import javax.websocket.server.ServerEndpoint

object ElizaBroker {
    private val LOGGER = Grizzly.logger(ElizaBroker::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        runServer()
    }

    private fun runServer() {
        val server = Server("localhost", 8080, "/websockets", HashMap(),
                ElizaBrokerEndpoint::class.java)
        try {
            Scanner(System.`in`).use { s ->
                server.start()
                LOGGER.info("Press 's' to shutdown the broker...")
                while (!s.hasNext("s"));
            }
        } catch (e: Exception) {
            LOGGER.log(Level.SEVERE, e.toString(), e)
        } finally {
            server.stop()
            LOGGER.info("Server stopped")
        }
    }
}

@ServerEndpoint(value = "/broker/{id}")
class ElizaBrokerEndpoint {

    @OnOpen
    @Throws(URISyntaxException::class, InterruptedException::class)
    fun onOpen(session: Session) {
        if (!uriExists(session.requestURI)) {
            LOGGER.info("[BROKER] New session URI ... " + session.requestURI)
            session.userProperties["pendingMessage"] = "INIT DOCTOR"
            sessions[session.requestURI] = session
            if (doctorExists()) {
                sendPendingMessages()
            }
        }
    }

    @OnMessage
    @Throws(URISyntaxException::class)
    fun onMessage(message: String, session: Session) {
        LOGGER.info("[BROKER] Message ... $message")
        if (session.requestURI.toString() == "/websockets/broker/doctor") {  // It is a doctor message
            val clientURI = URI(message.split(";").toTypedArray()[0])
            val decodedMessage = message.split(";").toTypedArray()[1]
            LOGGER.info("[BROKER] Message from doctor: Broker sends to $clientURI the message $decodedMessage")
            getSession(clientURI)!!.asyncRemote.sendText(decodedMessage)
        } else {                                                                      // It is a client message
            if (doctorExists()) {
                LOGGER.info("[BROKER] Message from client: Broker sends to the doctor the message ${session.requestURI};$message")
                getDoctor()?.asyncRemote?.sendText(encodeClientMessage(session.requestURI, message))
                sendPendingMessages()
            } else {
                LOGGER.info("[BROKER] Message from client: Doctor's ${session.requestURI};$message\" is postponed because there is still no doctor")
                session.userProperties["pendingMessage"] = message
                sessions[session.requestURI] = session
            }
        }
    }

    @OnClose
    fun onClose(session: Session, closeReason: CloseReason?) {
        LOGGER.info(String.format("Session %s closed because of %s", session.id, closeReason))
        sessions[session.requestURI] = null
    }

    @OnError
    fun onError(session: Session, errorReason: Throwable) {
        LOGGER.log(Level.SEVERE, String.format("Session %s closed because of %s", session.id, errorReason.javaClass.name), errorReason)
        sessions[session.requestURI] = null
    }

    private fun uriExists(uri: URI): Boolean {
        return sessions[uri] != null
    }

    @Throws(URISyntaxException::class)
    private fun doctorExists(): Boolean {
        return uriExists(URI("/websockets/broker/doctor"))
    }

    private fun getSession(uri: URI): Session? {
        return sessions[uri]
    }

    @Throws(URISyntaxException::class)
    private fun getDoctor(): Session? {
        return getSession(URI("/websockets/broker/doctor"))
    }
    // I'm so tired -> /websockets/broker/client;I'm so tired
    private fun encodeClientMessage(clientURI: URI, message: String): String {
        return "$clientURI;$message"
    }

    @Throws(URISyntaxException::class)
    private fun sendPendingMessages() {
        for ((_, session) in sessions.entries) {
            if (session != null) {
                val pendingMessage = session.userProperties.getOrDefault("pendingMessage", null) as String?
                session.userProperties["pendingMessage"] = null
                if (pendingMessage != null && !pendingMessage.startsWith("/websockets/broker/doctor")) {
                    LOGGER.info("[BROKER] Message from ${session.requestURI}: Broker sends to the doctor the pending message ${session.requestURI};$pendingMessage)")
                    getDoctor()!!.asyncRemote.sendText(encodeClientMessage(session.requestURI, pendingMessage))
                }
            }
        }
    }

    companion object {
        private val LOGGER = Grizzly.logger(ElizaBrokerEndpoint::class.java)
        private val sessions: MutableMap<URI, Session?> = HashMap()
    }
}