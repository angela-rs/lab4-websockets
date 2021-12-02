package websockets

import org.glassfish.grizzly.Grizzly
import org.glassfish.tyrus.client.ClientManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.logging.Level
import java.util.logging.Logger
import javax.websocket.*
import javax.websocket.CloseReason.CloseCodes

/*object ElizaServer {
    private val LOGGER: Logger = Grizzly.logger(ElizaServer::class.java)
    private val LATCH = CountDownLatch(1)

    @JvmStatic
    fun main(args: Array<String>) {
        runClient()
    }

    private fun runClient() {
        val client = ClientManager.createClient()
        try {
            client.connectToServer(ElizaServerEndpoint::class.java, URI("ws://localhost:8080/websockets/broker/doctor"))
            LATCH.await()
        } catch (e: DeploymentException) {
            LOGGER.log(Level.SEVERE, e.toString(), e)
        } catch (e: IOException) {
            LOGGER.log(Level.SEVERE, e.toString(), e)
        } catch (e: URISyntaxException) {
            LOGGER.log(Level.SEVERE, e.toString(), e)
        } catch (e: InterruptedException) {
            LOGGER.log(Level.SEVERE, e.toString(), e)
        }
    }
}*/

@ClientEndpoint
@Component
class ElizaServerEndpoint {

    private val eliza = Eliza()

    /**
     * Successful connection
     *
     * @param session
     */
    @OnOpen
    fun onOpen(session: Session) {
        LOGGER.info("Server Connected ... Session ${session.id}")
    }

    /**
     * Connection closure
     *
     * @param session
     */
    @OnClose
    fun onClose(session: Session, closeReason: CloseReason) {
        LOGGER.info("Session ${session.id} closed because of $closeReason")
    }

    /**
     * Message received
     *
     * @param message
     */
    @OnMessage
    fun onMsg(message: String, session:Session) {
        LOGGER.info("[DOCTOR] Message ... $message")
        val client = message.split(";")[0]
        if (client != "/websockets/broker/doctor") {
            if (message.contains("INIT DOCTOR")) {
                LOGGER.info("[DOCTOR] Message from broker: Doctor sends to the broker the doctor's initial messages)")
                synchronized(session) {
                    with(session.basicRemote) {
                        sendText("$client;The doctor is in.")
                        sendText("$client;What's on your mind?")
                        sendText("$client;---")
                    }
                }
            } else {
                val currentLine = Scanner(message.lowercase(Locale.getDefault()))
                if (currentLine.findInLine("bye") == null) {
                    LOGGER.info("[DOCTOR] Message from broker: Doctor sends to the broker eliza's response)")
                    LOGGER.info("Server received \"$message\"")
                    synchronized(session) {
                        with(session.basicRemote) {
                            sendText(client + ";" +eliza.respond(currentLine))
                            sendText("$client;---")
                        }
                    }
                } else {
                    session.close(CloseReason(CloseCodes.NORMAL_CLOSURE, "Alright then, goodbye!"))
                }
            }
        }
    }

    @OnError
    fun onError(session: Session, errorReason: Throwable) {
        LOGGER.error("Session ${session.id} closed because of ${errorReason.javaClass.name}", errorReason)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ElizaServerEndpoint::class.java)
    }
}