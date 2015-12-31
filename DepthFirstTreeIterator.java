import java.util.*;

public class DepthFirstTreeIterator <T> implements Iterator<Tree<T>> {

    private HashSet<Tree<T>> used;
    private LinkedList<Tree<T>> dfsPath;

    public DepthFirstTreeIterator(Tree<T> root) {
        used = new HashSet<>();
        dfsPath = new LinkedList<>();
        dfs(root);
    }

    private void dfs(Tree<T> root) {
        used.add(root);
        dfsPath.add(root);
        for (Tree<T> child : root.getChildren()) {
            if (used.contains(child) == false)
                dfs(child);
        }
    }
    @Override
    public boolean hasNext() {
        return dfsPath.size() != 0;
    }

    @Override
    public Tree<T> next() {
        return dfsPath.poll();
    }
}
