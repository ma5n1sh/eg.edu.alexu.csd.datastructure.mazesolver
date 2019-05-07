public class Stack  {
    private sLinkedList stackstorage=new sLinkedList();
    /**
     * Pushes an item onto the top of this stack.
     *
     * @param element
     * to insert
     */
    public void push(Object element){
        sNode n=new sNode();
        n.data=element;
        n.next=stackstorage.head;
        stackstorage.head=n;
    }
    /**
     * Removes the element at the top of stack and returns that element.
     *
     * @return top of stack element, or through exception if empty
     */
    public Object pop(){
        if(stackstorage.size()==0){throw new IllegalArgumentException("Stack is Empty");}
        sNode temp;
        temp=stackstorage.head;
        stackstorage.head=stackstorage.head.next;
        return temp.data;
    }
    /**
     * Get the element at the top of stack without removing it from stack.
     *
     * @return top of stack element, or through exception if empty
     */
    public Object peek(){
        if(stackstorage.size()==0){throw new IllegalArgumentException("Stack is Empty");}
        sNode temp;
        temp=stackstorage.head;
        return temp.data;
    }
    /**
     * Tests if this stack is empty
     *
     * @return true if stack empty
     */
    public boolean isEmpty(){
        return (stackstorage.size()==0);
    }
    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    public int size(){
        return stackstorage.size();
    }

}
