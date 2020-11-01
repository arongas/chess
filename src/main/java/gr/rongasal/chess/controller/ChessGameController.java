package gr.rongasal.chess.controller;

import gr.rongasal.chess.dto.GameRequest;
import gr.rongasal.chess.dto.GameResponse;
import gr.rongasal.chess.game.GameException;
import gr.rongasal.chess.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static gr.rongasal.chess.controller.Endpoints.GAME;

@RestController
@RequiredArgsConstructor
public class ChessGameController {
    private final GameService gameService;

    @PostMapping(GAME)
    @ResponseBody
    ResponseEntity<GameResponse> game(@RequestBody @Valid GameRequest gameRequest) throws GameException {
        return ResponseEntity.ok(gameService.executeGame(gameRequest));
    }

}
