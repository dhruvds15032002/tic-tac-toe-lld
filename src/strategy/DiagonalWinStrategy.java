package strategy;

import model.Board;
import model.Symbol;

public class DiagonalWinStrategy implements WinningStrategy{

    @Override
    public boolean checkWin(Board board, int row, int column, Symbol symbol) {
        Symbol[][] grid = board.getGrid();
        int size = board.getSize();

        boolean onMainDiagonal = (row == column);
        boolean onAntiDiagonal = (row + column == size - 1);

        if(!onMainDiagonal && !onAntiDiagonal){
            return false;
        }

        if(onMainDiagonal && checkMainDiagonal(grid, size, symbol)){
            return true;
        }

        if(onAntiDiagonal && checkAntiDiagonal(grid, size, symbol)){
            return true;
        }

        return false;
    }

    private boolean checkAntiDiagonal(Symbol[][] grid, int size, Symbol symbol) {

        for(int index = 0; index < size; index++){
            if(!symbol.equals(grid[index][size - index - 1])){
                return false; 
            }
        }
        return true;
    }

    private boolean checkMainDiagonal(Symbol[][] grid, int size, Symbol symbol) {
         for(int index = 0; index < size; index++){
            if(!symbol.equals(grid[index][index])){
                return false; 
            }
        }
        return true;
    }
}
