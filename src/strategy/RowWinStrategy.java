package strategy;

import model.Board;
import model.Symbol;

public class RowWinStrategy implements WinningStrategy{

    @Override
    public boolean checkWin(Board board, int row, int column, Symbol symbol) {
        Symbol grid[][] = board.getGrid();

        for(int col = 0; col < board.getSize(); col++){
            if(!symbol.equals(grid[row][col])){
                return false;
            }
        }
        return true;
    }
    
}
