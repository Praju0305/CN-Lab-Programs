

public class CN9 {
    public static void main(String args[]) {
        int maxPac = 20;
        int queSize = 10;

        double maxPro = 0.7;
        double minPro = 0.3;

        int queLen = 0;
        double dropPro = minPro;
        for (int i = 0; i < maxPac; ++i) {
            if (queLen == queSize) {
                System.out.println("Packet dropped ( Queue Full ) ");
                dropPro = minPro;
            } else if (Math.random() < dropPro) {
                System.out.println("Packet dropped ( Random ) ");
                dropPro += (maxPro - minPro) / (maxPac - 1);
            } else {
                System.out.println("Packet Accepted");
                queLen++;
                dropPro = minPro;
            }
        }

    }

}
