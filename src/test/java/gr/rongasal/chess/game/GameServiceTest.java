package gr.rongasal.chess.game;

import gr.rongasal.chess.dto.GameRequest;
import gr.rongasal.chess.dto.GameResponse;
import gr.rongasal.chess.game.algorithm.Algorithm;
import gr.rongasal.chess.game.algorithm.AlgorithmFactory;
import gr.rongasal.chess.game.algorithm.ConnectionProvider;
import gr.rongasal.chess.game.algorithm.Node;
import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;
import gr.rongasal.chess.game.pieces.Knight;
import gr.rongasal.chess.game.pieces.PieceFactory;
import gr.rongasal.chess.game.pieces.PieceType;
import gr.rongasal.chess.game.pieces.exception.UnsupportedPieceTypeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;


    @MockBean
    private AlgorithmFactory algorithmFactory;

    @MockBean
    private PieceFactory pieceFactory;


    @Test
    void executeGameNoSolution() throws GameException {
        mockPieceFactory();
        Algorithm algorithm=mockAlgorithm();
        when(algorithm.walk(any(), any())).thenReturn(Optional.empty());

        GameRequest gameRequest = createGameRequest(1, 2, 3, 4, 3, PieceType.KNIGHT, SearchAlgorithmType.POSSIBLE_PATHS);
        GameResponse gameResponse = gameService.executeGame(gameRequest);
        GameResponseAssert.assertThat(gameResponse)
                .hasDepth(3)
                .haveAlgorithm(SearchAlgorithmType.POSSIBLE_PATHS)
                .havePieceType(PieceType.KNIGHT)
                .startFrom(new ChessCell(1, 2))
                .haveDestination(new ChessCell(3, 4))
                .noResultFound();
    }



    @Test
    void executeGameWithSolution() throws GameException {
        mockPieceFactory();
        Algorithm algorithm=mockAlgorithm();
        Optional<List<List<Node>>> algorithmPaths = getAlgorithmPaths();
        when(algorithm.walk(any(), any())).thenReturn(algorithmPaths);

        GameRequest gameRequest = createGameRequest(1, 2, 3, 4, 3, PieceType.KNIGHT, SearchAlgorithmType.POSSIBLE_PATHS);
        GameResponse gameResponse = gameService.executeGame(gameRequest);
        //noinspection OptionalGetWithoutIsPresent
        GameResponseAssert.assertThat(gameResponse)
                .hasDepth(3)
                .haveAlgorithm(SearchAlgorithmType.POSSIBLE_PATHS)
                .havePieceType(PieceType.KNIGHT)
                .startFrom(new ChessCell(1, 2))
                .haveDestination(new ChessCell(3, 4))
                .resultFound(algorithmPaths.get());
    }

    private Optional<List<List<Node>>> getAlgorithmPaths() {
        List<List<Node>> result=new ArrayList<>();
        List<Node> nodes1=new ArrayList<>();
        nodes1.add(new ChessCell("A",1));
        nodes1.add(new ChessCell("B",2));
        nodes1.add(new ChessCell("C",3));
        result.add(nodes1);
        List<Node> nodes2=new ArrayList<>();
        nodes1.add(new ChessCell("D",4));
        nodes1.add(new ChessCell("E",5));
        nodes1.add(new ChessCell("F",6));
        result.add(nodes2);
        return Optional.of(result);
    }

    private Algorithm mockAlgorithm() throws GameException {
        Algorithm algorithm = mock(Algorithm.class);
        when(algorithmFactory.getAlgorithm(any(SearchAlgorithmType.class), any(ConnectionProvider.class), anyInt())).thenReturn(algorithm);
        return algorithm;
    }

    private void mockPieceFactory() throws UnsupportedPieceTypeException {
        Knight knight = mock(Knight.class);
        when(pieceFactory.createPiece(any(PieceType.class))).thenReturn(knight);
    }

    private GameRequest createGameRequest(int sourceX, int sourceY, int targetX, int targetY,
                                          int depth,
                                          PieceType pieceType, SearchAlgorithmType searchAlgorithmType) {
        ChessCell startPosition = new ChessCell(sourceX, sourceY);
        ChessCell targetPosition = new ChessCell(targetX, targetY);
        return new GameRequest(searchAlgorithmType, pieceType, startPosition, targetPosition, depth);
    }
}
