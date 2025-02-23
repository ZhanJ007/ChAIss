import org.junit.Before;
import org.junit.Test;
import com.example.chaiss.ChessEngine;
import static org.junit.Assert.assertArrayEquals;

public class PawnMovesTest {
    private ChessEngine chessEngine;

    // creates instance of our Chess Engine from Chess Python Library
    @Before
    public void setUp() {
        chessEngine = new ChessEngine();
    }

    @Test
    public void testPawnMoves() {
        // creates default board
        String initialFEN = chessEngine.createBoard();

        // moves available for pawn
        String[] expectedMoves = {"e2e5", "e2e4"}; // UCI format for pawn moves
        String[] allLegalMoves = chessEngine.getLegalMoves(initialFEN);
        String[] pawnMoves = filterMovesBySquare(allLegalMoves, "e2");
        assertArrayEquals(expectedMoves, pawnMoves);
    }
    private String[] filterMovesBySquare(String[] allMoves, String square) {
        return java.util.Arrays.stream(allMoves)
                .filter(move -> move.startsWith(square))
                .toArray(String[]::new);
    }
}
