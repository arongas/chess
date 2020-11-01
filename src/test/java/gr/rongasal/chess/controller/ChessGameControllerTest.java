package gr.rongasal.chess.controller;

import gr.rongasal.chess.game.algorithm.SearchAlgorithmType;
import gr.rongasal.chess.game.pieces.PieceType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static gr.rongasal.chess.controller.Endpoints.GAME;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ChessGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void game() throws Exception {


        JSONObject gameRequest = new JSONObject()
                .put("searchAlgorithmType", SearchAlgorithmType.POSSIBLE_PATHS)
                .put("pieceType", PieceType.KNIGHT)
                .put("start", createCell(1, 1))
                .put("destination", createCell(3, 4))
                .put("depth", 3);

        mockMvc.perform(MockMvcRequestBuilders.post(GAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gameRequest.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("searchAlgorithmType").value("AllPaths"))
                .andExpect(jsonPath("pieceType").value("Knight"))
                .andExpect(jsonPath("start.position_x").value(1))
                .andExpect(jsonPath("start.position_y").value(1))
                .andExpect(jsonPath("destination.position_x").value(3))
                .andExpect(jsonPath("destination.position_y").value(4))
                .andExpect(jsonPath("depth").value(3))
                .andExpect(jsonPath("solutions[*]").value(containsInAnyOrder("A1->C2->E3->C4", "A1->C2->A3->C4", "A1->B3->D2->C4", "A1->B3->A5->C4")))
                .andDo(print());
    }


    @Test
    void gameWithNoSolution() throws Exception {


        JSONObject gameRequest = new JSONObject()
                .put("searchAlgorithmType", SearchAlgorithmType.POSSIBLE_PATHS)
                .put("pieceType", PieceType.KNIGHT)
                .put("start", createCell(1, 1))
                .put("destination", createCell(8, 8))
                .put("depth", 3);

        mockMvc.perform(MockMvcRequestBuilders.post(GAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gameRequest.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("searchAlgorithmType").value("AllPaths"))
                .andExpect(jsonPath("pieceType").value("Knight"))
                .andExpect(jsonPath("start.position_x").value(1))
                .andExpect(jsonPath("start.position_y").value(1))
                .andExpect(jsonPath("destination.position_x").value(8))
                .andExpect(jsonPath("destination.position_y").value(8))
                .andExpect(jsonPath("depth").value(3))
                .andExpect(jsonPath("message").value("No Solution Found"))
                .andDo(print());
    }

    private JSONObject createCell(int x, int y) throws JSONException {
        return new JSONObject()
                .put("position_x", x)
                .put("position_y", y);
    }
}
