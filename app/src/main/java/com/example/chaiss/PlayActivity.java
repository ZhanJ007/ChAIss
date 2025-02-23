package com.example.chaiss;

import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.app.AlertDialog;



import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.PreserveAspectRatio;
import com.caverock.androidsvg.SVGParseException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private Python py;
    private PyObject chessLogic;
    private String currentFEN;
    private ImageView chessBoardImage;
    private String selectedSquare = null;
    private List<String> legalMoves = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        if(!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        py = Python.getInstance();
        chessLogic = py.getModule("chess_logic");

        chessBoardImage = findViewById(R.id.chess_board);

        currentFEN = chessLogic.callAttr("create_board").toString();
        updateBoard();

        chessBoardImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handleTouch(event.getX(), event.getY());
                    return true;
                }
                return false;
            }
        });

        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());
    }
    private void showGameOverDialog(String result) {
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("The game is over. " + result + ". Would you like to rematch?")
                .setPositiveButton("Rematch", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRematch();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // Exits Activity
                    }
                })
                .show();
    }
    private void startRematch() {
        currentFEN = chessLogic.callAttr("create_board").toString();
        selectedSquare = null;
        legalMoves = null;

        // Updates the board view
        updateBoard();
    }



    private void handleTouch(float x, float y) {
        String square = getSquareFromTouch(x, y);
        if (square == null) {
            return;
        }
        if (selectedSquare == null) {
            selectedSquare = square;
            try {
                PyObject legalMovesPy = chessLogic.callAttr("get_legal_moves_from_square", currentFEN, selectedSquare);
                List<String> legalMoves = new ArrayList<>();
                for (PyObject move : legalMovesPy.asList()) {
                    legalMoves.add(move.toString());
                }
                if (legalMoves.isEmpty()) {
                    Toast.makeText(this, "No legal moves from " + selectedSquare, Toast.LENGTH_SHORT).show();
                    selectedSquare = null;
                } else {
                    this.legalMoves = legalMoves;
                    updateBoard();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error finding legal moves: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                selectedSquare = null;
            }
        } else {
            String move = selectedSquare + square;
            Log.d("ChessApp", "Attempting move: " + move);
            try {
                PyObject result = chessLogic.callAttr("make_move", currentFEN, move);
                currentFEN = result.toString();

                // Check for game result (win loss or draw
                PyObject gameResult = chessLogic.callAttr("check_game_result", currentFEN);
                String resultMessage = gameResult.toString();

                // Display the results
                if (!resultMessage.equals("Game still ongoing")) {
                    Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();
                }

                selectedSquare = null;
                this.legalMoves = null;
                updateBoard();
            } catch (Exception e) {
                Toast.makeText(this, "Invalid move: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                selectedSquare = null;
                this.legalMoves = null;
                updateBoard();
            }
        }
    }

    private String getSquareFromTouch(float x, float y) {
        int boardSize = chessBoardImage.getWidth();
        int squareSize = boardSize / 8;

        int col = (int) (x / squareSize);
        int row = 7 - (int) (y / squareSize);

        if (col < 0 || col > 7 || row < 0 || row > 7) {
            return null;
        }

        char file = (char) ('a' + col);
        int rank = row + 1;

        return "" + file + rank;
    }

    private void updateBoard() {
        new Thread(() -> {
            try {
                PyObject svgData;
                if (selectedSquare != null && legalMoves != null) {
                    String[] legalMovesArray = legalMoves.toArray(new String[0]);
                    svgData = chessLogic.callAttr("render_board", currentFEN, selectedSquare, (Object) legalMovesArray);
                } else {
                    svgData = chessLogic.callAttr("render_board", currentFEN);
                }
                String boardSvg = svgData.toString();
                runOnUiThread(() -> {
                    try {
                        SVG svg = SVG.getFromString(boardSvg);
                        svg.setDocumentWidth("100%");
                        svg.setDocumentHeight("100%");
                        svg.setDocumentPreserveAspectRatio(PreserveAspectRatio.LETTERBOX);
                        chessBoardImage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                        chessBoardImage.setImageDrawable(new PictureDrawable(svg.renderToPicture()));
                    } catch (SVGParseException e) {
                        Toast.makeText(this, "Error rendering board: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error rendering board: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
                e.printStackTrace();
            }
        }).start();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentFEN", currentFEN);
        outState.putString("selectedSquare", selectedSquare);
        if (legalMoves != null) {
            outState.putStringArrayList("legalMoves", new ArrayList<>(legalMoves));
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentFEN = savedInstanceState.getString("currentFEN");
        selectedSquare = savedInstanceState.getString("selectedSquare");
        legalMoves = savedInstanceState.getStringArrayList("legalMoves");
        updateBoard();
    }
}
