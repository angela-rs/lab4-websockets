package websockets

import org.glassfish.grizzly.Grizzly
import org.glassfish.tyrus.server.Server
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException
import java.lang.String.format
import java.net.URI
import java.net.URISyntaxException
import java.util.concurrent.CountDownLatch
import javax.websocket.*
import javax.websocket.DeploymentException

class ElizaTest {
    private lateinit var container: WebSocketContainer

    private var broker: Server? = null

    @BeforeEach
    @Throws(DeploymentException::class, URISyntaxException::class, IOException::class)
    fun setup() {
        container = ContainerProvider.getWebSocketContainer()
        broker = Server("localhost", 8080, "/websockets", HashMap(), ElizaBrokerEndpoint::class.java)
        broker!!.start()
        //val doctor = ClientManager.createClient()
        container.connectToServer(ElizaServerEndpoint::class.java, URI("ws://localhost:8080/websockets/broker/doctor"))
    }

    @AfterEach
    fun close() {
        broker!!.stop()
    }

    @Test
    @Throws(DeploymentException::class, IOException::class, URISyntaxException::class, InterruptedException::class)
    fun onOpen() {
        Thread.sleep(500)
        val latch = CountDownLatch(3)
        val list: MutableList<String> = ArrayList()
        val configuration = ClientEndpointConfig.Builder.create().build()
        container.connectToServer(object : Endpoint() {
            override fun onOpen(session: Session, config: EndpointConfig?) {
                session.addMessageHandler(ElizaOnOpenMessageHandler(list, latch))
            }
        }, configuration, URI("ws://localhost:8080/websockets/broker/client"))

        latch.await()
        assertEquals(3, list.size)
        assertEquals("The doctor is in.", list[0])
    }

    @Test
    @Throws(DeploymentException::class, IOException::class, URISyntaxException::class, InterruptedException::class)
    fun onChat() {
        Thread.sleep(500)
        val latch = CountDownLatch(5)
        val list: MutableList<String> = ArrayList()
        val configuration = ClientEndpointConfig.Builder.create().build()
        val session = container.connectToServer(object : Endpoint() {
            override fun onOpen(session: Session, config: EndpointConfig?) {
                session.addMessageHandler(ElizaOnOpenMessageHandler(list, latch))
            }
        }, configuration, URI("ws://localhost:8080/websockets/broker/client"))

        session.basicRemote.sendText("always")
        latch.await()

        // assertEquals(XXX, list.size) COMPLETE ME
        assertEquals(5, list.size)

        // assertEquals(XXX, list[XXX]) COMPLETE ME
        assertEquals("The doctor is in.", list[0])
        assertEquals("What's on your mind?", list[1])
        assertEquals("---", list[2])

        assertEquals("Can you think of a specific example?", list[3])
        assertEquals("---", list[4])
    }

    private class ElizaOnOpenMessageHandler(private val list: MutableList<String>, private val latch: CountDownLatch?) : MessageHandler.Whole<String?> {
        override fun onMessage(message: String?) {
            LOGGER.info(format("Client received \"%s\"", message))
            if (message != null) {
                list.add(message)
            }
            latch!!.countDown()
        }
    }

    companion object {
        private val LOGGER = Grizzly.logger(ElizaTest::class.java)
    }
}