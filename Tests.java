import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tests {
    @Test
    public void Test1() {
        LinkedList<Integer> children = new LinkedList<>();
        children.add(2);
        children.add(3);
        Tree<Integer> tree = new Tree<Integer>(1, children, TraversalStrategy.DEPTH_FIRST);
        Assert.assertNotNull(tree);
        Assert.assertEquals(tree.getValue(), new Integer(1));
        Integer []dfsOrder = new Integer[]{1,2,3};
        int i = 0;

        // Iterable
        for (Integer x : tree) {
            Assert.assertEquals(dfsOrder[i++], x);
        }
    }

    @Test
    public void Test2() {

        Tree<String> tree = new Tree<String>("root", TraversalStrategy.BREADTH_FIRST);


        for (Character c = 'A'; c <= 'C'; c++) {
            final Character finalC = c;
            List<String> childrenC = new ArrayList<String>() {
                {
                    for (int i = 1; i <= 3; i++) {
                        add(finalC.toString() + new Integer(i).toString());
                    }
                }
            };

            Tree<String> subTree = new Tree<String>(c.toString(), childrenC);
            tree.add(subTree);
        }


        String []bfs = new String[]{"root", "A", "B", "C", "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};
        int i = 0;
        for (String str : tree)
            Assert.assertEquals(bfs[i++], str);

        System.out.println("BFS = " + tree.traverse());
        System.out.println("DFS = " + tree.traverseByOrder(TraversalStrategy.DEPTH_FIRST));
    }
}
