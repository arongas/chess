package gr.rongasal.chess.game.algorithm;

import gr.rongasal.chess.game.ChessCell;
import gr.rongasal.chess.game.pieces.Knight;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PossiblePathsAlgorithmTest {

    private static Stream<Arguments> provideAlgorithmInput() {
        return Stream.of(
                Arguments.of(toCell("A", 1), toCell("G", 7), 4,
                        List.of(
                                List.of(toCell("A",1), toCell("C",2), toCell("E",3), toCell("F",5), toCell("G",7)),
                                List.of(toCell("A",1), toCell("C",2), toCell("D",4), toCell("F",5), toCell("G",7)),
                                List.of(toCell("A",1), toCell("C",2), toCell("D",4), toCell("E",6), toCell("G",7)),
                                List.of(toCell("A",1), toCell("B",3), toCell("D",4), toCell("F",5), toCell("G",7)),
                                List.of(toCell("A",1), toCell("B",3), toCell("D",4), toCell("E",6), toCell("G",7)),
                                List.of(toCell("A",1), toCell("B",3), toCell("C",5), toCell("E",6), toCell("G",7))
                        )
                ),

                Arguments.of(toCell("A", 1), toCell("G", 7), 3,null)
        );
    }

    private static ChessCell toCell(String x, int y) {
        return new ChessCell(x, y);
    }

    @ParameterizedTest
    @MethodSource("provideAlgorithmInput")
    void walk(ChessCell from, ChessCell to, int exploreDepth, List<List<Node>> expectedResult) {
        ConnectionProvider piece = new Knight();
        @SuppressWarnings("unchecked")
        PossiblePathsAlgorithm possiblePathsAlgorithm = new PossiblePathsAlgorithm(piece, exploreDepth);
        Optional<List<List<Node>>> walkResult = possiblePathsAlgorithm.walk(from, to);
        if (CollectionUtils.isEmpty(expectedResult)) {
            assertThat(walkResult).isNotPresent();
        } else {
            assertThat(walkResult).isPresent().contains(expectedResult);
        }
    }
}
