public class DLinkedList<T> implements List<T>{
    private int size;
    private DNode<T> head;
    private DNode<T> cursor;
    public DLinkedList(){
        cursor=null;
        size=0;
        head=null;
    }
    @Override
    public void insert(T newElement) {
        if(newElement==null){
            System.out.println("Bad New Element");
            throw new NullPointerException();
        }
        DNode<T> new_node = new DNode<T>(newElement);
        if(isEmpty()){
            head = new_node;
            head.setPrev(null);
            head.setNext(null);
            cursor = head;
        }
        else{
            new_node.setNext(cursor.getNext());
            new_node.setPrev(cursor);
            cursor.setNext(new_node);
            cursor=cursor.getNext();
        }
        size++;

    }

    @Override
    public T remove() {
        if(isEmpty()){
            System.out.println("Remove from Null Link");
            return null;
        }
        DNode<T> to_del = new DNode<T>(cursor.getElement());
        DNode<T> prev_cur = cursor.getPrev();
        DNode<T> next_cur = cursor.getNext();
        if(next_cur==null&&prev_cur!=null){
            cursor.getPrev().setNext(null);
            goToBeginning();
        }
        else if(prev_cur==null&&next_cur!=null){
            cursor.getNext().setPrev(null);
            head=cursor.getNext();
            cursor.setNext(null);
            goToBeginning();
        }
        else if(size==1) {
            head=null;
            cursor=null;
        }
        else {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor=cursor.getNext();
        }
        size--;
        return to_del.getElement();
    }

    @Override
    public T remove(T element) {
        if(isEmpty() || element==null) {
            return null;
        }
        T x;
        DNode<T> temp = head;

        while(temp.getNext()!=null&&temp.getElement()!=element)
        {
          temp=temp.getNext();
        }
        if(temp.getElement()==element){
            cursor=temp;
            x = remove();
            return x;
        }
        return null;
    }

    @Override
    public void clear() {
        goToBeginning();
        while(cursor!=null){
            remove();
        }
        size=0;

    }

    @Override
    public void replace(T newElement) {
        if(isEmpty() || newElement==null) {
            throw new NullPointerException();
        }
        cursor.element=newElement;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()){
            return false;
        }
        cursor=head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()){
            return false;
        }
        while(cursor.getNext()!=null){
            cursor=cursor.getNext();
        }
        return true;
    }

    @Override
    public T getNext() {
        if(isEmpty()) {
            return null;
        }
        if(cursor.getNext()!=null) {
            cursor = cursor.getNext();
            T ret=cursor.getElement();
            return ret;
        }
       return null;
    }

    @Override
    public T getPrev() {
        if(isEmpty()) {
            return null;
        }
        if(cursor.getPrev()!=null) {
            cursor = cursor.getPrev();
            T ret=cursor.getElement();
            return ret;
        }
        return null;
    }

    @Override
    public T getCursor() {
        if(isEmpty()) {
            return null;
        }
        T ret=cursor.getElement();
        return ret;
    }

    @Override
    public boolean hasNext() {
        if(isEmpty()) {
            return false;
        }
        if(cursor.getNext()!=null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrev() {
        if(isEmpty()) {
            return false;
        }
        if(cursor.getPrev()!=null) {
            return true;
        }
        return false;
    }

}
