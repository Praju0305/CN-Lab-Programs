import java.util.Scanner;

public class CN8L {
	public static void main(String[] args) {
		int noOfPackets = 4;
		int bucketSize = 10;
		int outputRate = 1;
		int currentBucketSize = 0;
		int packet, remBucketSize;

		Scanner in = new Scanner(System.in);
		for (int i = 0; i < noOfPackets; i++) {
			System.out.print("Enter the packet size of packet " + (i + 1) + " : ");
			packet = in.nextInt();

			remBucketSize = bucketSize - currentBucketSize;
			if (packet <= remBucketSize)
				currentBucketSize += packet;
			else {
				System.out.println("Bucket overflow.");
				System.out.println("Packet discarded.");
			}
			System.out.println("Current bucket size : " + currentBucketSize);
			currentBucketSize -= outputRate;
			System.out.println("After outgoing : " + currentBucketSize);
		}
	}
}