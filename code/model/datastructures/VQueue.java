package code.model.datastructures;

import java.io.Serializable;

/**
 *
 * @author Jose Carlos
 */
public class VQueue<T extends Serializable> implements Serializable
{
    private Node<T> first;
    private Node<T> last;
    private int count;
    
    public boolean isEmpty()
    {
        return this.first == null;
    }
    
    public int size()
    {
        return this.count;
    }
    
    public void add(T data)
    {
        Node<T> toAdd = new Node<T>(data);
        if (isEmpty())
        {
            this.first = toAdd;
            this.last = this.first;;
        }
        else
        {
            toAdd.prev(this.last);
            this.last.next(toAdd);
            this.last = toAdd;
        }
        this.count++;
    }
    
    public T remove()
    {
        T res = null;
        if (!isEmpty())
        {
            res = this.first.data();
            this.first = this.first.next();
            this.count--;
        }
        return res;
    }
}
