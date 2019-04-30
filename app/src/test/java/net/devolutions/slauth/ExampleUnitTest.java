package net.devolutions.slauth;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void otp_auth() {
        Hotp hotp = new Hotp("otpauth://hotp/OTPAuth?secret=T2ULXKZHVJIXHXSKTVGDUFAIDAVJJ7AAXNP5F6RWOHCOB7P7JDJY7VG6A5VMJUCZ&algorithm=SHA1&digits=6&counter=0");

        for (int i = 0; i < 10; i++) {
            System.out.println("Hotp " + hotp.gen());
            hotp.inc();
        }

        System.out.println("Hotp " + hotp.toUri("Patate", "Poil"));

        Totp totp = new Totp("otpauth://totp/OTPAuth?secret=T2ULXKZHVJIXHXSKTVGDUFAIDAVJJ7AAXNP5F6RWOHCOB7P7JDJY7VG6A5VMJUCZ&algorithm=SHA1&digits=6&period=10");

        for (int i = 0; i < 10; i++) {
            System.out.println("Totp " + totp.gen());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Totp " + totp.toUri("Patate", "Poil"));
    }
}