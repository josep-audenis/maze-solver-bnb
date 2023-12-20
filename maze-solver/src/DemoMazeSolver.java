import edu.salle.url.maze.business.MazeSolver;
import edu.salle.url.maze.business.enums.Cell;
import edu.salle.url.maze.business.enums.Direction;
import edu.salle.url.maze.presentation.MazeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DemoMazeSolver implements MazeSolver {
    @Override
    public List<Direction> solve(Cell[][] cells, MazeRenderer mazeRenderer) {
        int upper = Integer.MAX_VALUE;
        PriorityQueue<Nodes> queue = new PriorityQueue<>();
        Nodes first;
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        List<Direction> best = new ArrayList<>();

        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[i].length; j++){
                if (cells[i][j] == Cell.START){
                    start.add(i);
                    start.add(j);
                }
                if (cells[i][j] == Cell.EXIT){
                    end.add(i);
                    end.add(j);
                }
            }
        }
        first = new Nodes(start, new ArrayList<>(), end, cells.length, cells[0].length);
        queue.offer(first);

        while (!queue.isEmpty()){
            Nodes config = queue.poll();

            List<Nodes> successors = config.expand(cells);

            for (Nodes successor : successors){
                if (successor.isFinished()){

                    if (successor.cost() < upper){
                        upper = successor.cost();
                        best = pathToDirections(successor.getPath());
                        mazeRenderer.render(cells, best,1000);
                    }
                } else {
                    if (successor.cost() < upper){
                        queue.offer(successor);
                        //mazeRenderer.render(cells, pathToDirections(successor.getPath()),1);
                    }
                }
            }
        }
        mazeRenderer.render(cells, best);
        return best;
    }

    public List<Direction> pathToDirections(List<List<Integer>> path){
        List<Direction> directions = new ArrayList<>();
        List<Integer> previous = new ArrayList<>();
        for (List<Integer> cords : path){
            if (cords.equals(path.get(0))) {
                previous = cords;
                continue;
            }
            int x_dif = previous.get(1) - cords.get(1);
            int y_dif = previous.get(0) - cords.get(0);

            if (x_dif > 0) directions.add(Direction.LEFT);
            if (x_dif < 0) directions.add(Direction.RIGHT);
            if (y_dif > 0) directions.add(Direction.UP);
            if (y_dif < 0) directions.add(Direction.DOWN);
            previous = cords;
        }
        return directions;
    }

}