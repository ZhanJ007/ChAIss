package com.example.chaiss;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;

public class RookMovesTest {
    private ChessEngine chessEngine;

    @Before
    public void setUp() {
        chessEngine = new ChessEngine();
    }

    @Test
    public void testRookUnblockedMoves() {

        String customFEN = "7R/8/8/8/8/8/8/8 w - - 0 1";


        String[] expectedMoves = {
                // Horizontal moves along the 1st rank
                "h8g8", "h8f8", "h8e8", "h8d8", "h8c8", "h8b8", "h8a7",
                // Vertical moves along the a-file
                "h8h7", "h8h6", "h8h5", "h8h4", "h8h3", "h8h2", "h8h1"
        };

        // Get all legal moves
        String[] allLegalMoves = chessEngine.getLegalMoves(customFEN);

        // Filter moves related to the rook on a1
        String[] rookMoves = filterMovesBySquare(allLegalMoves, "h8");


        Arrays.sort(expectedMoves);
        Arrays.sort(rookMoves);

        // Assert that the filtered moves match the expected rook moves
        assertArrayEquals(expectedMoves, rookMoves);
    }


    private String[] filterMovesBySquare(String[] allMoves, String square) {
        return Arrays.stream(allMoves)
                .filter(move -> move.startsWith(square))
                .toArray(String[]::new);
    }
}
