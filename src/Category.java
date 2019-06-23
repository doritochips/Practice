import java.util.*;

/**
 * Implemented using Union-Find
 * */
public class Category {
    static class Node {
        long data;
        Node parent;
        int rank;

        Node(long data, int rank) {
            this.data = data;
            this.parent = this;
            this.rank = rank;
        }
    }

    private static void makeSet(final long data, final Map<Long, Node> disjointSet) {
        disjointSet.put(data, new Node(data, 0));
    }

    private static Node findRepresentative(final long query, Map<Long, Node> disjointSet) {
        Node leaf = disjointSet.get(query);
        Node iterator = leaf;
        while(iterator.parent != iterator) {
            iterator = iterator.parent;
        }
        // compress path
        leaf.parent = iterator;
        return iterator;
    }

    private static void union(final Long nodeOne, final Long nodeTwo, Map<Long, Node> disjointSet) {
        Node n1 = disjointSet.get(nodeOne);
        Node n2 = disjointSet.get(nodeTwo);

        Node r1 = findRepresentative(n1.data, disjointSet);
        Node r2 = findRepresentative(n2.data, disjointSet);

        if (r1.rank > r2.rank) {
            r2.parent = r1;
            r1.rank += r2.rank;
        } else {
            r1.parent = r2;
            r2.rank += r1.rank;
        }
    }

    private static int category(final List<List<Long>> input, Map<Long, Node> ds) {
        for(List<Long> i : input) {
            Long i1 = i.get(0);
            Long i2 = i.get(1);
            if(!ds.containsKey(i1)) makeSet(i1, ds);
            if(!ds.containsKey(i2)) makeSet(i2, ds);

            union(i1, i2, ds);
        }

        Set<Node> count = new HashSet<>();
        for(Long key: ds.keySet()) {
            count.add(findRepresentative(key, ds));
        }
        return count.size();
    }

    public static void main(String[] args) {
        Map<Long, Node> ds = new HashMap<>();
        List<List<Long>> input = new ArrayList<>();
        List<Long> i1 = new ArrayList<>();
        i1.add(1L);
        i1.add(2L);
        input.add(i1);
        List<Long> i2 = new ArrayList<>();
        i2.add(4L);
        i2.add(7L);
        input.add(i2);
        List<Long> i3 = new ArrayList<>();
        i3.add(8L);
        i3.add(3L);
        input.add(i3);
        List<Long> i4 = new ArrayList<>();
        i4.add(3L);
        i4.add(2L);
        input.add(i4);

        assert category(input, ds) == 2;
    }
}
