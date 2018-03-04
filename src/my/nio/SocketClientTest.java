package my.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 单线程socket客户端
 */
public class SocketClientTest {

    private String host = "127.0.0.1";// 要发送给服务端的ip

    private int port = 8000;// 要发送给服务端的端口

    private Socket socket;

    public SocketClientTest() throws Exception {
        socket = new Socket(host, port);// 构造Socket客户端，并与连接服务端
    }


    public void talk() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            // 读取本地控制台的消息
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = localReader.readLine()) != null) {
                writer.println(msg);
                writer.flush();
                System.out.println("send msg:" + reader.readLine());
                if ("close".equals(msg)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SocketClientTest().talk();
    }
}
