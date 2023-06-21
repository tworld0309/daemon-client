package daemon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class App {

    public static void main(String[] args) throws Exception {

        Socket socket = null;

        try {
            socket = new Socket();
            System.out.println("\n[ Request ... ]\n");
            socket.connect(new InetSocketAddress("localhost", 9999));
            System.out.println("\n[ Success ... ]\n");

            byte[] bytes = null;
            String message = null;
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
//            message = "0078IF0001      NEXT0112345678901234567890123456789012INQ0001 2021012920210129";
//            message = "0078IF0002      NEXT0112345678901234567890123456789012INQ0001 2021012920210129";
//            message = args[0];
            message = "IF0002      NEXT0112345678901234567890123456789099INQ0001 2021012920210129";
            bytes = message.getBytes("UTF-8");

            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            dos.flush();

            System.out.println("\n[ Data Send Success ]\n" + message);

            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            int leng = dis.readInt();
            System.out.println("leng: " + leng);

            if (leng > 0) {
                byte input[] = new byte[leng];
                dis.readFully(input, 0, leng);

                message = new String(input, 0, leng, "UTF-8");
                System.out.println("\n[ Data Received ]\n" + message);
            }

            os.close();
            is.close();

            socket.close();
            System.out.println("\n[ Socket closed ]\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
                System.out.println("\n[ Socket closed ]\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
