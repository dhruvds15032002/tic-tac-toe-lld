package strategy;

import model.Board;
import model.Symbol;

public interface WinningStrategy {
    public boolean checkWin(Board board, int row, int column, Symbol symbol);
}
