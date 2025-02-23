import chess
import chess.svg

def create_board():
    board = chess.Board()
    return board.fen()

def get_legal_moves(fen):
    board = chess.Board(fen)
    moves = [move.uci() for move in board.legal_moves]
    return moves

def make_move(fen, move_uci):
    board = chess.Board(fen)
    move = chess.Move.from_uci(move_uci)
    if move in board.legal_moves:
        board.push(move)
        return board.fen()
    else:
        raise ValueError(f"Invalid move: {move_uci}")

def get_legal_moves_from_square(fen, square):
    board = chess.Board(fen)
    legal_moves = []
    from_square = chess.parse_square(square)
    for move in board.legal_moves:
        if move.from_square == from_square:
            to_square = chess.square_name(move.to_square)
            legal_moves.append(to_square)
    return legal_moves

def render_board(fen, selected_square=None, legal_moves=None):
    board = chess.Board(fen)
    squares_to_highlight = {}
    if selected_square:
        selected_sq = chess.parse_square(selected_square)
        squares_to_highlight[selected_sq] = "#a9a9f5"

    if legal_moves:
        for move_square in legal_moves:
            sq = chess.parse_square(move_square)
            squares_to_highlight[sq] = "#f5a9a9"

    svg_data = chess.svg.board(board, squares=squares_to_highlight)
    return svg_data

def check_game_result(fen):
    board = chess.Board(fen)
    if board.is_checkmate():
        if board.turn == chess.WHITE:
            return "Black wins!"
        else:
            return "White wins!"
    elif board.is_stalemate():
        return "Stalemate!"
    elif board.is_insufficient_material():
        return "Draw (Insufficient Material)!"
    elif board.is_game_over():
        return "Game Over!"
    else:
        return "Game still in progress"