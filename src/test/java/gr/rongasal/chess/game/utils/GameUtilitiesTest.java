package gr.rongasal.chess.game.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameUtilitiesTest {

    @Test
    void TestGetCharForNumber() {
        List<String> chars = IntStream.range(1, 27).mapToObj(GameUtilities::getCharForNumber).collect(Collectors.toList());
        assertThat(chars).containsExactly("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X", "Y", "Z");
    }

    @Test
    void TestGetNumberForCharacter() {
        List<Integer> numbers = Stream.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z").map(
                GameUtilities::getNumberForChar
        ).collect(Collectors.toList());

        assertThat(numbers).containsExactly(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26);
    }
}
