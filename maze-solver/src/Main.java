import edu.salle.url.maze.Maze;
import edu.salle.url.maze.MazeBuilder;

public class Main {
    public static void main(String[] args) {
        Maze maze = new MazeBuilder()
                .setMazeColumns(100)
                .setMazeRows(100)
                // Opcional, per fixar un input en comptes d'obtenir-ne un d'aleatori cada cop
                .setSeed(33)
                .setMazeSolver(new DemoMazeSolver())
                // També es pot fer servir buildDungeonMaze per escollir l'altre tipus de mapa
                .buildCaveMaze();
        // Observació: La classe DemoMazeSolver implementa la interfície MazeSolver

        maze.run();
    }
}