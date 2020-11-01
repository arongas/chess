package gr.rongasal.chess.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SolutionsResponse extends GameResponse {
    private List<String> solutions;

    public SolutionsResponse(GameRequest gameRequest, List<String> solutions) {
        super(gameRequest.getSearchAlgorithmType(), gameRequest.getPieceType(), gameRequest.getStart(), gameRequest.getDestination(), gameRequest.getDepth());
        this.solutions = solutions;
    }

}
