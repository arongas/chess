package gr.rongasal.chess.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChessCellTest {

    private static Stream<Arguments> providePossitions() {
        return Stream.of(
                Arguments.of(0, 0, false),

                Arguments.of(0, 1, false), Arguments.of(0, 2, false), Arguments.of(0, 3, false),Arguments.of(0, 4, false),
                Arguments.of(0, 5, false), Arguments.of(0, 6, false), Arguments.of(0, 7, false),Arguments.of(0, 8, false),

                Arguments.of(1, 1, true), Arguments.of(1, 2, true), Arguments.of(1, 3, true),Arguments.of(1, 4, true),
                Arguments.of(1, 5, true), Arguments.of(1, 6, true), Arguments.of(1, 7, true),Arguments.of(1, 8, true),

                Arguments.of(8, 1, true), Arguments.of(8, 2, true), Arguments.of(8, 3, true),Arguments.of(8, 4, true),
                Arguments.of(8, 5, true), Arguments.of(8, 6, true), Arguments.of(8, 7, true),Arguments.of(8, 8, true),

                Arguments.of(9, 1, false), Arguments.of(9, 2, false), Arguments.of(9, 3, false),Arguments.of(9, 4, false),
                Arguments.of(9, 5, false), Arguments.of(9, 6, false), Arguments.of(9, 7, false),Arguments.of(9, 8, false),

                Arguments.of(9, 9, false)
        );
    }

    @ParameterizedTest
    @MethodSource("providePossitions")
    void checkPositionValidation(int x, int y, boolean valid) {
        ChessCell chessCell = new ChessCell(x, y);
        assertThat(chessCell.isValid()).isEqualTo(valid);
    }
}
