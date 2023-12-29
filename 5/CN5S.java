import java.net.*;
import java.util.Scanner;
import java.io.*;

public class CN5S {
    public static void main(String args[]) throws Exception {
        ServerSocket sersoc = new ServerSocket(4000);
        System.out.println("Server Connected, Waiting for Client");
        Socket sock = sersoc.accept();
        System.out.println("Connection Successfull, Waiting for filename ...");
        InputStream istream = sock.getInputStream();
        Scanner sc = new Scanner(istream);
        String fname = sc.nextLine();
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        try {
            sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                pwrite.println(sc.nextLine());
            }
        } catch (Exception e) {
            pwrite.println("File name does not exists");
        } finally {
            System.out.println("Closing Connection");
        }
    }
}