

public class DLinkedList<T> implements List<T>{

    private DNode head;
    private DNode tail;
    private DNode cursor;

    public DLinkedList(){
        this.head = null;
        this.tail = null;
        cursor = head;
    }

    @Override
    public void insert(T newElement) {
        if(newElement == null) {
            throw new NullPointerException();
        }
        if(isEmpty()){
            head = new DNode(newElement);
            cursor = head;
            tail = head;
        } else{
            DNode n = new DNode(newElement);
            n.setNext(cursor.getNext());
            n.setPrev(cursor);
            cursor.setNext(n);
            cursor = n;
            if(!hasNext()) tail = cursor;
        }
    }

    @Override
    public T remove() {
        if(isEmpty()) return null;
        T element = cursor.getElement();
        if(cursor == head && cursor == tail){
            head = null;
            tail = null;
            return element;
        } else if(cursor == tail){
            tail = cursor.getPrev();
            cursor.setNext(null);
            goToBeginning();
            return element;
        } else if(cursor != head && cursor != tail){
            cursor.getPrev().setNext(cursor.getNext());
            getNext();
        } else {
            getNext();
            head = cursor;
            cursor.setPrev(null);
        }
        return element;
    }

    @Override
    public T remove(T element) {
        T temp;
        cursor = head;
        if(isEmpty()) return null;
        if(cursor.getElement() == element){
            temp = cursor.getElement();
            if(hasNext()){
                getNext();
                head = cursor;
                head.setPrev(null);
                return temp;
            } else{
                head = null;
            }
        }
        while(getNext() != null){
            if(cursor.getElement() == element){
                if(cursor == tail){
                    temp = cursor.getElement();
                    tail = cursor.getPrev();
                    cursor.setNext(null);
                    goToBeginning();
                    return temp;
                }
                temp = cursor.getElement();
                cursor.getPrev().setNext(cursor.getNext());
                getNext();
                return temp;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public void replace(T newElement) {
        if(isEmpty() || (newElement == null)){
            throw new NullPointerException();
        }
        DNode n = new DNode(newElement);
        n.setPrev(cursor.getPrev());
        if(cursor != head){
            n.getPrev().setNext(n);
        }
        n.setNext(cursor.getNext());
        if(cursor != tail){
            n.getNext().setPrev(n);
        }
        cursor = n;
    }

    @Override
    public boolean isEmpty() {
        if(head == null){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()) return false;
        else{
            cursor = head;
            return true;
        }
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()) return false;
        else{
            cursor = tail;
            return true;
        }
    }

    @Override
    public T getNext() {
        if(!hasNext()){
            return null;
        } else{
            cursor = cursor.getNext();
            return cursor.getElement();
        }
    }

    @Override
    public T getPrev() {
        if(!hasPrev()){
            return null;
        } else{
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
    }

    @Override
    public T getCursor() {
        if(isEmpty()) return null;
        else{
            return cursor.getElement();
        }
    }

    @Override
    public boolean hasNext() {
        if(isEmpty()) return false;
        if(cursor.getNext() == null) return false;
        else{
            return true;
        }
    }

    @Override
    public boolean hasPrev() {
        if(isEmpty()) return false;
        if(cursor.getPrev() == null) return false;
        else{
            return true;
        }
    }
    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element){
            this.element = element;
        }

        public T getElement(){
            return element;
        }

        public void setNext(DNode next){
            this.next = next;
        }

        public DNode getNext(){
            return next;
        }

        public void setPrev(DNode prev){
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;
        }
    }
}
