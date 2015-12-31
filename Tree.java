import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<T> implements Iterable<T>, Iterator<T>{

    private T value;
    private List<Tree<T>> children;
    private TraversalStrategy traversalStrategy;
    private Iterator<Tree<T>> currentIterator;
    // Constructors

    public Tree(T value) {
        this(value, TraversalStrategy.DEPTH_FIRST);
        this.children = new LinkedList<>();
    }
    public Tree(T value, TraversalStrategy strategy) {
        this.traversalStrategy = strategy;
        this.value = value;
        this.children = new LinkedList<>();
    }

    public Tree(T value, TraversalStrategy strategy, List<Tree<T>> children) {
        this.traversalStrategy = strategy;
        this.value = value;
        this.children = new LinkedList<>(children);
    }

    public Tree(T value, List<T> children, TraversalStrategy strategy) {
        this.traversalStrategy = strategy;
        this.value = value;
        this.children = new LinkedList<>();
        for (T child : children) {
            this.add(new Tree<T>(child));
        }
    }
    public Tree(T value, List<T> children) {
        this.traversalStrategy = TraversalStrategy.DEPTH_FIRST;
        this.value = value;
        this.children = new LinkedList<>();
        for (T child : children) {
            this.add(new Tree<T>(child));
        }
    }


    // Properties
    public T getValue() {
        return value;
    }
    public List<Tree<T>> getChildren() {
        return children;
    }
    public TraversalStrategy getTraversalStrategy() {
        return traversalStrategy;
    }
    public void setTraversalStrategy(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }


    public void add(Tree<T> child) {
        this.children.add(child);
    }
    public void add(T child) {
        add(new Tree<T>(child));
    }

    public Iterator<Tree<T>> getIterator(Tree<T> tree, TraversalStrategy traversalStrategy) {
        return traversalStrategy == TraversalStrategy.BREADTH_FIRST ?
                new BreadthFirstTreeIterator(tree) :
                new DepthFirstTreeIterator(tree);
    }
    public List<T> traverse() {
        return traverseByOrder(this.traversalStrategy);
    }
    public List<T> traverseByOrder(TraversalStrategy strategy) {
        Iterator<Tree<T>> iterator = getIterator(this, strategy);
        List<T> result = new ArrayList<>();
        while(iterator.hasNext()) {
            result.add(iterator.next().getValue());
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        currentIterator = getIterator(this, traversalStrategy);
        return this;
    }

    @Override
    public boolean hasNext() {
        return currentIterator.hasNext();
    }

    @Override
    public T next() {
        return currentIterator.next().getValue();
    }
}
