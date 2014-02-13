package Connection;


public class Message implements java.io.Serializable {
    public byte[] sender;
    public String receiver;
    public byte[] message;
    public byte[] key;
}

