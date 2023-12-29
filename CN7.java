//RSA

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

class RSA {
    BigInteger prk, puk, mod;

    void getKeys(int bitlen) {
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(bitlen, r);
        BigInteger q = BigInteger.probablePrime(bitlen, r);
        mod = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        puk = BigInteger.probablePrime(bitlen / 2, r);
        prk = puk.modInverse(phi);
    }

    BigInteger encrypt(BigInteger m) {
        return m.modPow(puk, mod);
    }

    BigInteger decrypt(BigInteger c) {
        return c.modPow(prk, mod);
    }

}

class CN7 {
    public static void main(String[] args) {
        RSA rsa = new RSA();
        rsa.getKeys(1024);
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the message to be encrypted : ");
        BigInteger m = new BigInteger(in.next().getBytes());
        BigInteger c = rsa.encrypt(m);
        System.out.println("Encrypted message : " + c.longValue());
        BigInteger d = rsa.decrypt(c);
        System.out.println("Decrypted message : " + new String(d.toByteArray()));

    }
}
