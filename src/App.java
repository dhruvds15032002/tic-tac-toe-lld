import java.util.List;

import model.Player;
import model.Symbol;

public class App {
    public static void main(String[] args) throws Exception {
        Player player1 = new Player("Dhruv", new Symbol('X'));
        Player player2 = new Player("Rahul", new Symbol('O'));

        Game game = new Game(3, List.of(player1, player2));
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.makeMove(0, 2);
        game.makeMove(2, 2);

        System.out.println("Status: " + game.getStatus());
        System.out.println("Who Won: " + game.getWinner());
    }
}
