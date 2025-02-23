// Dinosaurs
package com.example.chaiss;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the chess engine
        ChessEngine chessEngine = new ChessEngine();

        // Test 1: Create a new chess board
        String fen = chessEngine.createBoard();
        Log.d("ChessTest", "Initial board position (FEN): " + fen);

        // Test 2: Get legal moves for the starting position
        String[] legalMoves = chessEngine.getLegalMoves(fen);
        Log.d("ChessTest", "Legal moves from starting position:");
        for (String move : legalMoves) {
            Log.d("ChessTest", move);
        }

        // Test 3: Make a move (e.g., "e2e4") and get the updated FEN
        String newFen = chessEngine.makeMove(fen, "e2e4");
        Log.d("ChessTest", "Updated board position after e2e4 (FEN): " + newFen);

        // Test 4: Get legal moves for the updated position
        String[] newLegalMoves = chessEngine.getLegalMoves(newFen);
        Log.d("ChessTest", "Legal moves after e2e4:");
        for (String move : newLegalMoves) {
            Log.d("ChessTest", move);
        }
    }
}


