package xyz.luvily.lightningserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "capes")
public class Cape {

    @Id
    private String id;

    private final int identifier;
    private final byte[] texture;

    public Cape(int identifier, byte[] texture) {
        this.identifier = identifier;
        this.texture = texture;
    }

    public int getIdentifier() {
        return identifier;
    }

    public byte[] getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return "Cape (" + this.identifier + ")";
    }
}
