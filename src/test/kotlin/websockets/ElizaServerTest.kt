package websockets

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*
import org.springframework.boot.web.server.LocalServerPort
import java.net.URI
import java.util.concurrent.CountDownLatch
import javax.websocket.*


@SpringBootTest(webEnvironment = RANDOM_PORT)
class ElizaServerTest {

    private lateinit var container: WebSocketContainer

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        container = ContainerProvider.getWebSocketContainer()
    }

    @Test
    fun onOpen() {
        val latch = CountDownLatch(3)
        val list = mutableListOf<String>()

        val client = ElizaOnOpenMessageHandler(list, latch)
        container.connectToServer(client, URI("ws://localhost:$port/eliza"))
        latch.await()
        assertEquals(3, list.size)
        assertEquals("The doctor is in.", list[0])
    }

    @Test
    fun onChat() {
        val latch = CountDownLatch(4)
        val list = mutableListOf<String>()

        val client = ElizaOnOpenMessageHandlerToComplete(list, latch)
        container.connectToServer(client, URI("ws://localhost:$port/eliza"))
        latch.await()
        // assertEquals(XXX, list.size) COMPLETE ME
        assertEquals(4, list.size)
        // assertEquals(XXX, list[XXX]) COMPLETE ME
        assertEquals("Can you think of a specific example?", list[3])
    }

}

@ClientEndpoint
class ElizaOnOpenMessageHandler(private val list: MutableList<String>, private val latch: CountDownLatch)  {
    @OnMessage
    fun onMessage(message: String) {
        list.add(message)
        latch.countDown()
    }
}

@ClientEndpoint
class ElizaOnOpenMessageHandlerToComplete(private val list: MutableList<String>, private val latch: CountDownLatch)  {

    @OnMessage
    fun onMessage(message: String, session: Session)  {
        list.add(message)
        latch.countDown()
        // if (COMPLETE ME) {
        //    COMPLETE ME
        // }
        if (latch.count == 1L){
            session.basicRemote.sendText("always")
        }
    }
}
