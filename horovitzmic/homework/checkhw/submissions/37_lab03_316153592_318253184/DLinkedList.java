public class DLinkedList<T> implements List<T> {
    private DNode head;
    private DNode tail;
    private DNode cursor;
    private DNode temp;
    int counter_elements = 0;

    public DLinkedList(){
        head = null;
        tail = null;
        cursor = null;
    }

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public void setElement(T element) {
            this.element = element;
        }

        public DNode(T element) {
            this.element = element;
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


    @Override
    public void insert(T newElement) {
        if(newElement==null) {
            throw new IllegalArgumentException();
        }
        DNode newNode=new DNode(newElement);

        if(head==null) { /*if there is no list*/
            head=newNode;
            newNode.setNext(null);
            newNode.setPrev(null);
        } else {
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor);
            if(cursor.getNext()!=null)
                cursor.getNext().setPrev(newNode);
            cursor.setNext(newNode);
        }
        cursor=newNode;
        counter_elements ++;
    }

    @Override
    public T remove() {
        if(head == null || cursor == null){
            return null;
        }
        T return_element = cursor.getElement();

        if(head == cursor && head.getNext() != null){
            head = head.getNext();
            cursor = head;
        }
        else if(head == cursor && head.getNext() == null){
            head =null;
            cursor = null;
        }

        else if(head != cursor && cursor.getNext() == null){
            temp = cursor.getPrev();
            temp.setNext(null);
            cursor = head;
        }
        else if(head != cursor && cursor.getNext() != null){
            temp = cursor.getPrev();
            cursor = cursor.getNext();
            temp.setNext(cursor);
            cursor.setPrev(temp);
        }
        counter_elements --;
        return return_element;
    }

    @Override
    public T remove(T element) {
        temp = head;
        while(temp != null && temp.getElement() != element){
            temp = temp.getNext();
        }
        if(temp != null){
            cursor = temp;
            return remove();
        }
        return null;
    }

    @Override
    public void clear(){
        head = null;
        tail = null;
        counter_elements = 0;
    }

    @Override
    public void replace(T newElement) {
        if(newElement == null || head == null){
            throw new IllegalArgumentException();
        }
        cursor.setElement(newElement);
    }

    @Override
    public boolean isEmpty() {
        if(counter_elements == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()){
            return false;
        }
        cursor = head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(this.isEmpty()){
            return false;
        }
        tail = cursor;
        while(!(tail.next == null))
            tail = tail.next;
        cursor = tail;
        return true;
    }

    @Override
    public T getNext() {
        if(hasNext()){
            cursor = cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getPrev() {
        if(!hasPrev()){
            return null;
        }
        cursor = cursor.getPrev();
        return cursor.getElement();
    }

    @Override
    public T getCursor() {
        if(!this.isEmpty()) {
            return this.cursor.element;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(cursor == null) {
            return false;
        }
        else if(cursor.getNext() == null) {
            return false;
        }
        return true;

    }

    @Override
    public boolean hasPrev() {
        if(cursor == head) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DLinkedList{" +
                "head=" + head +
                ", cursor=" + cursor +
                ", temp=" + temp +
                ", counter_elements=" + counter_elements +
                '}';
    }
}