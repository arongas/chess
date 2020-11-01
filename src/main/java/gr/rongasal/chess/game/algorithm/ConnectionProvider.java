package gr.rongasal.chess.game.algorithm;

import java.util.List;

public interface ConnectionProvider<T extends Node> {
    List<T> next(T from);
}
