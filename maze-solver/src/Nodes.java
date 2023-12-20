import edu.salle.url.maze.business.enums.Cell;
import edu.salle.url.maze.business.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Nodes implements Comparable<Nodes>{
    private final List<Integer> node;
    private final List<List<Integer>> path;
    private final List<Integer> end;
    private final boolean[][] visited;

    public Nodes(List<Integer> start, List<List<Integer>> path, List<Integer> end, int sizeX, int sizeY){
        this.node = start;
        this.path = path;
        this.path.add(node);
        this.end = end;
        this.visited = new boolean[sizeX][sizeY];
        this.visited[start.get(0)][start.get(1)] = true;
    }

    private Nodes(Nodes that){
        this.node = new ArrayList<>(that.node);
        this.path = new ArrayList<>(that.path);
        this.end = that.end;
        this.visited = that.visited;
    }

    public List<List<Integer>> getPath(){
        return path;
    }

    public List<Nodes> expand(Cell[][] cells){
        List<Nodes> successors = new ArrayList<>();
        for (Direction direction : Direction.values()){
            if (valid(direction, cells)){
                Nodes successor = new Nodes(this);
                switch(direction){
                    case UP:
                        successor.node.set(1, successor.node.get(1)-1);
                        break;
                    case DOWN:
                        successor.node.set(1, successor.node.get(1)+1);
                        break;
                    case LEFT:
                        successor.node.set(0, successor.node.get(0)-1);
                        break;
                    case RIGHT:
                        successor.node.set(0, successor.node.get(0)+1);
                        break;
                }
                successor.path.add(successor.node);
                successor.visited[successor.node.get(0)][successor.node.get(1)] = true;
                successors.add(successor);
            }
        }
        return successors;
    }

    public int cost(){
        return path.size() + manhattan(node,end);
    }

    public boolean isFinished(){
        return path.contains(end);
    }

    private boolean valid(Direction direction, Cell[][] cells){
        List<Integer> newNode = new ArrayList<>();
        int x = node.get(0);
        int y = node.get(1);
        switch(direction){
            case UP:
                newNode.add(x);
                newNode.add(y - 1);
                return cells[x][y - 1] != Cell.WALL && !visited[newNode.get(0)][newNode.get(1)];
            case DOWN:
                newNode.add(x);
                newNode.add(y + 1);
                return cells[x][y + 1] != Cell.WALL && !visited[newNode.get(0)][newNode.get(1)];
            case LEFT:
                newNode.add(x - 1);
                newNode.add(y);
                return cells[x - 1][y] != Cell.WALL && !visited[newNode.get(0)][newNode.get(1)];
            case RIGHT:
                newNode.add(x + 1);
                newNode.add(y);
                return cells[x + 1][y] != Cell.WALL && !visited[newNode.get(0)][newNode.get(1)];
        }
        return false;
    }

    private int manhattan(List<Integer> start, List<Integer> end){
        return Math.abs(start.get(0) - end.get(0)) + Math.abs(start.get(1) - end.get(1));
    }

    @Override
    public int compareTo(Nodes that) {
        return this.cost() - that.cost();
    }
}
