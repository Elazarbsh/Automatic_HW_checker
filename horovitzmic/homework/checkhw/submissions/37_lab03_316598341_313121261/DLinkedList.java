public class DLinkedList<T> implements List<T> {
    private DNode head;
    private DNode cursor;

    public DLinkedList(){
        head=null;
        cursor=null;

    }
    private class DNode{
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

    public void insert(T newElement) {
            if(newElement==null){
                throw new RuntimeException();
            }
            DNode add=new DNode(newElement);
            if(isEmpty()==true){
                head=add;
                cursor=add;
                return;
            }
             if(hasNext()==false){
                 add.setPrev(cursor);
                cursor.setNext(add);
                cursor=add;
                return;
              }
             if(head==cursor&&hasNext()==false){
                 add.setPrev(head);
                 head.setNext(add);
                 add.setNext(null);
                 cursor=add;
                 return;
             }
         if(head==cursor&&head.getNext()!=null){
            add.setNext(head.getNext());
            add.setPrev(head);
            head.setNext(add);
            cursor=add;
            return;
        }
             add.setPrev(cursor);
             add.setNext(cursor.getNext());
             cursor.setNext(add);
             cursor=add;

            }


    public T remove() {
        DNode temp=cursor;
        if(isEmpty()==true){
            return null;
        }
        if(hasNext()==false&&hasPrev()==false){
            head=null;
            cursor=null;
            return temp.getElement();
        }
        if(hasNext()==false&&cursor!=head){
            cursor.getPrev().setNext(null);
            cursor=head;
            return  temp.getElement();
        }
        if(cursor==head){
            head=head.getNext();
            head.setPrev(null);
            cursor=head;
            return  temp.getElement();
        }
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        cursor= cursor.getNext();
        return  temp.getElement();
        }

    public T remove(T element) {
        DNode temp = head;
        if (!isEmpty() && element != null) {
            while (temp != null) {
                if (temp.getElement() == element) {
                    if(temp==head){
                        if(temp.next==null){
                            head=null;
                            cursor=null;
                            return temp.getElement();
                        }
                        head=head.getNext();;
                        head.setPrev(null);
                        cursor=head;
                        return temp.getElement();
                    }
                    if (temp.next==null){
                        temp.getPrev().setNext(null);
                        cursor=head;
                    }
                    else{
                        temp.getPrev().setNext(temp.getNext());
                        temp.getNext().setPrev(temp.getPrev());
                        cursor=temp.getNext();
                    }
                    return temp.getElement();
                }
                temp = temp.getNext();
            }
        }
        return null;
    }

    public boolean hasNext() {
        if(cursor!=null){
            if(cursor.getNext()!=null)
                return true;
        }
        return false;
    }


    public boolean hasPrev() {
        if(cursor!=null) {
            if (cursor.getPrev() != null) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        if(!isEmpty()) {
            goToBeginning();
            while (hasNext()!=false) {
                remove();
            }
            head = null;
            cursor=null;
        }
    }


    public void replace(T newElement) {
        if(isEmpty() || newElement==null){
            throw new RuntimeException();}
        DNode temp=cursor;
        DNode a=new DNode(newElement);


        if(hasPrev()==true){
            a.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(a);
        }
        if(hasNext()==true){
            a.setNext(cursor.getNext());
            cursor.getNext().setPrev(a);
        }
        cursor=a;
    }


    public boolean isEmpty() {
        if(head==null){return true;}
        return false;
    }


    public boolean goToBeginning() {
        if (!isEmpty()){
            cursor=head;
            return true;
        }
        return false;
    }


    public boolean goToEnd() {
        if(!isEmpty()){
            DNode temp=head;
            while(temp.getNext()!=null){
                temp=temp.getNext();
            }
            cursor=temp;
            return true;
        }
        return false;
    }


    public T getNext() {
        if(hasNext()==true){
            cursor=cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }


    public T getPrev() {
            if(hasPrev()==true) {
                    cursor = cursor.getPrev();
                    return  cursor.getElement();
                }


        return null;
    }

    public T getCursor() {
        if(cursor==null){
            return null;
        }
        if(isEmpty()==false){
            return cursor.getElement();
        }
        throw new RuntimeException();
    }


    public String toString(){
        String st="";
        if(isEmpty()){
            st="null";
            return st;
        }
        DNode temp=head;
        while(temp.getNext()!=null){
            st+=temp.getElement()+"->\t";
            temp=temp.getNext();
        }
        return st;
    }
}
