package code.model.datastructures;

/**
 *
 * @author Jose Carlos
 */
public class Node<T>
{
    private T data;
    private Node<T> next;
    private Node<T> prev;

    public Node(T data) 
    {
        this.data = data;
    }
    
    public boolean isEmpty()
    {
        return this.data == null;
    }

    public T data() 
    {
        return data;
    }

    public Node<T> next() 
    {
        return next;
    }

    public void next(Node<T> next) 
    {
        this.next = next;
    }

    public Node<T> prev() 
    {
        return prev;
    }

    public void prev(Node<T> prev) 
    {
        this.prev = prev;
    }
}
