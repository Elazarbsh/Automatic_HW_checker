/*
    Class - DLinkedList
    This class represents a linked list.
    Each node in the list is from type DNode.

 */

public class DLinkedList<T> implements List<T>{
    private DNode head;
    private DNode cursor;
    private int size;

    public DLinkedList (){
        size = 0;
    }

    /* Inserts newElement into the list. If the list is not empty,
       then inserts newElement after the cursor. Otherwise, inserts newElement as
       the first (and only) element in the list. In either case, moves the cursor to
       newElement */
    @Override
    public void insert(T newElement) {
        if (newElement == null){                // Throw exceptions if element is null
            throw new RuntimeException("newElement is null");
        }
        DNode newNode = new DNode(newElement);
        if (size == 0){                         // Insert to head of the list
            newNode.setPrev(null);
            newNode.setNext(null);
            head = newNode;
            cursor = head;
        }else{
            newNode.setPrev(cursor);
            if (hasNext()){                     // Insert to the middle of the list
                newNode.setNext(cursor.getNext());
                cursor.getNext().setPrev(newNode);
            }else{                              // Insert to the end of the list
                newNode.setNext(null);
            }
            cursor.setNext(newNode);
            getNext();
        }
        size++;
    }


    /* Removes the element marked by the cursor from the list. If the
       resulting list is not empty, then moves the cursor to the element that
       followed the deleted element. If the deleted element was at the end of the
       list, then moves the cursor to the beginning of the list. Returns the deleted
       element, or null if the list was empty. */
    @Override
    public T remove() {
        T deleted = null;
        if (!isEmpty()){
            deleted = cursor.getElement();
            if (hasNext() && hasPrev()){        // Removes from middle of the list
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                getNext();
            }else if (hasPrev()){               // Removes from end of the list
                cursor.getPrev().setNext(null);
                goToBeginning();
            }else{
                if (size == 1){                 // Removes the only node in the list
                    clear();
                }
                else {                          // Removes the first node in the list
                    getNext();
                    cursor.setPrev(null);
                    head = cursor;
                }
            }
            if (!isEmpty()) size--;
        }
        return deleted;
    }

    /* Removes element from the list. Moves the cursor to the element
       that followed the deleted element. If the deleted element was at the end of
       the list, then moves the cursor to the beginning of the list. Returns the
       deleted element, or null if it did not exist in the list.
       If this element appears several times, removes the first occurrence of it. */
    @Override
    public T remove(Object element) {
        T result = null;
        if ( (!isEmpty()) && (element != null) ){
            goToBeginning();
            do{                                 // Search for the element in the list
                if (cursor.getElement().equals(element)){
                    result = remove();
                    break;
                }else{
                    getNext();
                }
            }while (hasNext());
        }
        return result;
    }

    // Removes all the elements in a list.
    @Override
    public void clear() {
        head = null;
        cursor = null;
        size = 0;
    }

    /* Replaces the element marked by the cursor with newElement. The
	   cursor remains at newElement */
    @Override
    public void replace(T newElement) {
        if (newElement != null && !isEmpty()){
            DNode newNode = new DNode(newElement);
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor.getPrev());
            if (hasNext())
                cursor.getNext().setPrev(newNode);
            if (hasPrev())
                cursor.getPrev().setNext(newNode);
            cursor = newNode;
        }else{
            throw new RuntimeException();
        }
    }

    // Returns true if the list is empty. Otherwise, returns false.
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /* If the list is not empty, then moves the cursor to the
	   beginning of the list. Otherwise, returns false. */
    @Override
    public boolean goToBeginning() {
        if (!isEmpty()){
            cursor = head;
            return true;
        }
        return false;
    }

    /* If the list is not empty, then moves the cursor to the end of
       the list. Otherwise, returns false. */
    @Override
    public boolean goToEnd() {
        if (!isEmpty()){
            while(hasNext()){
                getNext();
            }
            return true;
        }
        return false;
    }

    /* If the cursor is not at the end of a list, then moves the
	   cursor to the next element and returns it. Otherwise, returns null. */
    @Override
    public T getNext() {
        if (hasNext()){
            cursor = cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }

    /* If the cursor is not at the beginning of a list, then moves
	   the cursor to the preceding element and returns it. Otherwise, returns null. */
    @Override
    public T getPrev() {
        if (hasPrev()){
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
        return null;
    }

    /* Returns a copy of the element marked by the cursor and null if
	   the list is empty. */
    @Override
    public T getCursor() {
        if (!isEmpty())
            return cursor.getElement();
        return null;
    }

    /* If the cursor is not at the end of the list then returns true.
	   Otherwise, returns false.
	   If the list is empty returns false. */
    @Override
    public boolean hasNext() {
        if (!isEmpty()){
            return cursor.getNext() != null;
        }
        return false;
    }

    /* If the cursor is not at the beginning of the list then returns
       true. Otherwise, returns false.
       If the list is empty returns false. */
    @Override
    public boolean hasPrev() {
        if (!isEmpty()){
            return cursor.getPrev() != null;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        if (goToBeginning()){
            result.append(cursor.getElement());
            while(hasNext()){
                getNext();
                result.append(" < - > ");
                result.append(cursor.getElement());
            }
        }
        return result.toString();
    }

    /*
        Class DNode
        This class represents nodes of elements from type T.
        Each node is linked to previous node and next node (or null).
     */
    private class DNode{
        private T element;                  // Element in the list
        private DNode next;                 // Reference to the next element
        private DNode prev;                 // Reference to the previous element

        public DNode(T element){            // Constructor
            this.element = element;
        }

        public T getElement(){              // Returns it's element
            return this.element;
        }

        public void setNext(DNode next){    // Sets reference to the next node in the list
            this.next = next;
        }

        public DNode getNext(){             // Returns reference to the next node in the list
            return next;
        }

        public void setPrev(DNode prev){    // Sets reference to the previous node in the list
            this.prev = prev;
        }

        public DNode getPrev(){             // Returns reference to the previous node in the list
            return prev;
        }
    }
}

