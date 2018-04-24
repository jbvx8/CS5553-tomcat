package binarycodec;

public interface IBinaryCodec {
    public byte[] decodeBase64(final byte[] base64Data);
    
    public byte[] decodeBase64(
            final byte[] base64Data, final int off, final int len);
    
    public byte[] decodeBase64(final String base64String);
    
    public byte[] encodeBase64(final byte[] binaryData);
    
    public byte[] encodeBase64(final byte[] binaryData, final boolean isChunked);
    
    public byte[] encodeBase64(final byte[] binaryData, final boolean isChunked, final boolean urlSafe);
    
    public byte[] encodeBase64(final byte[] binaryData, final boolean isChunked,
            final boolean urlSafe, final int maxResultSize);
    
    public String encodeBase64String(final byte[] binaryData);
}
