package net.devolutions.slauth;

import java.io.IOException;

public class Hotp extends RustObject {
    static {
        System.loadLibrary("slauth");
    }

    public Hotp(String uri) {
        this.raw = JNA.INSTANCE.hotp_from_uri(uri);
    }

    public String gen() {
        return JNA.INSTANCE.hotp_gen(raw);
    }

    public void inc() {
        JNA.INSTANCE.hotp_inc(raw);
    }

    public String toUri(String label, String issuer) {
        return JNA.INSTANCE.hotp_to_uri(raw, label, issuer);
    }

    public Boolean validateCurrent(String code) {
        return JNA.INSTANCE.hotp_validate_current(raw, code);
    }

    public Boolean verify(String code) {
        return JNA.INSTANCE.hotp_verify(raw, code);
    }

    @Override
    public void close() throws IOException {
        JNA.INSTANCE.hotp_free(raw);
    }
}
