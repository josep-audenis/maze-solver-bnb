import edu.salle.url.maze.business.enums.Cell;
import edu.salle.url.maze.business.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Nodes implements Comparable<Nodes>{
    private final List<Integer> node;
    private final List<List<Integer>> path;
    private List<Integer> end;

    public Nodes(List<Integer> start, List<List<Integer>> path, List<Integer> end){
        this.node = start;
        this.path = path;
        this.path.add(node);
        this.end = end;
    }

    private Nodes(Nodes that){
        this.node = new ArrayList<>(that.node);
        this.path = new ArrayList<>(that.path);
        this.end = that.end;
    }
    public int getX(){
        return node.get(0);
    }
    public int getY(){
        return node.get(1);
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
                        successor.path.add(successor.node);
                        break;
                    case DOWN:
                        successor.node.set(1, successor.node.get(1)+1);
                        successor.path.add(successor.node);
                        break;
                    case LEFT:
                        successor.node.set(0, successor.node.get(0)-1);
                        successor.path.add(successor.node);
                        break;
                    case RIGHT:
                        successor.node.set(0, successor.node.get(0)+1);
                        successor.path.add(successor.node);
                        break;
                }
                successors.add(successor);
            }
        }
        return successors;
    }

    public int cost(){
        return path.size() - manhattan(node,end);
    }

    public boolean isFinished(){
        boolean what = false;
        if (path.contains(end)){
            what = true;
        }
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
                return cells[x][y - 1] != Cell.WALL && !path.contains(newNode);
            case DOWN:
                newNode.add(x);
                newNode.add(y + 1);
                return cells[x][y + 1] != Cell.WALL && !path.contains(newNode);
            case LEFT:
                newNode.add(x - 1);
                newNode.add(y);
                return cells[x - 1][y] != Cell.WALL && !path.contains(newNode);
            case RIGHT:
                newNode.add(x + 1);
                newNode.add(y);
                return cells[x + 1][y] != Cell.WALL && !path.contains(newNode);
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
