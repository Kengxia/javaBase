package my.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  单线程Socket服务端
 */
public class SocketServerTest {

    private int port = 8000;// 端口

    private ServerSocket serverSocket;

    public SocketServerTest() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("waitting connet...");
    }

    public void service() throws IOException {
        Socket socket = null;
        String msg = null;
        // 不停的监听，直到接收到请求
        while (true) {
            try {
                socket = serverSocket.accept(); // 准备接受连接
                System.out.println("new connection: " + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); // 输入流
                PrintWriter writer = new PrintWriter(socket.getOutputStream()); // 输出流
                while ((msg = reader.readLine()) != null) { // 接收消息
                    System.out.println("receive msg:" + msg);
                    writer.println(msg); // 发送消息
                    writer.flush(); // 注意，在使用缓冲流在发送消息的时候最好进行强制刷新，否则，可能会由于缓冲区不满而暂时不发送消息
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

    }
    public static void main(String[] args) throws Exception {
        new SocketServerTest().service();
    }

}
