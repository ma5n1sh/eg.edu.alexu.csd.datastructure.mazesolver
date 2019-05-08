public class queuell {
private sLinkedList storage=new sLinkedList();
    /**
     * Inserts an item at the queue front.
     */
    public void enqueue(Object item){
        storage.add(item);
    }
    /**
     * Removes the object at the queue rear and returns it.
     */
    public Object dequeue(){
        if(storage.isEmpty()){throw new IllegalArgumentException("QUEUE IS EMPTY");}
        else {
            sNode n=storage.head;
            storage.head=storage.head.next;
            return n.data;
        }
    }
    /**
     * Tests if this queue is empty.
     */
    public boolean isEmpty(){
        return storage.isEmpty();
    }
    /**
     * Returns the number of elements in the queue
     */
    public int size(){
        return storage.size();
    }

}
