package gr.rongasal.chess.game.utils;

public class GameUtilities {
    public static final int BOARD_SIZE = 8;

    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

    public static int getNumberForChar(String character) {
        if (character == null || character.length() != 1) {
            return -1;
        } else {
            char charact = character.toCharArray()[0];
            int Alpha = 'A'; //for upper case
            int Zeta = 'Z'; //for upper case
            if (charact <= Zeta & charact >= Alpha) {
                return charact - Alpha + 1;
            } else {
                return -1;
            }
        }
    }
}
