package net.devolutions.slauth;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

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

        String code1 = totp.gen();

        try {
            Thread.sleep(31000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String code2 = totp.genWith(31);

        assertEquals(code1, code2);
    }

    @Test
    public void u2fTest() {
        try {
            byte[] att_cert = Base64.getDecoder().decode("MIICODCCAd6gAwIBAgIJAKsa9WC9HvEuMAoGCCqGSM49BAMCMFoxDzANBgNVBAMMBlNsYXV0aDELMAkGA1UEBhMCQ0ExDzANBgNVBAgMBlF1ZWJlYzETMBEGA1UEBwwKTGF2YWx0cm91ZTEUMBIGA1UECgwLRGV2b2x1dGlvbnMwHhcNMTkwNzAyMTgwMTUyWhcNMzEwNjI5MTgwMTUyWjBaMQ8wDQYDVQQDDAZTbGF1dGgxCzAJBgNVBAYTAkNBMQ8wDQYDVQQIDAZRdWViZWMxEzARBgNVBAcMCkxhdmFsdHJvdWUxFDASBgNVBAoMC0Rldm9sdXRpb25zMFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE15PAnpUUIzbgKxD6RFuNMjjl/cD06vKRBtl0X/CiNzc3igTh1qcc00QICgAQUxdvHSn+DaSRki/kI9OJ8lkPGqOBjDCBiTAdBgNVHQ4EFgQU7iZ4JceUHOuWoMymFGm+ZBUmwwgwHwYDVR0jBBgwFoAU7iZ4JceUHOuWoMymFGm+ZBUmwwgwDgYDVR0PAQH/BAQDAgWgMCAGA1UdJQEB/wQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjAVBgNVHREEDjAMggpzbGF1dGgub3JnMAoGCCqGSM49BAMCA0gAMEUCIEdjPFNsund4FXs/1HpK4AXWQ0asfY6ERhNlg29VGS6pAiEAx8f2lrlVV1tASWbC/edTgH9JsCbANuXW/9FZcWHGl2E=".getBytes("UTF-8"));
            byte[] att_key = Base64.getDecoder().decode("MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgzgUSoDttmryF0C+ck4GppKwssha7ngah0dfezfTBzDOhRANCAATXk8CelRQjNuArEPpEW40yOOX9wPTq8pEG2XRf8KI3NzeKBOHWpxzTRAgKABBTF28dKf4NpJGSL+Qj04nyWQ8a".getBytes("UTF-8"));

            String json = "{\"appId\":\"https://login.devolutions.com/\",\"registerRequests\":[{\"challenge\":\"UzAxNE0yMTBWM1JDYzA1a1JqWndRUT09\",\"version\":\"U2F_V2\"}],\"registeredKeys\":[],\"requestId\":1,\"timeoutSeconds\":300,\"type\":\"u2f_register_request\"}";

            WebRequest web_r = new WebRequest(json);

            String origin = web_r.getOrigin();

            WebResponse rsp = web_r.register(origin, att_cert, att_key);

            SigningKey key = rsp.getSigningKey();

            System.out.println(key.getKeyHandle());
            System.out.println(key.toString());
            System.out.println(rsp.toJson());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidResponseTypeException e) {
            e.printStackTrace();
        }
    }
}
