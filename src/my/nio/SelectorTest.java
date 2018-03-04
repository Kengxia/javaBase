package my.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Selector选择器
 */
public class SelectorTest {

    public static void main(String[] args) {
        try {
            ServerSocketChannel  acceptorSvr = ServerSocketChannel.open();
            acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"),8080));
            acceptorSvr.configureBlocking(false);

            Selector selector =  Selector.open();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
