package gr.rongasal.chess.game;

import gr.rongasal.chess.dto.GameResponse;
import gr.rongasal.chess.dto.SolutionNotFoundResponse;
import gr.rongasal.chess.dto.SolutionsResponse;
import gr.rongasal.chess.game.algorithm.Node;
import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;
import gr.rongasal.chess.game.pieces.PieceType;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponseAssert extends AbstractAssert<GameResponseAssert, GameResponse> {

    public static GameResponseAssert assertThat(GameResponse actual) {
        return new GameResponseAssert(actual,GameResponseAssert.class);
    }

    public GameResponseAssert(GameResponse gameResponse, Class<?> selfType) {
        super(gameResponse, selfType);
    }

    public GameResponseAssert noResultFound() {
        isNotNull();
        if (!(actual instanceof SolutionNotFoundResponse)) {
            failWithMessage("Expected solution to be of type %s but was %s",
                    SolutionNotFoundResponse.class, actual.getClass());
        }
        return this;
    }

    public GameResponseAssert resultFound(List<List<Node>> solutions) {
        isNotNull();
        if (!(actual instanceof SolutionsResponse)) {
            failWithMessage("Expected solution to be of type %s but was %s",
                    SolutionsResponse.class, actual.getClass());
        }else {
            Assertions.assertThat(((SolutionsResponse) actual).getSolutions())
                    .containsExactlyInAnyOrder(solutions.stream().map(ln -> ln.stream().map(Object::toString).collect(Collectors.joining("->"))).toArray(String[]::new));
        }
        return this;
    }

    public GameResponseAssert hasDepth(int depth) {
        isNotNull();
        if (actual.getDepth()!=depth) {
            failWithMessage("Expected to have depth %s but was %s",
                    depth, actual.getDepth());
        }
        return this;
    }

    public GameResponseAssert startFrom(ChessCell chessCell) {
        isNotNull();
        if (!actual.getStart().equals(chessCell)) {
            failWithMessage("Expected to have start %s but have %s",
                    chessCell, actual.getStart());
        }
        return this;
    }

    public GameResponseAssert haveDestination(ChessCell chessCell) {
        isNotNull();
        if (!actual.getDestination().equals(chessCell)) {
            failWithMessage("Expected to have destination %s but have %s",
                    chessCell, actual.getDestination());
        }
        return this;
    }


    public GameResponseAssert havePieceType(PieceType pieceType) {
        isNotNull();
        if (!actual.getPieceType().equals(pieceType)) {
            failWithMessage("Expected to have piece type %s but has %s",
                    pieceType, actual.getPieceType());
        }
        return this;
    }


    public GameResponseAssert haveAlgorithm(SearchAlgorithmType type) {
        isNotNull();
        if (!actual.getSearchAlgorithmType().equals(type)) {
            failWithMessage("Expected to have algorithm %s but had %s",
                    type, actual.getSearchAlgorithmType());
        }
        return this;
    }

}
