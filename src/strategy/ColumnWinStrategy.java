package strategy;

import model.Board;
import model.Symbol;

public class ColumnWinStrategy implements WinningStrategy{

    @Override
    public boolean checkWin(Board board, int row, int column, Symbol symbol) {
        Symbol[][] grid = board.getGrid();

        for(int currRow = 0; currRow < board.getSize(); currRow++){
            if(!symbol.equals(grid[currRow][column])){
                return false;
            }
        }
        return true;
    }
    
}
