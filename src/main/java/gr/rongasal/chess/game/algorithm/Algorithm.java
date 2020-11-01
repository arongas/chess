package gr.rongasal.chess.game.algorithm;

import java.util.List;
import java.util.Optional;

public interface Algorithm {
    Optional<List<List<Node>>> walk(Node start, Node target);
}
