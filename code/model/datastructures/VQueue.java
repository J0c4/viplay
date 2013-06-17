package code.model.datastructures;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Jose Carlos
 */
public class VQueue<T extends Serializable> implements Serializable, Iterable<T>
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

    @Override
    public Iterator<T> iterator() 
    {
        Iterator<T> itetator = new VQueueIterator<T>(this.first);
        return itetator;
    }
    
    private class VQueueIterator<T> implements Iterator<T> 
    {
        private Node<T> current;

        public VQueueIterator(Node<T> first) 
        {
            this.current = first;
        }

        @Override
        public boolean hasNext() 
        {
            return this.current != null;
        }

        @Override
        public T next() 
        {
            T res = null;
            if (hasNext())
            {
                res = this.current.data();
                this.current = this.current.next();
            }
            return res;
        }

        @Override
        public void remove() 
        {   
        }
    }
}
