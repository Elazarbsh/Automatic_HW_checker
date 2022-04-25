public class DLinkedList<T> implements List<T>{
    private int cursize;
    private DNode cursor;

    public DLinkedList() {
        this.cursize = 0;
        this.cursor=null;
    }
    public DNode getHead(){
        if(cursize==0){
            return null;
        }
        DNode cur = this.cursor;
        while(cur.getPrev()!=null){
            cur=cur.getPrev();
        }
        return cur;
    }
    @Override
    public void insert(T newElement) {
        if(newElement==null){
            throw new RuntimeException();
        }
        if(this.cursor==null){
            this.cursor=new DNode(newElement);
            this.cursize+=1;
            return;
        }
        DNode oldnext = this.cursor.getNext();
        this.cursor.setNext(new DNode(newElement));
        this.cursor.getNext().setPrev(this.cursor);
        this.cursor.getNext().setNext(oldnext);
        this.cursor=this.cursor.getNext();
        this.cursize+=1;

    }

    @Override
    public T remove() {
        if(cursize==0){
            return null;
        }
        if(cursize==1){
            T ret = getHead().getElement();
            clear();
            return ret;
        }

        T ret=this.cursor.getElement();
        if(this.hasNext()){
            this.cursor.getNext().setPrev(this.cursor.getPrev());
            this.cursor=this.cursor.getNext();
        }
        else{
            this.cursor=this.getHead();
        }
        if(this.hasPrev()){
            this.cursor.getPrev().setNext(this.cursor.getNext());
        }

        this.cursize--;
        return ret;
    }

    @Override
    public T remove(T element) {
        if(cursize==1){

            T ret = getHead().getElement();
            if(ret==element){
                clear();
                return ret;
            }
            else{
                return null;
            }

        }
        DNode cur = this.getHead();
        while(cur!=null && cur.getNext()!=null){
            if(cur.getElement()==element){
                this.cursor=cur;
                T ret=remove();
                return ret;
            }
            cur=cur.getNext();
        }
        return null;
    }

    @Override
    public void clear() {
        this.cursor=null;
        this.cursize=0;

    }

    @Override
    public void replace(T newElement) {
        if(cursize==0 || newElement==null){
            throw new RuntimeException();
        }
        DNode newcursor= new DNode(newElement);
        newcursor.setNext(cursor.getNext());
        newcursor.setPrev(cursor.getPrev());
        cursor=newcursor;
        if(cursor.getPrev()!=null){
            cursor.getPrev().setNext(cursor);
        }
        if(cursor.getNext()!=null) {
            cursor.getNext().setPrev(cursor);
        }
    }

    @Override
    public boolean isEmpty() {
        if(cursize==0){
            return true;
        }
        return false;
    }

    @Override
    public boolean goToBeginning() {
        if(cursize==0){
            return false;
        }
        this.cursor=this.getHead();
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(cursize==0){
            return false;
        }
        while(this.cursor.getNext() != null){
            this.cursor=this.cursor.getNext();
        }
        return true;
    }

    @Override
    public T getNext() {
        if(!hasNext()) {
            return null;
        }
        this.cursor=this.cursor.getNext();
        return this.cursor.getElement();
    }

    @Override
    public T getPrev() {
        if(!hasPrev()) {
            return null;
        }
        this.cursor=this.cursor.getPrev();
        return this.cursor.getElement();
    }

    @Override
    public T getCursor() {
        if(this.cursize==0){
            return null;
        }
        return this.cursor.getElement();
    }

    @Override
    public boolean hasNext() {
        if (this.cursor==null || this.cursor.getNext() == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean hasPrev() {
        if (this.cursor==null || this.cursor.getPrev() == null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "derp";
//        DNode cur = this.getHead();
//        StringBuilder sb = new StringBuilder();
//        while(cur.getNext()!=null){
//           sb.append(cur.getElement().toString());
//           sb.append(" ");
//        }
//        return sb.toString();
    }

    private class DNode{
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
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

        public T getElement() {
            return element;
        }
    }
}
