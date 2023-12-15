import edu.salle.url.maze.business.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Nodes {
    private List<Integer> node;
    private List<List<Integer>> path;

    public Nodes(int x, int  y, List<List<Integer>> path){
        this.node = new ArrayList<>();
        this.node.add(x);
        this.node.add(y);
        this.path = new ArrayList<>(path);
        this.path.add(node);
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
}
