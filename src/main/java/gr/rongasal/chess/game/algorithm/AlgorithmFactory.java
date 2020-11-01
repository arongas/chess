package gr.rongasal.chess.game.algorithm;

import gr.rongasal.chess.game.algorithm.exception.UnknownAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class AlgorithmFactory {

    public Algorithm getAlgorithm(@NotNull SearchAlgorithmType searchAlgorithmType,
                                  @NotNull ConnectionProvider connectionProvider, int exploreDepth)
            throws UnknownAlgorithmException {
        // expecting more...
        //noinspection SwitchStatementWithTooFewBranches
        switch (searchAlgorithmType) {
            case POSSIBLE_PATHS:
                //noinspection unchecked
                return PossiblePathsAlgorithm.builder().connectionProvider(connectionProvider).exploreDepth(exploreDepth).build();
            default:
                throw new UnknownAlgorithmException(searchAlgorithmType);
        }
    }
}
