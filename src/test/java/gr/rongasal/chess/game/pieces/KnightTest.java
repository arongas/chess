package gr.rongasal.chess.game.pieces;

import gr.rongasal.chess.game.ChessCell;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @SuppressWarnings("unchecked")
    private static Stream<Arguments> providePossitions() {
        return Stream.of(
                Arguments.of(createChessCell(1, 1), createExpectedSolutions(
                        Pair.of(3, 2),
                        Pair.of(2, 3))),

                Arguments.of(createChessCell(3, 3), createExpectedSolutions(
                        Pair.of(5, 4),
                        Pair.of(5, 2),
                        Pair.of(1, 4),
                        Pair.of(1, 2),
                        Pair.of(4, 5),
                        Pair.of(2, 5),
                        Pair.of(4, 1),
                        Pair.of(2, 1)
                )),

                Arguments.of(createChessCell(8, 8), createExpectedSolutions(
                        Pair.of(6, 7),
                        Pair.of(7, 6)
                        )),

                Arguments.of(createChessCell(1, 8), createExpectedSolutions(
                        Pair.of(7, 3),
                        Pair.of(6, 2)
                        )),

                Arguments.of(createChessCell(8, 1), createExpectedSolutions(
                        Pair.of(2, 6),
                        Pair.of(3, 7)
                ))
        );
    }

    private static ChessCell createChessCell(int x, int y) {
        return new ChessCell(x, y);
    }

    @SuppressWarnings("unchecked")
    private static List<ChessCell> createExpectedSolutions(Pair<Integer, Integer>... possitions) {
        List<ChessCell> chessCells = new ArrayList<>();
        for (Pair<Integer, Integer> possition : possitions) {
            chessCells.add(new ChessCell(possition.getRight(), possition.getLeft()));
        }
        return chessCells;
    }

    @ParameterizedTest
    @MethodSource("providePossitions")
    void testNextPossitionsOfKnight(ChessCell current, List<ChessCell> expectedPossible) {
        Piece knight = new Knight();

        List<ChessCell> possible = knight.next(current);
        assertThat(possible).containsExactlyInAnyOrder(expectedPossible.toArray(new ChessCell[0]));
    }
}
