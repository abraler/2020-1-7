import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ExecutorService pools=Executors.newFixedThreadPool(10);
        ServerSocket serverSocket=new ServerSocket(8080);
        while(true){
            Socket socket=serverSocket.accept();
            pools.execute(new Task(socket));
            System.out.println("ddddd");
        }
    }
}
