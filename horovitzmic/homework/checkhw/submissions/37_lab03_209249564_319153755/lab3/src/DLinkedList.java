public class DLinkedList<T> implements List<T>{

    private DNode<T> cursor;
    private DNode<T> head;

    private  class DNode<T>{
        private  T element;
        private DNode<T> next;
        private DNode<T> prev;

        public DNode (T element){ this.element = element; }
        public T getElement(){
            return element;
        }
        public void setNext(DNode<T> next){
            this.next = next;
        }
        public DNode<T> getNext(){
            return next;
        }
        public  void setPrev(DNode<T> prev){
            this.prev = prev;
        }
        public DNode<T> getPrev(){
            return prev;
        }
    }
    public DLinkedList(){
        cursor = null;
        head =null;
    }

    public void insert(T newElement) {
         DNode<T> newNode = new DNode<T>(newElement);
        if(newElement == null) {
            new Exception("THE NEWELEMENT IS NULL");
        }
        if(head == null){
            newNode.setPrev(null);
            newNode.setNext(null);
            head=newNode;
            cursor=head;
        }
        else {
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            newNode.setPrev(head);
            cursor=cursor.getNext();
        }
    }

    public T remove() {
        if(head != null){
           T element=cursor.getElement();
            if(cursor.getNext()!= null && cursor.getPrev()!=null){
                cursor.getNext().setPrev(cursor.getPrev());
                cursor.getPrev().setNext(cursor.getNext());
                cursor= cursor.getNext();
                return element;
            }
            if(cursor.getNext()== null && cursor.getPrev()!=null){
                cursor.getPrev().setNext(null);
                cursor.setPrev(null);
                goToBeginning();
                return element;
            }
            if(cursor.getNext()== null && cursor.getPrev()==null){
                head=null;
                cursor=null;
                return element;
            }
            if(cursor.getNext()!= null && cursor.getPrev()==null){
                cursor.getNext().setPrev(null);
                cursor.setNext(null);
                cursor= cursor.getNext();
                return element;
            }
        }
        return null;
    }

    public T remove (T element){
        if (head != null) {
            while (head.getElement() != element) {
                if (head.getNext() == null)
                    return null;
                head = head.getNext();
            }
            cursor = head;
            return remove();
        }
        return null;
    }

    public void clear(){
        if(head != null){
            cursor= head;
            while(head != null) {
                remove();
            }
        }
    }

    public void replace ( T newElement ){
        if (head!=null && newElement!=null) {
            DNode<T> newNode = new DNode<T>(newElement);
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor.getPrev());
            if(cursor.getPrev()!=null)
                cursor.getPrev().setNext(newNode);
            if(cursor.getNext()!=null)
                cursor.getNext().setPrev(newNode);
            cursor=newNode;
        }
    }

    public boolean isEmpty(){
        if(head!=null){
            return true;
        }
        return false;
    }

    public boolean goToBeginning ( ){
        if(head!=null){
            cursor = head;
            return true;
        }
        return false;
    }

    public boolean goToEnd ( ){
        if (head!=null){
            while (cursor.getNext()!=null){
                cursor = cursor.getNext();
            }
            return true;
        }
        return false;
    }

    public T getNext ( ){
        if (cursor.getNext() !=null){
            cursor = cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }

    public T getPrev ( ){
        if (cursor.getPrev()!=null){
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
        return null;

    }

    public boolean hasNext ( ){
        if(head!=null){
            if(cursor.getNext()!=null){
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean hasPrev ( ) {
        if(head!=null){
            if(cursor.getPrev()!=null){
                return true;
            }
            return false;
        }
        return false;
    }

    public T getCursor ( ) {
        if(head!=null){
            return cursor.getElement();
        }
        return null;

    }

    public String toString() {
        return "DLinkedList{" + "cursor=" + cursor + ", head=" + head + '}';
    }
}
