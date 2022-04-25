public class DLinkedList<T> implements List<T> {
    private DNode head;
    private DNode tail;
    private DNode courser;
    private DNode temp = new DNode(null);
    private int size = 0;// shows the size of list

//default contarctor
    public DLinkedList() {
        head = null;
        tail = null;
        courser = null;
    }
    @Override
    //insert new element to list
    public void insert(T newElement) {
        //insert new element instead existed element(replace)
        if(courser==head&&courser!=null&&newElement!=null&&courser.getNext()!=null){
            replace(newElement);
            return;
        }
        if(newElement!=null) {
            //insert new element list is the list not empty
            if (!this.isEmpty()) {
                courser.setNext(new DNode( newElement));
                courser.getNext().setPrev(courser);
                courser = courser.getNext();
                tail=courser;
                //create new list by creating a Dnode and makes it as head
            } else {
                head = new DNode( newElement);
                courser = head;
                tail = head;
            }
            size++;
            //throw exeption if element is null
        } else {
            throw new NullPointerException("Should not work");
        }


    }

    @Override
    //remove element by current courser
    public T remove() {
        if (courser != null) {
            //if the list not empty and he is the last one
            if (!this.isEmpty() && !hasNext()) {
                temp.element=courser.element;
                DNode prev = courser.getPrev();
                if (prev != null) {
                    prev.setNext(null);
                }
                courser = head;
                size--;

                return temp.element;
            }
            //if the list not empty and he is not the last one
            if (!this.isEmpty() && hasNext()) {
                temp = courser;
                DNode prev = courser.getPrev();
                courser = courser.next;
                size--;
                if (prev != null) {
                    prev.setNext(courser);
                    courser.setPrev(prev);
                }
                return  temp.element;
            }
        }
        return null;
    }



    @Override
    //remove element by given one
    public T remove(T element) {
        DNode currNode = head;
        //loop for getting the correct element to replace
        while (currNode != null) {
            if (currNode.getElement().equals(element)) {
                courser = currNode;
                return remove();
            }
            currNode = currNode.getNext();
        }
        return null;

    }

    @Override
    //clear the list
    public void clear() {
        if(this.isEmpty())
            return;
        head=courser=tail=null;
        size=0;

    }

    @Override
    //replace elements by given element and current courser
    public void replace(T newElement) {
        //if the list is not empty and new elemnt is not null
            if (!this.isEmpty() && newElement != null) {
                courser.element =  newElement;

            }
            //throw exception
            else {
                throw new NullPointerException("Should not work");
            }

            }

    @Override
    //checks if the list is empty.if empty return true
    public boolean isEmpty() {
        if(size==0)
            return true;
        return false;
    }

    @Override
    //going to the beginning of the list
    public boolean goToBeginning() {
        if(!this.isEmpty()){
            courser=head;
            return true;
        }
        return false;
    }

    @Override
    //going to the end of the list
    public boolean goToEnd() {
        if(!this.isEmpty()){
            tail=courser;
            //loop for setting tail
            while(tail.next!=null)
                tail=tail.next;
            courser=tail;
            return true;
        }
        return false;
    }

    @Override
    //returns the next element
    public T getNext() {
        if(courser!=tail){
            courser =courser.getNext();
            return courser.element;
        }
        return null;
    }

    @Override
    //returns the previous element
    public T getPrev() {
        if(courser!=head ){
            courser=courser.getPrev();
            return courser.element;
        }
        return null;
    }

    @Override
    //return the current element
    public T getCursor() {
        if(!this.isEmpty()){
            return this.courser.element ;
        }
        return null;
    }

    @Override
    //checks if there is a next element
    public boolean hasNext() {
        if(courser!=tail )
            return true;
        return false;

    }

    @Override
    //checks if ther a previous element
    public boolean hasPrev() {
        if(courser!=head )
            return true;
        return false;
    }
    //given class
    private class DNode{
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element){
            this.element=element;
        }

        public T getElement(){
            return element;
        }
        public void setNext(DNode next){
            this.next=next;
        }
        public DNode getNext(){
            return next;
        }
        public void setPrev(DNode prev){
            this.prev=prev;
        }
        public  DNode getPrev(){
            return prev;
        }

    }
}
