public class DLinkedList<T> implements List<T> {
    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
            this.element = element;
        }

        public T getElemet() {
            return element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;
        }
    }

    private DNode head;
    private DNode cursor;


    public DLinkedList() {
        this.head = null;
        this.cursor = this.head;

    }

    @Override
    public void insert(T newElement)
    {
        if (newElement == null)
        {
            throw new RuntimeException("ERROR: the newElement is null");
        }
        DNode newD = new DNode(newElement);
        if (head != null)
        {
            newD.setNext(this.cursor.next);
            newD.setPrev(this.cursor);
            this.cursor.setNext(newD);
            this.cursor = this.cursor.getNext();
        }
        else
        {
            newD.setPrev(null);
            newD.setNext(null);
            this.head = newD;
            this.cursor = this.head;
        }


    }

    @Override
    public T remove()
    {
        T t;
        if (isEmpty())
            return null;
        if (this.cursor == this.head)
        {
            t = this.cursor.getElemet();
            this.head = this.cursor.getNext();
            this.cursor = this.cursor.getNext();

        }
        else
        {
            t = this.cursor.getElemet();
            (this.cursor.getPrev()).setNext(this.cursor.getNext());
            this.cursor = this.cursor.getNext();
            if (cursor == null)
                this.cursor = this.head;
        }

        return t;

    }

    @Override
    public T remove(T element)
    {
        DNode myC = this.head;
        while (myC != null)
        {
            if (myC.getElemet() == element)
                break;
            myC = myC.getNext();
        }
        if (myC == null)
            return null;
        this.cursor = myC;
        return remove();
    }

    @Override
    public void clear()
    {
        this.head = null;
        this.cursor = null;
    }

    @Override
    public void replace(T newElement)
    {
        if (this.isEmpty()  || newElement == null )
            throw new RuntimeException("ERROR: list or element are null");
        this.cursor.element = newElement;
    }

    @Override
    public boolean isEmpty()
    {
        if (this.head == null)
            return true;
        return false;
    }

    @Override
    public boolean goToBeginning()
    {
        if (this.isEmpty())
            return false;
        this.cursor = this.head;
        return true;
    }

    @Override
    public boolean goToEnd()
    {
        if (this.isEmpty())
            return false;
        while (this.cursor.getNext() != null)
            this.cursor = this.cursor.getNext();
        return true;
    }

    @Override
    public T getNext()
    {
        if (this.isEmpty())
            return null;
        if (this.cursor.getNext() == null)
            return null;
        this.cursor = this.cursor.getNext();
        return this.cursor.getElemet();
    }

    @Override
    public T getPrev()
    {
        if (this.isEmpty())
            return null;
        if (this.cursor == this.head)
            return null;
        this.cursor = this.cursor.getPrev();
        return this.cursor.getElemet();
    }

    @Override
    public T getCursor()
    {
        if (this.isEmpty())
            return null;
        return this.cursor.getElemet();
    }

    @Override
    public boolean hasNext()
    {
        if (this.isEmpty())
            return false;
        return this.cursor.getNext() != null;
    }

    @Override
    public boolean hasPrev()
    {
        if (this.isEmpty())
            return false;
        return this.cursor.getPrev() != null;
    }

    @Override
    public String toString()
    {
        if (this.isEmpty())
            return "DLinkedList{EMPTY}";
        DNode myC = this.head;
        String str = "DLinkedList{";
        str += this.head.getElemet();
        while (myC.getNext() != null)
        {
            str += "--> ";
            str += myC.getElemet();
        }
        return  str;
    }
}







