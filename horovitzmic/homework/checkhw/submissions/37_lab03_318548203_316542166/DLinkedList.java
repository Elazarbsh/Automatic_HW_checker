import java.security.InvalidParameterException;

public class DLinkedList<T> implements List<T>{
    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
            this.element = element;
            next = null;
            prev = null;
        }

        public T getElement() {
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
    private DNode cursor;
    private DNode head;

    public DLinkedList() {
        cursor = null;
        head = null;
    }

    @Override
    public void insert(T newElement) {
        if(newElement == null){ // precondition
            throw new InvalidParameterException();
        }
        DNode dn = new DNode(newElement);
        if(!isEmpty()) {
            dn.prev = cursor;
            dn.next = cursor.next;
            if(hasNext()){  // not tail:
                cursor.next.prev = dn;
            }
            cursor.next = dn;
            if(!hasPrev()){ // cursor points to head
                head = cursor;
            }
            getNext();
        }
        else{ // empty list
            head = new DNode(newElement);
            cursor = head;
        }
    }

    @Override
    public T remove() {
        if(isEmpty()){
            return null; // return null if list is empty
        }

        T retVal = cursor.element;

        if(hasNext()){
            if(hasPrev()){ // cursor points to sandwiched node
                cursor.prev.next = cursor.next;
                cursor.next.prev = cursor.prev;
            }
            else{ // cursor points to head
                cursor.next.prev = null;
                head = cursor;
            }
            getNext();
        }
        else {
            if(hasPrev()){  // cursor points to tail
                cursor.prev.next = null;
                goToBeginning();
            }
            else{ // single node list
                head = null;
                cursor = null;
            }

        }

        return retVal;
    }

    @Override
    public T remove(T element) {
        if(isEmpty()){
            return null; // return null if list is empty
        }

        T retVal = null;
        DNode originalPosition = cursor;

        cursor = head; // start search from the head
        do{ // search for the given T element
            if(cursor.element == element){ // element found in list
                retVal = cursor.element;
                break;
            }
            if(hasNext()){
                getNext();
            }
            else{
                break;
            }
        }
        while(true);

        if(retVal == null){ // T element was not found
            cursor = originalPosition;
            return null;
        }

        if(hasNext()){
            if(hasPrev()){ // cursor points to sandwiched node
                cursor.prev.next = cursor.next;
                cursor.next.prev = cursor.prev;
            }
            else{ // cursor points to head
                cursor.next.prev = null;
                head = cursor.next;
            }
            getNext();
        }
        else {
            if(hasPrev()){  // cursor points to tail
                cursor.prev.next = null;
                goToBeginning();
            }
            else{ // single node list
                head = null;
                cursor = null;
            }

        }

        return retVal;
    }

    @Override
    public void clear() {
        head = null;
        cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if(isEmpty()){ // preconditions
            throw new RuntimeException();
        }
        if(newElement == null){
            throw new InvalidParameterException();
        }
        cursor.element = newElement;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public boolean goToBeginning() {
        if(!isEmpty()){
            cursor = head;
            return true;
        }
        return false;
    }

    @Override
    public boolean goToEnd() {
        if(!isEmpty()){
            while(cursor.next != null){
                cursor = cursor.next;
            }
            return true;
        }
        return false;
    }

    @Override
    public T getNext() {
        if(hasNext()){
            cursor = cursor.next;
            return cursor.element;
        }
        return null;
    }

    @Override
    public T getPrev() {
        if(hasPrev()){
            cursor = cursor.prev;
            return cursor.element;
        }
        return null;
    }

    @Override
    public T getCursor() {
        if(isEmpty()){ // precondition
            return null;
        }
        return cursor.element;
    }

    @Override
    public boolean hasNext() {
        if(isEmpty()){
            return false;
        }
        if(cursor.next != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrev() {
        if(isEmpty()){
            return false;
        }
        if(cursor.prev != null){
            return true;
        }
        return false;
    }

    public String toString(){
        String s = "";
        if(isEmpty()){
            return s;
        }
        DNode iterator = cursor;
        goToBeginning();
        while(iterator != null){
            s += iterator.element.toString();
            iterator = iterator.next;
        }
        return s;
    }
}
