public class DLinkedList<T> implements List<T> {
    private DNode cursor;
    private int size;
    private DNode head;
    public DLinkedList(){
        cursor = null;
        size = 0;
        head = null;
    }
    @Override
    public void insert(T newElement) {
        if (newElement == null){
            throw new RuntimeException("illegal element");
        }
        if (isEmpty() == true){
            head = new DNode(newElement);
            cursor = head;
        }
        else{
            DNode newNode = new DNode(newElement);
            newNode.element = newElement;
            newNode.setPrev(cursor);
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
            cursor = cursor.getNext();
        }
    }

    @Override
    public T remove() {
        if (isEmpty() == true){
            return null;
        }
        if(cursor.getPrev() == null && cursor.getNext() == null){
            T deletedNode = cursor.element;
            clear();
            return deletedNode;
        }
        if (cursor.getNext() == null && cursor.getPrev() != null){
            T deletedNode = cursor.element;
            cursor.getPrev().setNext(null);
            goToBeginning();
            return deletedNode;
        }
        if(cursor.getPrev() == null){
            T deletedNode = cursor.element;
            cursor.getNext().setPrev(null);
            cursor = cursor.getNext();
            head = head.getNext();
            return deletedNode;
        }
        T deletedNode = cursor.element;
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        cursor = cursor.getNext();
        return deletedNode;
    }

    @Override
    public T remove(T element) {
        if (isEmpty() == true && element != null){
            return null;
        }
        goToBeginning();
        while (cursor.getElement() != element){
            if(cursor.getNext() == null){
                break;
            }
            cursor = cursor.getNext();
        }
        if(cursor.getElement() == element)
        {
            T deletedElement = remove();
            return deletedElement;
        }
        return null;
    }
    @Override
    public void clear() {
        if (isEmpty()){
            return;
        }
        cursor = null;
        head = null;
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty() == true || newElement == null){
            throw new RuntimeException("illegal element");
        }
        cursor.element = newElement;
    }

    @Override
    public boolean isEmpty() {
        if(cursor == null){
            return  true;
        }
        return false;
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()){
            return false;
        }
        cursor = head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()){
            return false;
        }
        while (cursor.next != null){
            cursor = cursor.next;
        }
        return true;
    }

    @Override
    public T getNext() {
        if (isEmpty()) {
            return null;
        }
        if (cursor.getNext() != null){
            cursor = cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getPrev() {
        if (isEmpty()) {
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
        if(isEmpty()){
            return null;
        }
        return cursor.element;
    }

    @Override
    public boolean hasNext() {
        if(isEmpty()){
            return false;
        }
        if (cursor.next == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasPrev() {
        if(isEmpty()){
            return false;
        }
        if (cursor == head){
            return false;
        }
        return true;
    }

    public String toString(){
        String str = "";
        goToBeginning();
        str.concat("[");
        while(head != null && hasNext()){
            str.concat(cursor.element.toString());
            str.concat(",");
            cursor = cursor.next;
        }
        str.concat("]");
        return str;
    }

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;
        public DNode( T element){
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
        public DNode getPrev(){
            return prev;
        }
    }
}
