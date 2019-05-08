public class sLinkedList {
    sNode head;

    public void add(int index, Object element) {
        int i = 0;
        sNode n = head;
        while (n.next != null && i < index-1) {
            n = n.next;
            i++;
        }
        if ((n.next == null && i < index-1) || index < 0) {
            throw new IllegalArgumentException("can't add at unexisting index");
        }
        if (i+1 == index) {
            sNode w = new sNode();
            w.data = element;
            w.next = n.next;
            n.next = w;
        }
        if (i == 0) {
            sNode w = new sNode();
            w.data = element;
            w.next = head;
            head = w;
        }
    }
    public sLinkedList copy(){
        sLinkedList output=new sLinkedList();
        sNode n=new sNode();
        n=head;
        while(n!=null){
            output.add(n.data);
            n=n.next;
        }
        return output;
    }

    public void add (Object element) {

        sNode node = new sNode();
        node.data = element;
        node.next = null;

        if (head == null) {
            head = node;
        }
        else {
            sNode n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }

    public Object get(int index) {
        sNode n = head;
        int i = 0;
        while (n.next != null && i < index) {
            n = n.next;
            i++;
        }
        if ((n.next == null && i != index) || index < 0) {
            throw new IllegalArgumentException("can't get at unexisting index");
        }else {
            return n.data;
        }
    }

    public void set(int index, Object element) {
        int i = 0;
        sNode n = head;
        while (n.next != null && i < index) {
            n = n.next;
            i++;
        }
        if ((n.next == null && i != index) || index < 0) {
            throw new IllegalArgumentException("can't set at unexisting index");
        }else {
            n.data = element;
        }
    }

    public void clear() {
        while (head.next != null) {
            sNode n = head;
            while (n.next.next != null) {
                n = n.next;
            }
            n.next.data = null;
            n.next = null;
        }
        head.next = null;
        head = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void remove(int index) {
        int i = 0;
        sNode n = head;
        if (index == 0) {
            head.data = null;
            head = head.next;
            return;
        }
        while (n.next != null && i < index-1) {
            n = n.next;
            i++;
        }
        if ((n.next == null && i+1 != index) || index < 0) {
            throw new IllegalArgumentException("can't set at unexisting index");
        }else {
            sNode w = n.next.next;
            n.next.data = null;
            n.next.next = null;
            n.next = w;
        }
    }

    public int size() {
        if(head==null){return 0;}
        int size = 1;
        sNode n = head;
        while (n.next != null) {
            n = n.next;
            size++;
        }
        return size;
    }

    public sLinkedList sublist(int fromIndex, int toIndex) {
        sLinkedList sub = new sLinkedList();
        sNode n1 = head;
        int i = 0;
        while (n1 != null && i < fromIndex) {
            n1 = n1.next;
            i++;
        }
        if ((n1 == null && i < fromIndex) || fromIndex < 0 || toIndex >= size()) {
            throw new IllegalArgumentException("can't get at unexisting index");
        }
        while (n1 != null && i < toIndex+1) {
            sub.add(n1.data);
            n1 = n1.next;
            i++;
        }
        return sub;
    }

    public boolean contains(Object o) {
        sNode n = head;
        while (n != null) {
            if (n.data == o) {
                return true;
            }
            n = n.next;
        }
        return false;
    }
}
