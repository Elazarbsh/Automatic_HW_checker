public class DLinkedList<T> implements List<T>
{
    //START of private class
    private class DNode{
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element)
        {
            this.prev = null;
            this.next = null;
            this.element = element;
        }
        public T getElement()
        {
            return element;
        }
        public void setNext(DNode next)
        {
            this.next = next;
        }
        public DNode getNext()
        {
            return this.next;
        }
        public void setPrev(DNode prev)
        {
            this.prev = prev;
        }
        public DNode getPrev()
        {
            return this.prev;
        }
    }
    //END of private class

    private DNode head;
    private DNode cursor;

    public DLinkedList()
    {
        head = null;
        cursor = null;
    }

    @Override
    public void insert(T newElement)
    {
        DNode new1 = new DNode(newElement);

        //PreCondition
        if (newElement == null)
        {
            throw (new RuntimeException("The new element is empty"));
        }
        //PostCondition
        if (head == null)
        {
            new1.setPrev(null);
            head = new1;

        }
        else
        {
            new1.setNext(cursor.next);
            new1.setPrev(cursor);
            cursor.setNext(new1);
        }
        cursor = new1;
    }

    @Override
    public T remove()
    {
        if(isEmpty())
        {
            return null;
        }

        T returnedValue = null;
        if (cursor == head)
        {
            returnedValue = head.getElement();
            head = head.next;
            if(head != null)
            {
                head.setPrev(null);
            }
            cursor = head;
            return returnedValue;
        }

        returnedValue = cursor.getElement();
        (cursor.getPrev()).setNext(cursor.getNext());
        (cursor.getNext()).setPrev(cursor.getPrev());

        if(cursor.next == null)
        {
            cursor = head;
        }
        else
        {
            cursor = cursor.getNext();
        }
        return returnedValue;
    }

    @Override
    public T remove(T element)
    {
        DNode temp = head;
        T returned = null;
        while (true)
        {
            if(temp == null)
            {
                break;
            }
            if(temp.getElement() == element)
            {
                cursor = temp;
                returned = cursor.getElement();
                return this.remove();
            }
            temp = temp.getNext();
        }
        return null;
    }

    @Override
    public void clear()
    {
        head = null;
        cursor = null;
    }

    @Override
    public void replace(T newElement)
    {
        //PreCondition
        if(isEmpty())
        {
            throw (new RuntimeException("The list is empty"));
        }
        if(newElement == null)
        {
            throw (new RuntimeException("The new element is empty"));
        }
        //PostCondition
        cursor.element = newElement;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean goToBeginning() {
        if (!isEmpty())
        {
            cursor = head;
            return true;
        }
        return false;
    }

    @Override
    public boolean goToEnd()
    {
        if (isEmpty())
        {
            return false;
        }
        while (cursor.getNext() != null)
        {
            cursor = cursor.getNext();
        }
        return true;
    }

    @Override
    public T getNext()
    {
        //PreCondition
        if(isEmpty())
        {
            return null;
        }
        //PostCondition
        if(cursor.getNext() == null)
        {
            return null;
        }
        cursor = cursor.getNext();
        return cursor.getElement();
    }

    @Override
    public T getPrev()
    {
        if(isEmpty())
        {
            return null;
        }
        if(cursor == head)
        {
            return null;
        }
        cursor = cursor.getPrev();
        return cursor.getElement();
    }

    @Override
    public T getCursor() {
        if(isEmpty())
        {
            return null;
        }
        return cursor.getElement();
    }

    @Override
    public boolean hasNext()
    {
        if(isEmpty())
        {
            return false;
        }
        return cursor.getNext() != null;
    }

    @Override
    public boolean hasPrev()
    {
        if (isEmpty())
        {
            return false;
        }
        return cursor.getPrev() != null;
    }
}
