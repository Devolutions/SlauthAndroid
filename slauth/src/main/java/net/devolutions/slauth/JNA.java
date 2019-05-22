package net.devolutions.slauth;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface JNA extends Library {
    String JNA_LIBRARY_NAME = "slauth";

    JNA INSTANCE = Native.load(JNA_LIBRARY_NAME, JNA.class);

    Pointer hotp_from_uri(String uri);

    void hotp_free(Pointer hotp);

    String hotp_gen(Pointer hotp);

    void hotp_inc(Pointer hotp);

    String hotp_to_uri(Pointer hotp, String label, String issuer);

    Boolean hotp_validate_current(Pointer hotp, String code);

    Boolean hotp_verify(Pointer hotp, String code);

    void totp_free(Pointer totp);

    Pointer totp_from_uri(String uri);

    String totp_gen(Pointer totp);

    String totp_gen_with(Pointer totp, long elapsed);

    String totp_to_uri(Pointer totp, String label, String issuer);

    Boolean totp_validate_current(Pointer totp, String code);

    Boolean totp_verify(Pointer totp, String code);
}
