public class Main {
    public static void main(String[] args) {
        int numberOfIterations = 5;
        int countBeg = 1;
        int countEnd = 5;

        int elementSizeCounterBeg = (int) Math.pow(10, 3);
        int elementSizeCounterEnd = (int) Math.pow(10, 4);
        long nSizeInsertiontimeTotal = 0;
        long nSizeavgheapSorttimeTotal = 0;
        long nSizeavgrandomizedSelecttimeTotal = 0;
        long count = countEnd;


        for (int sizeCounter = elementSizeCounterBeg;  sizeCounter <= elementSizeCounterEnd; sizeCounter += 1000) {
            for (int iterationCount = countBeg; iterationCount <= countEnd; iterationCount += 1) {
                SortingPerformance project = new SortingPerformance(sizeCounter, false);
                nSizeInsertiontimeTotal += project.insertionSort();
                nSizeavgheapSorttimeTotal += project.buildMaxHeap();
                nSizeavgrandomizedSelecttimeTotal += project.randomizedSelect();
                System.out.println("\n");
            }
            long avgInsertionTime = nSizeInsertiontimeTotal/count;
            long avgHeapsortTime = nSizeavgheapSorttimeTotal/count;
            long avgRandomizedSelectTime = nSizeavgrandomizedSelecttimeTotal /count;
            System.out.println("For Size n: " + sizeCounter + "  Average Insertion time (microseconds): " + avgInsertionTime);
            System.out.println("For Size n: " + sizeCounter + "  Average Heapsort time (microseconds): " + avgHeapsortTime);
            System.out.println("For Size n: " + sizeCounter + "  Average RandomizeSelect time (microseconds): " + avgRandomizedSelectTime);
        }
    }
}