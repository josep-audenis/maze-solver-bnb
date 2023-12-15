import edu.salle.url.maze.business.MazeSolver;
import edu.salle.url.maze.business.enums.Cell;
import edu.salle.url.maze.business.enums.Direction;
import edu.salle.url.maze.presentation.MazeRenderer;

import java.util.List;

public class DemoMazeSolver implements MazeSolver {
    @Override
    public List<Direction> solve(Cell[][] cells, MazeRenderer mazeRenderer) {
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[i].length; j++){
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
        return null;
    }
}
