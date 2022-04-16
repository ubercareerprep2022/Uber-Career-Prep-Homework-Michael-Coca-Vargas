
/**
 * Part 4 Uber Homework
 * @author Michael Coca-Vargas
 */
public class Part4
{
    public class LinkedList<T> {
        private Node head;
        private Node tail;
        private int size;

        public LinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /**
         * Creates a linked list with head as the head node and tail node.
         * Sets the size to 0.
         */
        public LinkedList(Node head){
            this.head = head;
            this.tail = head;
            this.size = 0;
        }

        /**
         * Pushes new Node to back of the linked list.
         */
        public void push(Node newNode) {
            // empty linked list
            if (this.size == 0) {
                // head becomes new Node
                this.head = newNode;

                // tail becomes new Node
                this.tail = newNode;

                this.size += 1;
                return;
            }

            // old Tail is the current tail
            Node oldTail = this.tail;

            // sets the next node for the old Tail to new Node
            oldTail.next = newNode;

            // sets tail to new Node
            this.tail = newNode;

            this.size += 1;
        }

        /**
         * Removes last node from linked list and returns it.
         */
        public Node pop() {
            Node prevNode;
            Node removedNode = null;

            // avoid popping on empty linked list
            if (this.size == 0) {
                return removedNode;
            }

            // there is no previous node
            if (this.size == 1) {
                removedNode = this.tail;
                this.tail = null;
                this.head = null;
                this.size -= 1;

                return removedNode;
            }

            // finds the node before the tail in linked list
            prevNode = this.elementAt(this.size - 2);

            // removed Node is the current tail
            removedNode = this.tail;

            // the new tail is the node before the tail
            this.tail = prevNode;

            // sets the next of tail to null
            this.tail.next = null;

            // if the size is 1 the new Tail is also the new Head
            if (this.size == 1) {
                this.head = removedNode;
            }

            this.size -= 1;

            return removedNode;
        }

        /**
         * Inserts node in linked list at index.
         */
        public void insert(int index, Node newNode) {
            Node nextNode;
            Node prevNode;

            // linked list is empty no prev node
            if (this.size == 0) {
                // push new Node to end of linked list
                push(newNode);

                this.size += 1;

                return;
            }

            // head is only node in linked list
            if (this.size == 1) {
                // next Node is current head
                nextNode = this.head;

                // head is the new Node
                this.head = newNode;

                // head next is the next Node, previous head
                this.head.next = nextNode;

                this.size += 1;

                return;
            }

            // finds the previous node at the index
            prevNode = this.elementAt(index - 1);

            // next Node is the node being shifted to right by 1
            nextNode = prevNode.next;

            // sets the new Node before the old Node and after prev Node
            prevNode.next = newNode;

            // sets the new Node next to old Node
            newNode.next = nextNode;

            this.size += 1;
        }

        /**
         * Removes node at index in linked list.
         */
        public void remove(int index) {
            Node removedNode;
            Node prevNode;

            // linked list is empty nothing to remove
            if (this.size == 0) {
                return;
            }

            if (this.size == 1) {
                // removes head
                this.head = null;

                // removes tail
                this.tail = null;

                this.size -= 1;

                return;
            }

            // attempting to remove the head
            if (index == 0) {
                // removed Node is the current head
                removedNode = this.head;

                // head is the next node after head
                this.head = removedNode.next;

                // removes the next from previous head
                removedNode.next = null;

                return;
            }

            // find the previous node at the index
            prevNode = this.elementAt(index - 1);

            // the node being removed from linked list
            removedNode = prevNode.next;

            // sets prev Node next to the node after removed Node
            prevNode.next = removedNode.next;

            // removes removed Node next 
            removedNode.next = null;

            this.size -= 1;
        }

        /**
         * Finds the node at the given index in the linked list.
         */
        public Node elementAt(int index) {
            Node foundNode = null;
            Node tempNode = this.head;
            int count = 0;

            // the position is out bounds
            if (index >= this.size) {
                return foundNode;
            }

            while (tempNode != null) {
                // found the node 
                if (count == index) {
                    foundNode = tempNode;
                    break;
                }

                tempNode = tempNode.next;
                count += 1;
            }

            return foundNode;
        }

        /**
         * Returns size of linked list.
         */
        public int size() {
            return this.size;
        }

        /**
         * Goes through the nodes in the linked list checking
         * if the was Visited attribute is true. It only becomes
         * true when iterating through the linked list.
         */
        public boolean hasCycle() {
            boolean foundCycle = false;
            Node tempNode = this.head;

            // goes through the linked list
            while (tempNode != null) {
                // if the node was visited before it means
                // there was a cycle
                if (tempNode.wasVisited) {
                    foundCycle = true;
                }

                // set the node was Visited to true
                tempNode.wasVisited = true;

                tempNode = tempNode.next;
            }

            // reset the was Visited field of all the nodes
            while (tempNode != null) {
                tempNode.wasVisited = false;
                tempNode = tempNode.next;
            }

            return foundCycle;
        }

        /**
         * Prints all the nodes data in the linked list.
         */
        public void printList() {
            Node tempNode = this.head;

            while (tempNode != null) {
                System.out.println(tempNode.data);
                tempNode = tempNode.next;
            }
        }

        /* node class to create the linked list*/
        private class Node<T> {
            private T data;
            private Node next;
            private boolean wasVisited;

            public Node(T data) {
                this.data = data;
                this.next = null;
                this.wasVisited = false;
            }

            public Node(T data, Node next) {
                this.data = data;
                this.next = next;
            }
        }
    }

}
