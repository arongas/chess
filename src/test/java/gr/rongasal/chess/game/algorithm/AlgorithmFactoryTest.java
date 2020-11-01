package gr.rongasal.chess.game.algorithm;

import gr.rongasal.chess.game.algorithm.exception.UnknownAlgorithmException;
import gr.rongasal.chess.game.pieces.Knight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AlgorithmFactory.class})
class AlgorithmFactoryTest {

    @Autowired
    private AlgorithmFactory algorithmFactory;

    @Test
    void testGetAlgorithm() throws UnknownAlgorithmException {
        Algorithm algorithm = algorithmFactory.getAlgorithm(SearchAlgorithmType.POSSIBLE_PATHS,new Knight(),3);
        assertThat(algorithm.getClass()).isEqualTo(PossiblePathsAlgorithm.class);
    }

    @Test
    void testAllTypesAreUsedFromFactory() throws UnknownAlgorithmException {
        for (SearchAlgorithmType algorithmType : SearchAlgorithmType.values()) {
            Algorithm algorithm = algorithmFactory.getAlgorithm(algorithmType,new Knight(),3);
            assertThat(algorithm).isNotNull();
        }
    }
}
