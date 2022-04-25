

public class DLinkedList<T> implements List<T> {
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

        public void setElement(T element) {
            this.element = element;
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

    private DNode cursor;
    private DNode head;

    public DLinkedList() {
        this.cursor = null;
    }

    public void insert(T newElement) {
        if (newElement == null) {
            throw new RuntimeException();
        }
        DNode newNode = new DNode(newElement);
        if (cursor == null) {
            newNode.setNext(null);
            newNode.setPrev(null);
            cursor = newNode;
            head = newNode;
            return;
        }
        if (cursor.getNext() == null) {
            newNode.setPrev(cursor);
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
            cursor = newNode;
            return;
        }

        newNode.setPrev(cursor);
        newNode.setNext(cursor.getNext());
        cursor.setNext(newNode);
        newNode.getNext().setPrev(newNode);
        cursor = newNode;
    }


    public T remove() {
        if (cursor == null) {
            return null;
        }
        T element = cursor.getElement();
        if (cursor.getNext() == null && cursor.getPrev() == null) {
            head = null;
            cursor = null;
            return element;
        }
        if (cursor.getNext() == null) {
            cursor.getPrev().setNext(null);
            cursor = head;
            return element;
        }
        if (cursor.getPrev() == null) {
            head = head.getNext();
            cursor.getNext().setPrev(null);
            cursor = head;
            return element;
        }
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        cursor = cursor.getNext();
        return element;
    }


    public T remove(T element) {
        if (cursor == null) {
            return null;
        }
        goToBeginning();
        while (true) {
            if (cursor.getElement().equals(element)) {
                remove();
                return element;
            }
            if (!hasNext()) {
                break;
            }
            getNext();
        }

        return null;
    }


    public void clear() {
        this.cursor = null;
        this.head = null;
    }


    public void replace(T newElement) {
        if (newElement == null) {
            throw new RuntimeException();
        }
        if (cursor == null) {
            throw new RuntimeException();
        }

        cursor.setElement(newElement);
    }


    public boolean isEmpty() {
        return cursor == null;
    }


    public boolean goToBeginning() {
        if (cursor == null) {
            return false;
        }
        cursor = head;
        return true;
    }


    public boolean goToEnd() {
        if (cursor == null) {
            return false;
        }
        while (cursor.getNext() != null) {
            cursor = cursor.getNext();
        }
        return true;
    }


    public T getNext() {
        if (cursor == null) {
            return null;
        }
        if (cursor.getNext() != null) {
            DNode tmp = cursor.getNext();
            cursor = tmp;
            return cursor.getElement();

        }
        return null;
    }


    public T getPrev() {
        if (cursor == null) {
            return null;
        }

        if (cursor.getPrev() != null) {
            DNode tmp = cursor.getPrev();
            cursor = tmp;
            return cursor.getElement();

        }
        return null;

    }


    public T getCursor() {
        //return cursor == null ? null : cursor.getElement();
        if (cursor == null) {
            return null;
        }
        return cursor.getElement();
    }


    public boolean hasNext() {
        if (cursor == null) {
            return false;
        }
        return cursor.getNext() != null;
    }


    public boolean hasPrev() {
        if (cursor == null) {
            return false;
        }
        return cursor.getPrev() != null;
    }


    public String toString() {
        if (cursor == null) {
            return null;
        }
        goToBeginning();
        StringBuilder str = new StringBuilder();
        while (hasNext()) {
            str.append(cursor.getElement()).append(" ");
            getNext();
        }

        return new String(str);
    }
}
