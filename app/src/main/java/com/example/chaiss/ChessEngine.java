package com.example.chaiss;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class ChessEngine {
    private Python python;
    private PyObject chessLogic;

    public ChessEngine() {
        python = Python.getInstance();
        chessLogic = python.getModule("chess_logic");
    }

    public String createBoard() {
        return chessLogic.callAttr("create_board").toString();
    }

    public String[] getLegalMoves(String fen) {
        PyObject moves = chessLogic.callAttr("get_legal_moves", fen);
        return moves.asList().stream().map(Object::toString).toArray(String[]::new);
    }

    public String[] getLegalMoves(String fen, String square) {
        PyObject moves = chessLogic.callAttr("get_legal_moves", fen, square);
        return moves.asList().stream().map(Object::toString).toArray(String[]::new);
    }

    public String makeMove(String fen, String move) {
        return chessLogic.callAttr("make_move", fen, move).toString();
    }

    public String renderBoard(String fen) {
        return chessLogic.callAttr("render_board", fen).toString();
    }

    public String renderBoard(String fen, String[] highlights) {
        PyObject highlightsList = Python.getInstance().getBuiltins().callAttr("list", (Object) highlights);
        return chessLogic.callAttr("render_board", fen, highlightsList).toString();
    }

    public String setPiece(String fen, String piece, String square) {
        return chessLogic.callAttr("set_piece", fen, piece, square).toString();
    }

}
