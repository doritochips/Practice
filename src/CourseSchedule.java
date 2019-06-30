import java.util.*;
import java.util.stream.Collectors;

/** LeetCode 210
 * @Author: David Zhou
 * Implement using topological sorting
 */
public class CourseSchedule {

    static class Node{
        int v;
        List<Node> children;
        public Node(int v){
            this.v = v;
            children = new ArrayList<>();
        }

        public int getValue() {
            return v;
        }
    }
    private static List<Integer> topoSort(Map<Integer, Node> graph) {
        Stack<Node> sorted = new Stack<>();
        Set<Node> visited = new HashSet<>();
        for(Integer k : graph.keySet()) {
            traverse(graph.get(k), sorted, visited);
        }
        return sorted.stream().map(Node::getValue).collect(Collectors.toList());
    }

    private static void traverse(Node cur, Stack<Node> sorted, Set<Node> visited) {
        if(visited.contains(cur)) return;
        visited.add(cur);
        for(Node c : cur.children) {
            traverse(c, sorted, visited);
        }
        sorted.add(cur);
    }

    private static Map<Integer, Node> buildGraph(List<List<Integer>> input) {
        Map<Integer, Node> graph = new HashMap<>();
        for(List<Integer> pair : input) {
            int parent = pair.get(1);
            int child = pair.get(0);
            if(!graph.containsKey(parent)) graph.put(parent, new Node(parent));
            if(!graph.containsKey(child)) graph.put(child, new Node(child));

            graph.get(parent).children.add(graph.get(child));
        }
        return graph;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 0), Arrays.asList(3, 1), Arrays.asList(3, 2));
        List<Integer> r = topoSort(buildGraph(input));
        System.out.println(r.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }
}
