package gr.rongasal.chess.game;


import gr.rongasal.chess.dto.GameRequest;
import gr.rongasal.chess.dto.GameResponse;
import gr.rongasal.chess.dto.SolutionNotFoundResponse;
import gr.rongasal.chess.dto.SolutionsResponse;
import gr.rongasal.chess.game.algorithm.Algorithm;
import gr.rongasal.chess.game.algorithm.AlgorithmFactory;
import gr.rongasal.chess.game.algorithm.ConnectionProvider;
import gr.rongasal.chess.game.algorithm.Node;
import gr.rongasal.chess.game.pieces.PieceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private static final String NO_SOLUTION_FOUND = "No Solution Found";
    private static final String DELIMITER = "->";

    private final AlgorithmFactory algorithmFactory;
    private final PieceFactory pieceFactory;

    public GameResponse executeGame(@NotNull GameRequest gameRequest) throws GameException {
        log.info("Executing game {}", gameRequest);
        ConnectionProvider<ChessCell> piece = pieceFactory.createPiece(gameRequest.getPieceType()); // select chess piece type
        Algorithm searchAlgorithm = algorithmFactory.getAlgorithm(gameRequest.getSearchAlgorithmType(), piece, gameRequest.getDepth()); //select algorithm
        Optional<List<List<Node>>> routes = searchAlgorithm.walk(gameRequest.getStart(), gameRequest.getDestination()); //execute algorithm
        log.info("Game {} execution resulted in {}", gameRequest,routes);
        return routes.<GameResponse>map(lists -> new SolutionsResponse(gameRequest,
                lists.stream().map(ln -> ln.stream().map(Object::toString).collect(Collectors.joining(DELIMITER))).collect(Collectors.toList())))
                .orElseGet(() -> new SolutionNotFoundResponse(gameRequest, NO_SOLUTION_FOUND));
    }

}
