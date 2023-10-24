public class PowerOfTwoMaxHeap<T extends Comparable<T>> {

    private final int powerOfTwo;
    private final Object[] elements;
    private int size;

    public PowerOfTwoMaxHeap(int powerOfTwo) {
        this.powerOfTwo = powerOfTwo;
        this.elements = new Object[1 << powerOfTwo];
        this.size = 0;
    }

    public void insert(T element) {
        // Add the new element to the end of the heap.
        elements[size++] = element;

        // Bubble the new element up the heap until it reaches its correct position.
        int parentIndex = (size - 1) / powerOfTwo;
        int childIndex = size - 1;
        while (parentIndex >= 0 && elements[childIndex].compareTo(elements[parentIndex]) > 0) {
            swap(elements, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / powerOfTwo;
        }
    }

    public T popMax() {
        // If the heap is empty, return null.
        if (size == 0) {
            return null;
        }

        // Get the maximum element from the root of the heap.
        T maxElement = (T) elements[0];

        // Move the last element in the heap to the root.
        elements[0] = elements[size - 1];
        size--;

        // Bubble the new root element down the heap until it reaches its correct position.
        int parentIndex = 0;
        int childIndex = 1;
        while (childIndex < size) {
            int leftChildIndex = childIndex;
            int rightChildIndex = childIndex + 1;

            // Find the maximum child of the parent.
            int maxChildIndex = leftChildIndex;
            if (rightChildIndex < size && elements[rightChildIndex].compareTo(elements[leftChildIndex]) > 0) {
                maxChildIndex = rightChildIndex;
            }

            // If the parent is smaller than its maximum child, swap them.
            if (elements[parentIndex].compareTo(elements[maxChildIndex]) < 0) {
                swap(elements, parentIndex, maxChildIndex);
                parentIndex = maxChildIndex;
                childIndex = parentIndex * powerOfTwo + 1;
            } else {
                break;
            }
        }

        return maxElement;
    }

    private void swap(Object[] elements, int index1, int index2) {
        Object temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
