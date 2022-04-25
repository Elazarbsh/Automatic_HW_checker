
public class DLinkedList<T> implements List<T>{
    private DNode<T> head;
    private DNode<T> cursor;

    public DLinkedList() {
        head = null;
        cursor=null;
    }
    @Override
    public void insert(T newElement) {
        if (newElement==null){
            throw new IllegalArgumentException();
        }

        DNode<T> inNode = new DNode<T>(newElement);
        if (isEmpty()){   //(head == null)
            head = inNode;
            goToBeginning();
            }
        else {//from a->b and b->a to a->dnode->b and b=>dnode->a
            if (hasNext()){
                DNode<T> tmp = cursor.getNext();//b
                tmp.setPrev(inNode);//b.prev->inode
                inNode.setNext(tmp);//inode.next->b
                cursor.setNext(inNode);//cur.next->inode
                inNode.setPrev(cursor);//dnode.prv->cur
            }
            else {
                DNode<T> tmp = cursor;//cursor is the last node
                tmp.setNext(inNode);
                inNode.setPrev(tmp);
                inNode.setNext(null);
            }
        }
        cursor=inNode;
    }

    @Override
    public T remove() {
        if (isEmpty()) {//head!=null
            return null;
        }
        T retVal = getCursor();
        DNode<T> tmp = cursor;
        if (hasNext()){
            if(hasPrev()){
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
            }
            else {
                cursor.getNext().setPrev(null);
                head = cursor.getNext();
//                    goToBeginning();//check! maybe cursor = head
            }
            cursor = cursor.getNext();
        }
        else{
            if (!hasPrev()){//!hasPrev&&!hasNext
                head=null;
                cursor=null;
            }
            else {
                cursor.getPrev().setNext(null);
                goToBeginning();
            }
        }

        return retVal;
    }

    @Override
    public T remove(T element) {
//        if (element==null){
//            throw new IllegalArgumentException();
//        }
        goToBeginning();

        while(getCursor()!=element && hasNext()){// או שהגענו לאיבר הסופי והם לא שווים או שהם שווים
            getNext();
//            cursor = cursor.getNext();
        }
        if (getCursor()==element){
            return remove();
        }
        return null;
    }

    @Override
    public void clear() {
        goToBeginning();
        while(!isEmpty()){ //head!=null
            remove();
        }
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty()){
            throw new NullPointerException();
        }
        if (newElement==null){
            throw new IllegalArgumentException();
        }
            insert(newElement);
            cursor=cursor.getPrev();
            remove();

    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()) {
            return false;
        }
        cursor=head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if (isEmpty())
            return false;
        DNode<T> tmp = head;
        while(tmp.getNext()!=null){
            tmp=tmp.getNext();
        }
        cursor=tmp;
        return true;
    }

    @Override
    public T getNext() {
        if (hasNext()){
            DNode<T> tmp = cursor.getNext();
            T retVal = tmp.getElement();
            cursor=tmp;
            return retVal;
        }
        return null;
    }

    @Override
    public T getPrev() {
        if (hasPrev()){
            DNode<T> tmp = cursor.getPrev();
            T retVal = tmp.getElement();
            cursor=tmp;
            return retVal;
        }
        return null;
    }

    @Override
    public T getCursor() {
        if (isEmpty()){
            return null;
        }
        DNode<T> copy = new DNode<T>(cursor.getElement());
        return copy.getElement();
    }

    @Override
    public boolean hasNext() {
        return cursor != null && cursor.getNext() != null && !isEmpty();
    }

    @Override
    public boolean hasPrev() {
        if( cursor == null || cursor.getPrev() ==null || isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        goToBeginning();
        str.append(getCursor());
        while(!isEmpty()) {
            str.append((", ")).append((getNext() != null) ? ", " : " ");
        }
        str.append("]");
        return str.toString();
    }
    private class DNode<T>{

        private T element;//element in the list
        private DNode<T> next;//reference to the next element
        private DNode<T> prev; //reference to the previous element

        public DNode(T element){
            this.element=element;
        }
        public T getElement(){
            return element;
        }
        public void setNext(DNode<T> next){
            this.next = next;
        }
        public DNode<T> getNext(){
            return next;
        }
        public void setPrev(DNode<T> prev){
            this.prev = prev;
        }
        public DNode<T> getPrev(){
            return prev;
        }
    }
}
