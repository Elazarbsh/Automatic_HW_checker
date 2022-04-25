

public class DLinkedList<T> implements List<T>{
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

    private DNode head;
    private DNode cursor;

    public DLinkedList(){
        this.head = null;
        this.cursor = null;
    }

    public void insert(T newElement){
        if(newElement == null){
            throw new RuntimeException("The element is null");
        }
        DNode element=new DNode(newElement);
        if(isEmpty()==true){
            head = element;
            cursor = element;
            return;
        }

        if(hasNext()==false){
            element.setPrev(cursor);
            cursor.setNext(element);
            cursor = element;
            return;
        }

        if(hasPrev()==false && hasNext() == false){
            element.setPrev(head);
            head.setNext(element);
            element.setNext(null);
            cursor = element;
            return;
        }

        if(hasPrev()==false && head.getNext() !=null){
            element.setNext(head.getNext());
            element.setPrev(head);
            head.setNext(element);
            cursor = element;
            return;
        }

        element.setPrev(cursor);
        element.setNext(cursor.getNext());
        cursor.setNext(element);
        cursor = element;
    }

    public T remove() {


        DNode temp = cursor;


        if(isEmpty()==true){
            return null;
        }

        if(hasNext()==false&&hasPrev()==false){
            clear();
            return temp.getElement();
        }

        if(hasNext()==false && hasPrev() == true){
            cursor.getPrev().setPrev(null);
            cursor = head;
            head = temp;
            return temp.getElement();
        }
        if(hasPrev()==false){
            head = head.getNext();
            head.setPrev(null);
            cursor = head;
            return temp.getElement();
        }
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        cursor=cursor.getNext();
        return temp.getElement();
    }


    public T remove(T element) {

        DNode temp = head;
        DNode delete = null;

        if(isEmpty() == false && element != null){
            while(head.getElement() != element && head.getNext() != null ){
                head = head.getNext();
            }
            cursor = head;
            delete = cursor;
            if(cursor!=null && cursor.getElement() != element && cursor.getNext() == null){
                head = temp;
                return null;
            }



            if(hasNext()==false&&hasPrev()==false){
                clear();
                return delete.getElement();
            }

            if(cursor != null && hasNext() == false ){
                head.setPrev(null);
                cursor = temp;
                head = temp;
                return delete.getElement();
            }

            if(cursor != null && cursor == temp ){
                DNode next = head.getNext();
                head.setNext(null);
                head = next;
                head.setPrev(null);
                cursor = next;
                return delete.getElement();
            }



            if(hasPrev() == true && hasNext() == true){
                DNode next = head.getNext();
                DNode prev = head.getPrev();
                head.setNext(null);
                head.setPrev(null);
                head = prev;
                head.setNext(next);
                head = head.getNext();
                head.setPrev(prev);
                return delete.getElement();
            }


            return delete.getElement();
        }
        return null;

    }



    public void clear() {
        this.head = null;
        this.cursor = null;
    }


    public void replace(T newElement) {
        if(isEmpty()==true || newElement == null){
            throw new RuntimeException();
        }
        DNode toReplace = new DNode(newElement);
        DNode temp = head;

        if(hasPrev() == true){
            toReplace.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(toReplace);
        }

        if(hasNext() == true){
            toReplace.setNext(cursor.getNext());
            cursor.getNext().setPrev(toReplace);
        }
        cursor = toReplace;

    }


    public boolean isEmpty() {
        if(head==null){
            return true;
        }
        return false;
    }


    public boolean goToBeginning() {
        if(!isEmpty()){
            cursor = head;
            return true;
        }
        return false;
    }


    public boolean goToEnd() {
        if(isEmpty()==false){
            while(cursor.getNext() != null){
                cursor = cursor.getNext();

            }
            return true;
        }
        return false;
    }


    public T getNext() {
        if(cursor!=null && hasNext()==true && cursor.getNext()!=null){
            cursor = cursor.getNext();
            return cursor.getElement();
        }

        return null;
    }


    public T getPrev(){
        if(hasPrev()==true){
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
        return null;
    }


    public T getCursor() {
        if(isEmpty()==false){
            return cursor.getElement();
        }
        if(cursor==null){
            return null;
        }
        throw new RuntimeException();

    }


    public boolean hasNext() {

        if(cursor!=null && cursor.getNext()!=null){
            return true;
        }
        return false;
    }


    public boolean hasPrev() {
        if(cursor!=null&& cursor.getPrev() != null )
            return true;
        return false;
    }

    public String toString(){
        DNode tmp = head;
        String st = "";
        while(tmp != null){
            st +=tmp.getElement().toString()+" ";
            tmp = tmp.getNext();
        }
        return st;
    }
}
