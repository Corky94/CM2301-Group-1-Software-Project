package Connection;

import java.io.Serializable;
import javax.crypto.SecretKey;

public class SessionKey implements Serializable {

    private byte[] encryptedSessionKey;
    private byte[] ivSpec;
    private SecretKey sessionKey;

    public SessionKey() {

    }

    public SessionKey(byte[] key, byte[] iv) {
        encryptedSessionKey = key;
        ivSpec = iv;
    }

    public SecretKey getKey() {
        return sessionKey;
    }

    public byte[] getEncryptedKey() {
        return encryptedSessionKey;
    }

    public byte[] getIvSpec() {
        return ivSpec;
    }

    public void setKey(SecretKey key) {
        sessionKey = key;
    }

    public void setEncryptedKey(byte[] key) {
        encryptedSessionKey = key;
    }

    public void setIvSpec(byte[] iv) {
        ivSpec = iv;
    }

    public void deleteKey() {
        sessionKey = null;
    }

    public void deleteEncryptedKey() {
        encryptedSessionKey = null;
    }
}
