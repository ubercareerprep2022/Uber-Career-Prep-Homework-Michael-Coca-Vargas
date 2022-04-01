/**
 * Part 3 Uber homework
 * @author Michael Coca-Vargas
 */
public class Part3 {
    /**
     * Stack class implemented using array.
     *
     * @author Michael Coca-Vargas
     */
    public class Stack <T extends Comparable<T>> {
        private int size;
        private T[] arrayStack;
        private int capacity;
        private T min;

        /**
        * Default constructor setting stack size to 50.
        */
        @SuppressWarnings("unchecked")
        public Stack() {
            this.capacity = 50;
            this.arrayStack = (T[]) new Object[50];
            this.size = 0;
        }

        /**
        * Parameterized constructor that takes in capacity.
        */
        @SuppressWarnings("unchecked")
        public Stack(int capacity)
        {
            this.capacity = capacity;
            this.arrayStack = (T[]) new Object[capacity];
            this.size = 0;
        }

        /**
         * Checks if the stack is full, if it is creates new stack with double the size.
         * If it is not adds the new Head at the top of the stack. 
         * Also checks if new Head is less than the current min.
         */
        public void push(T newHead) {
            // checks if stack is full
            if (this.isFull()) {
                this.doubleCapacity();
            }

            // shifts array to the right by 1 position
            for (int i = this.size - 1; i >= 0; i--) {     
                this.arrayStack[i + 1] = this.arrayStack[i];
            }

            // if the stack is empty or
            // the new Head is worth less than the current min
            if (this.isEmpty() || newHead.compareTo(this.min) < 0) {
                this.min = newHead;
            }

            // adds the new number to top of stack
            this.arrayStack[0] = newHead;

            // increases the size of the stack
            this.size += 1;
        }

        /**
         * Removes the value at the top of the stack, and
         * returns the value that was at the top of stack.
         */
        public T pop() {
            T head = this.arrayStack[0];

            // shifts array to the left by 1 position
            for (int i = 0; i < this.size - 1; i++) {
                this.arrayStack[i] = this.arrayStack[i+1];
            }

            // removes the duplicate value at the end
            this.arrayStack[this.size - 1] = null;

            // decreases the size of the stack
            this.size -= 1;

            return head;
        }

        /**
         * Returns what is at the top of the stack.
         */
        public T top() {
            return this.arrayStack[0];
        }

        /**
         * Checks if the stack is empty.
         */
        public boolean isEmpty() {
            if (this.size == 0) {
                return true;
            }

            return false;
        }

        /**
         * Returns the size of the stack.
         */
        public int size() {
            return this.size;
        }

        /**
         * Checks if the stack is full.
         */
        public boolean isFull() {
            if (this.size == this.capacity) {
                return true;
            }

            return false;
        }

        /**
         * Returns min, the smallest value in the stack.
         */
        public T min() {
            return this.min;
        }

        /**
        * Copies over the previous stack in an array with double
        * the size, then assigns it to arrayStack.
        */
        @SuppressWarnings("unchecked")
        public void doubleCapacity() {
            this.capacity *= 2;
            T[] doubleStack = (T[]) new Object[this.capacity];

            for (int i = 0; i < this.size; i++) {
                doubleStack[i] = this.arrayStack[i];
            }

            this.arrayStack = doubleStack;
        }
    }

    
    /**
     * Queue class implemented using array.
     *
     * @author Michael Coca-Vargas
     */
    public class Queue <T> {
        private int size;
        private T[] arrayQueue;
        private int capacity;

        /**
        * Default constructor sets queue size to 50.
        */
        @SuppressWarnings("unchecked")
        public Queue() {
            this.capacity = 50;
            this.arrayQueue = (T[]) new Object[50];
            this.size = 0;
        }

        /**
        * Parameterized constructor that takes in capacity.
        */
        @SuppressWarnings("unchecked")
        public Queue(int capacity) {
            this.capacity = capacity;
            this.arrayQueue = (T[]) new Object[capacity];
            this.size = 0;
        }

        /**
         * Checks if the queue is full, if it is creates new queue with double the size.
         * If it is not adds the new value at the back of the queue. 
         */
        public void enqueue(T newValue) {
            if (this.isFull()) {
                this.doubleCapacity();
            }

            // shifts array to the right by 1 position
            for (int i = this.size - 1; i >= 0; i--) {     
                this.arrayQueue[i + 1] = this.arrayQueue[i];
            }

            // adds the new number to top of stack
            this.arrayQueue[0] = newValue;

            // increases the size of the stack
            this.size += 1;
        }

        /**
         * Removes the value at the back of the queue.
         * Returns the value that was at the back of stack.
         */
        public T dequeue() {
            T back = this.arrayQueue[this.size - 1];

            // shifts array to the left by 1 position
            for (int i = 0; i < this.size - 1; i++) {
                this.arrayQueue[i] = this.arrayQueue[i+1];
            }

            // removes the duplicate value at the end
            this.arrayQueue[this.size - 1] = null;

            // decreases the size of the stack
            this.size -= 1;

            return back;
        }

        /**
         * Returns the value at the back of the queue.
         */
        public T rear() {
            return this.arrayQueue[this.size - 1];
        }

        /**
         * Returns the value at the front of the queue.
         */
        public T front() {
            return this.arrayQueue[0];
        }

        /**
         * Returns the size of the queue.
         */
        public int size() {
            return this.size;
        }

        /** 
         * Returns if the queue is empty.
         */
        public boolean isEmpty() {
            if (this.size == 0) {
                return true;
            }

            return false;
        }

        /**
         * Returns if the queue is full.
         */
        public boolean isFull() {
            if (this.size == this.capacity) {
                return true;
            }

            return false;
        }

        /**
        * Copies over the previous queue in an array with double
        * the size, then assigns it to arrayQueue.
        */
        @SuppressWarnings("unchecked")
        public void doubleCapacity() {
            this.capacity *= 2;
            T[] doubleQueue = (T[]) new Object[this.capacity];

            for (int i = 0; i < this.size; i++) {
                doubleQueue[i] = this.arrayQueue[i];
            }

            this.arrayQueue = doubleQueue;
        }
    }

}
