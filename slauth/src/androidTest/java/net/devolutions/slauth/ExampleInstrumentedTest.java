package net.devolutions.slauth;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void totpUris() {
        String baseUri = "otpauth://totp/john.doe@email.com?secret=HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ&algorithm=SHA1&digits=6&period=30&issuer=Slauth";
        Totp totp = new Totp(baseUri);

        String genUri = totp.toUri("john.doe@email.com", "Slauth");

        System.out.println(genUri);

        assertEquals(baseUri, genUri);
    }
}
