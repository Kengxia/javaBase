package my.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class SocketServerTest1 {

    private static int LISTEN_PORT=8000;

    private ServerSocket serverSocket;

    public SocketServerTest1() throws IOException {
        serverSocket =  new ServerSocket(LISTEN_PORT);
    }

    public void service(){
        Socket socket = null;
        while (true){
            try {
                System.out.println("waitting connet...");
                socket = serverSocket.accept();
                new Handler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        try {
            new SocketServerTest1().service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("new connection accepted:" + socket.getInetAddress() + ":" + socket.getPort());
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String msg = null;
                while ((msg = reader.readLine()) != null) {
                    System.out.println("from " + socket.getInetAddress() + ":" + socket.getPort() + ", receive msg:"
                            + msg);
                    writer.println(msg);
                    writer.flush();
                    if ("close".equals(msg)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
