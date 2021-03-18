package xyz.luvily.lightningserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "capes")
public class Cape {

    @Id
    private String id;

    private final int identifier;
    private final byte[] texture;

    public Cape(int identifier, byte[] texture) {
        this.identifier = identifier;
        this.texture    = texture;
    }

    public int getIdentifier() {
        return identifier;
    }

    public byte[] getTexture() {
        return texture;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cape{" +
                "id='" + id + '\'' +
                ", identifier=" + identifier +
                ", texture=" + Arrays.toString(texture) +
                '}';
    }
}
