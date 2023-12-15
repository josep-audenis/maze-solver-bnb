import edu.salle.url.maze.business.MazeSolver;
import edu.salle.url.maze.business.enums.Cell;
import edu.salle.url.maze.business.enums.Direction;
import edu.salle.url.maze.presentation.MazeRenderer;

import java.sql.SQLOutput;
import java.util.*;

public class DemoMazeSolver implements MazeSolver {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public List<Direction> solve(Cell[][] cells, MazeRenderer mazeRenderer) {
        Nodes startNode;
        List<Integer> end = new ArrayList<>();
        PriorityQueue<Nodes> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.getPath().size()));
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[i].length; j++){
                if (cells[i][j] == Cell.START){
                    startNode = new Nodes(i,j, new ArrayList<>());
                    queue.add(startNode);
                }
                if (cells[i][j] == Cell.EXIT){
                    end.add(i);
                    end.add(j);
                }
            }
        }

        while(!queue.isEmpty()){
            Nodes current = queue.poll();
            if (current.getX() == end.get(0) && current.getY() == end.get(1)){
                return pathToDirections(current.getPath());
            }
            int x = 0;
            int y = 0;
            for (Direction dir : Direction.values()){
                switch (dir){
                    case UP:
                        x = current.getX();
                        y = current.getY() - 1;
                        break;
                    case DOWN:
                        x = current.getX();
                        y = current.getY() + 1;
                        break;
                    case LEFT:
                        x = current.getX() - 1;
                        y = current.getY();
                        break;
                    case RIGHT:
                        x = current.getX() + 1;
                        y = current.getY();
                        break;
                }
                List<Integer> cords = new ArrayList<>();
                cords.add(x);
                cords.add(y);
                if (x < cells.length && y < cells[0].length && cells[x][y] != Cell.WALL && !current.getPath().contains(cords)){
                    Nodes newNode = new Nodes(x, y, current.getPath());
                    if (!queue.contains(newNode)){
                        queue.add(newNode);
                        System.out.println(current.getPath());
                    }
                }
            }
        }
        return null;
    }

    public List<Direction> pathToDirections(List<List<Integer>> path){
        List<Direction> directions = new ArrayList<>();
        List<Integer> previous = new ArrayList<>();
        System.out.println(path);
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
        System.out.println(directions);
        return directions;
    }

}
