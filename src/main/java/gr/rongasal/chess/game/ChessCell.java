package gr.rongasal.chess.game;

import gr.rongasal.chess.game.algorithm.Node;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static gr.rongasal.chess.game.utils.GameUtilities.BOARD_SIZE;
import static gr.rongasal.chess.game.utils.GameUtilities.getCharForNumber;
import static gr.rongasal.chess.game.utils.GameUtilities.getNumberForChar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChessCell implements Node {
    @Min(1)
    @Max(8)
    private int position_x;

    @Min(1)
    @Max(8)
    private int position_y;

    public ChessCell(String position_x, int position_y) {
        this.position_x = getNumberForChar(position_x);
        this.position_y = position_y;
    }

    @Override
    public String toString() {
        return getCharForNumber(position_x) + position_y;
    }

    public boolean isValid() {
        return position_x >= 1 && position_x <= BOARD_SIZE && position_y >= 1 && position_y <= BOARD_SIZE;
    }
}
