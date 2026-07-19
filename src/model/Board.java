package model;

public class Board {
    private final int size;
    private final Symbol[][] grid;
    private int filledCells;

    public Board(int size){
        this.size = size;
        grid = new Symbol[size][size];
        this.filledCells = 0;
    }

    public int getSize(){
        return size;
    }

    public Symbol[][] getGrid(){
        return grid;
    }

    public void placeSymbol(int row, int column, Symbol symbol){
        if(!isCellEmpty(row, column)){
            throw new IllegalStateException("Cell already has a symbol, try another cell");
        }
        grid[row][column] = symbol;
        filledCells++;
    }

    public boolean isFull(){
        // for(Symbol[] row : grid){
        //     for(Symbol item : row){
        //         if(item == null){
        //             return false;
        //         }
        //     }
        // }
        return filledCells == size * size;
    }

    public boolean isCellEmpty(int row, int column){
        validateCell(row,column);
        return grid[row][column] == null;
    }

    private void validateCell(int row, int column){
        if(row < 0 || row >= size || column < 0 || column >= size){
            throw new IllegalArgumentException("Invalid position: (" + row + "," + column + ")");
        }
    }
}
