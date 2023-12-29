import java.util.Scanner;

public class CN8T extends Thread {
    public static void main(String[] args) {
        int tokens = 0;
        int rate = 3;
        int capacity = 10;
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of requests");
        n = in.nextInt();
        System.out.println("Enter the number of packets per request");
        int requests[] = new int[n];
        for (int i = 0; i < n; i++)
            requests[i] = in.nextInt();
        for (int i = 0; i < n; i++) {
            tokens = Math.min(tokens + rate, capacity);
            if (requests[i] <= tokens) {
                tokens -= requests[i];
                System.out.println("Packet " + (i + 1) + " is transmitted");
            } else
                System.out.println("Packet " + (i + 1) + " is discarded");
            System.out.println("Current number of tokens : " + tokens);
        }
    }
}