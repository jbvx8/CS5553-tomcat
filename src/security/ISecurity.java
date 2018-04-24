package security;

public interface ISecurity {
    public byte[] digestMD5(byte[]... input);
    public String encode(byte[] binaryData);

}
