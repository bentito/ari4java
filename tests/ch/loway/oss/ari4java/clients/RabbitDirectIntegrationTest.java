package ch.loway.oss.ari4java.clients;

import ch.loway.oss.ari4java.tools.amqp.RabbitMQClient;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

/**
 * Test AMQP functionality as integration test against localhost rabbitmq server
 *
 */
public class RabbitDirectIntegrationTest {
    private static final String RABBITMQ_HOST = "localhost"; // rabbitmq running on localhost can be used for AMQP integration testing
    private static final String VIRTUAL_HOST = "/";
    private static final String QUEUE_NAME = "test_queue";
    private static final String EXCHANGE_TOPIC = "test_topic";
    private Connection connection;
    private RabbitMQClient rabbitMQClient;

    @Before
    public void setUp() throws IOException, TimeoutException {
        rabbitMQClient = new RabbitMQClient();
        rabbitMQClient.setQueueNameName(QUEUE_NAME);
        rabbitMQClient.setExchangeName(EXCHANGE_TOPIC);
        rabbitMQClient.setConnection(RABBITMQ_HOST, VIRTUAL_HOST);
    }

    @After
    public void tearDown() throws IOException {
        connection.close();
    }

    /**
     *  You should keep this integration test as @Ignore unless you run a rabbitmq server on localhost with default install
     * @throws IOException
     * @throws InterruptedException
     */
    @Ignore
    public void sendAndRecieveMessage() throws IOException, InterruptedException {
        String message = "Hello World";
        rabbitMQClient.send(message);
        String receivedMessage = rabbitMQClient.receive();
        assertEquals(message, receivedMessage);
    }
}

