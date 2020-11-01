package gr.rongasal.chess.game.algorithm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SearchAlgorithmType {
    POSSIBLE_PATHS("AllPaths");

    private String name;

    @JsonCreator
    public static SearchAlgorithmType getEnum(String type) {
        if (type != null) {
            for (SearchAlgorithmType da : values()) {
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
