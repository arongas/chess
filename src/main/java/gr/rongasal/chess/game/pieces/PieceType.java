package gr.rongasal.chess.game.pieces;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PieceType {
    KNIGHT("Knight");

    private String name;

    @JsonCreator
    public static PieceType getEnum(String type) {
        if (type != null) {
            for (PieceType da : values()) {
                if ((da.name != null) && da.name.equalsIgnoreCase(type))
                    return da;
                if (da.name().equalsIgnoreCase(type))
                    return da;
            }
        }
        return null;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
