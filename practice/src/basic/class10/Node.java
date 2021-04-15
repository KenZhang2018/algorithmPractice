package basic.class10;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public int value;
    public int in;
    public int out;
    public List<Node> nexts;
    public List<Edge> edges;
//    public List<EdgeNotEasyUse> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
