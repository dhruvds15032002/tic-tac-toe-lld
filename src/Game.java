import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import enums.GameStatus;
import model.Board;
import model.Player;
import strategy.ColumnWinStrategy;
import strategy.DiagonalWinStrategy;
import strategy.RowWinStrategy;
import strategy.WinningStrategy;

public class Game {
    private Deque<Player> players;
    private Board board;
    private List<WinningStrategy> strategies;
    private GameStatus status;
    private Player winner;

    public Game(int size, List<Player> players){
        this.players = new LinkedList<>(players);
        board = new Board(size);
        strategies = List.of(
            new ColumnWinStrategy(),
            new RowWinStrategy(),
            new DiagonalWinStrategy()
        );
        status = GameStatus.IN_PROGRESS;
    }

    public GameStatus getStatus(){
        return status;
    }

    public Board getBoard(){
        return board;
    }

    public Player getWinner(){
        return winner;
    }

    public void makeMove(int row, int column){
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game already finished. Status: " + status);
        }
        Player player = players.removeFirst();
        board.placeSymbol(row, column, player.getSymbol());

        for(WinningStrategy strategy : strategies){
            boolean victory = strategy.checkWin(board, row, column, player.getSymbol());
            if (victory) {
                winner = player;
                status = GameStatus.WIN;
                return;
            }
        }
        
        if (board.isFull()) {
            status = GameStatus.TIE;
            return;
        }

        players.addLast(player);
    }
}
