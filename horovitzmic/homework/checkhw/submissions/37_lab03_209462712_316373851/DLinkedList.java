
public class DLinkedList<T> implements List<T>{

    private class DNode{
        private T element;
        private DNode next;
        private  DNode prev;

        public void setElement(T element) {
            this.element = element;
        }

        public DNode(T element){
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

    DNode head;
    DNode cursor;
    DNode temp;

    int counter_elements = 0;


    public DLinkedList(){
        head = null;
        temp = null;
    }

    @Override
    public void insert(T newElement) {
        if(newElement == null){
            throw new IllegalArgumentException();
        }
        if(head == null){
            head = new DNode(newElement);
            cursor = head;
        }
        else if(head.getNext() == null){
            head.setNext(new DNode(newElement));
            cursor = head.getNext();
            cursor.setPrev(head);
        }
        else if(head == cursor){
            temp = new DNode(newElement);
            cursor = head.getNext();
            head.setNext(temp);
            cursor.setPrev(temp);
            temp.setNext(cursor);
            temp.setPrev(head);
            cursor = temp;
        }
        else if(cursor != head && cursor.getNext() == null){
            temp = cursor;
            cursor.setNext(new DNode(newElement));
            cursor = cursor.getNext();
            cursor.setPrev(temp);
            cursor.setNext(null);
        }
        else{
            temp = cursor.getNext();
            cursor.setNext(new DNode(newElement));
            cursor = cursor.getNext();
            cursor.setPrev(temp.getPrev());
            temp.setPrev(cursor);
            cursor.setNext(temp);
        }
//        else if(head == null){
//          head = new DNode(newElement);
//          head.setNext(null);
//          head.setPrev(null);
//          cursor = head;
//        }
//        else if(cursor != head && cursor.getNext() == null){
//            temp = cursor;
//            cursor.setNext(new DNode(newElement));
//            cursor = cursor.getNext();
//            cursor.setPrev(temp);
//            cursor.setNext(null);
//        }
//
//        else if(head == cursor){
//            head.setNext(new DNode(newElement));
//            cursor = head.getNext();
//            cursor.setPrev(head);
//            cursor.setNext(null);
//        }
//
//
//
//        else if(cursor != head && cursor.getNext() != null){
//            temp = cursor.getNext();
//            cursor.setNext(new DNode(newElement));
//            cursor = cursor.getNext();
//            cursor.setPrev(temp.getPrev());
//            temp.setPrev(cursor);
//            cursor.setNext(temp);
//        }
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
    public void clear() {
        head = null;
        cursor = null;
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
        return (head == null);
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()) {
            return false;
        }
        cursor = head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()) {
            return false;
        }

        while(cursor.getNext() != null){
            cursor = cursor.getNext();
        }

        return true;
    }

    @Override
    public T getNext() {
        if(head == null){
            return null;
        }
        else if(cursor.getNext() != null){
            cursor = cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getPrev() {
        if(head == null){
            return null;
        }
        else if(cursor.getPrev() != null){
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getCursor() {
        if(isEmpty()){
            return null;
        }

        return cursor.getElement();
    }

    @Override
    public boolean hasNext() {
        if(isEmpty()){
            return false;
        }
        return cursor.getNext() != null;
    }

    @Override
    public boolean hasPrev() {
        if(isEmpty()){
            return false;
        }
        return cursor.getPrev() != null;
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
