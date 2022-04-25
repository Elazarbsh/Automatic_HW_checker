public class DLinkedList<T> implements List<T> {

    private DNode head;
    private DNode cursor = null;
    private DNode tail;

    public DLinkedList() {

    }

    @Override
    public String toString() {
        return "DLinkedList{}";
    }

    @Override
    public void insert(T newElement) {

        if (newElement != null) {
            if (isEmpty()) {
                head = new DNode(newElement);
                cursor = tail = head;
            } else {
                DNode newNode = new DNode(newElement);
                if (cursor != tail) {
                    DNode prevNext = cursor.getNext();
                    cursor.setNext(newNode);
                    newNode.setPrev(cursor);
                    newNode.setNext(prevNext);
                    prevNext.setPrev(newNode);

                } else {
                    cursor.setNext(newNode);
                    newNode.setPrev(cursor);
                    tail = newNode;
                }
                cursor = newNode;
            }
            return;
        }
        throw new RuntimeException("New element is equal to null");
    }

    @Override
    public T remove() {
        T res = null;
        if (head != null) {
            res = cursor.getElement();
            if (head == tail && head == cursor) {
                head = tail = cursor = null;
            } else if (head == cursor) {
                head = head.getNext();
                head.setPrev(null);
                cursor = head;
            } else if (cursor == tail) {
                tail = tail.getPrev();
                tail.setNext(null);
                cursor = head;
                return res;
            } else {
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                cursor = cursor.getNext();
            }
        }
        return res;
    }

    @Override
    public T remove(T element) {
        DNode s = getNode(element);
        if (s != null) {
            cursor = s;
            return remove();
        }
        return null;
    }

    private DNode getNode(T element) {
        DNode s;
        for (s = head; s != null && s.element != element; s = s.next) ;
        return s;
    }

    @Override
    public void clear() {
        head = tail = cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if (!isEmpty() && newElement != null) {
            DNode newNode = new DNode(newElement);
            if (head == tail && head == cursor) {
                head = tail = cursor = newNode;
            } else if (head == cursor) {
                DNode prevNext = head.getNext();
                head = newNode;
                head.setNext(prevNext);
            } else if (cursor == tail) {
                DNode prevNode = tail.getPrev();
                prevNode.setNext(newNode);
                tail = newNode;
            } else {
                cursor.getPrev().setNext(newNode);
                newNode.setPrev(cursor.getPrev());
                newNode.setNext(cursor.getNext());
                cursor.getNext().setPrev(newNode);
            }
            cursor = newNode;
            return;

        }
        throw new RuntimeException("List is empty or new element is null");
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean goToBeginning() {
        if (!isEmpty()) {
            cursor = head;
            return true;
        }
        return false;
    }

    @Override
    public boolean goToEnd() {
        if (!isEmpty()) {
            cursor = tail;
            return true;
        }
        return false;
    }

    @Override
    public T getNext() {
        if (hasNext()) {
            cursor = cursor.next;
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getPrev() {
        if (hasPrev()) {
            cursor = cursor.prev;
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getCursor() {
        if (cursor != null)
            return cursor.getElement();
        return null;
    }

    @Override
    public boolean hasNext() {
        return cursor != null && cursor != tail;
    }

    @Override
    public boolean hasPrev() {
        return cursor != null && cursor != head;
    }

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;


        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public DNode getNext() {
            return next;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getPrev() {
            return prev;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }
    }
}
