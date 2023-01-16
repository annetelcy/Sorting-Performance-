import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Sort array using following implementations: Insertion Sort, HeapSort, Randomized Select
 * Measure the time taken for each sorting methods
 */
public class SortingPerformance {
    private int[] A;
    private int arraySize;
    private boolean printInternal;

    public SortingPerformance(int arraySize, boolean printInternal) {
        this.printInternal = printInternal;
        this.arraySize = arraySize + 1;
        this.A = new int[this.arraySize];

        Random random = new Random();

        for (int i = 1; i < this.arraySize; i++) {
            this.A[i] = random.nextInt(this.arraySize);
        }
    }

    public long insertionSort() {

        int[] Alg1 = this.A.clone();  // copy the array into a new array so that the data field A can be used again
        long startTime = System.nanoTime();
        int key;
        int n = Alg1.length;
        //int i;
        //[10,9,8,7,6,5]
        for (int j = 1; j < n; j++) {
            key = Alg1[j];
            int i = j - 1;
            while (i >= 0 && key < Alg1[i]) {
                Alg1[i + 1] = Alg1[i];
                i = i - 1;
                //--i;
                Alg1[i + 1] = key;
            }
        }
        long endTime = System.nanoTime();

        long insertionSortduration = endTime - startTime;
        insertionSortduration = TimeUnit.MICROSECONDS.convert(insertionSortduration, TimeUnit.NANOSECONDS);
        if (this.printInternal) {
            System.out.println("Size: " + (this.arraySize - 1) + " Time (nanoseconds): " + insertionSortduration + "  InsertionSort: " + Arrays.toString(Alg1));
        }
        return insertionSortduration;
    }

    public long buildMaxHeap() {
        int[] alg2 = this.A.clone();

        long startTime = System.nanoTime();
        int n = alg2.length;

        // Build max heap
        for (int i = n / 2 ; i >= 1; i--) {
            heapify(alg2, n, i);
        }

        // Heap sort
        for (int i = n - 1; i >= 0; i--) {
            int temp = alg2[0];
            alg2[0] = alg2[i];
            alg2[i] = temp;

            // Heapify root element
            heapify(alg2, i, 0);
        }
        long endTime = System.nanoTime();
        long heapSortduration = endTime - startTime;
        heapSortduration = TimeUnit.MICROSECONDS.convert(heapSortduration, TimeUnit.NANOSECONDS);

        if (this.printInternal) {
            System.out.println("Size: " + (this.arraySize - 1) + " Time (nanoseconds): " + heapSortduration + " HeapSort: " + Arrays.toString(alg2));
        }
        return heapSortduration;
    }

    private void heapify(int arr[], int n, int i) {
        // Find largest among root, left child and right child
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        // Swap and continue heapifying if root is not largest
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public long randomizedSelect() {
        int[] alg3 = this.A.clone();
        long startTime = System.nanoTime();
        randomizedSelect(alg3, 0, alg3.length - 1);
        long endTime = System.nanoTime();
        long randomizedSelectduration = endTime - startTime;
        randomizedSelectduration = TimeUnit.MICROSECONDS.convert(randomizedSelectduration, TimeUnit.NANOSECONDS);
        if (this.printInternal) {
            System.out.println("Size: " + (this.arraySize - 1) + " Time (nanoseconds): " + randomizedSelectduration +
                " Randomized Select: " + Arrays.toString(alg3));
        }
        return randomizedSelectduration;

    }

    public void randomizedSelect(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            randomizedSelect(arr, begin, partitionIndex - 1);
            randomizedSelect(arr, partitionIndex + 1, end);
        }
    }

    private int partition(int arr[], int begin, int end) {
        int randNum = new Random().nextInt(end);
        int pivot = arr[randNum];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }
}