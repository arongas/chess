package gr.rongasal.chess.game.pieces;

import gr.rongasal.chess.game.pieces.exception.UnsupportedPieceTypeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PieceFactory.class})
class PieceFactoryTest {

    @Autowired
    private PieceFactory pieceFactory;

    @Test
    public void testGetKnight() throws UnsupportedPieceTypeException {
        Piece piece = pieceFactory.createPiece(PieceType.KNIGHT);
        assertThat(piece.getClass()).isEqualTo(Knight.class);
    }


    @Test
    public void testAllTypesAreUsedFromFactory() throws UnsupportedPieceTypeException {
        for (PieceType pieceType : PieceType.values()) {
            Piece piece = pieceFactory.createPiece(pieceType);
            assertThat(piece).isNotNull();
        }
    }
}
