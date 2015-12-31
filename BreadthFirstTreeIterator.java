import java.util.*;

public class BreadthFirstTreeIterator<T> implements Iterator<Tree<T>> {
    private Queue<Tree<T>> queue;
    private HashSet<Tree<T>> used;
    public BreadthFirstTreeIterator(Tree<T> root) {
        queue = new ArrayDeque<>();
        used = new HashSet<>();
        queue.add(root);
        used.add(root);
    }
    @Override
    public boolean hasNext() {
        return queue.size() != 0;
    }

    @Override
    public Tree next() {
        Tree<T> answer = queue.poll();
        for (Tree<T> child : answer.getChildren()) {
            if (used.contains(child) == false) {
                used.add(child);
                queue.add(child);
            }
        }
        return answer;
    }
}
