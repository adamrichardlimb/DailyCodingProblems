package Arrays;

import java.util.Arrays;

public class ArrayProblem2 {

    public ArrayProblem2() {
        int[] answer = task1(new int[] {3, 7, 5, 6, 9});
        assert Arrays.equals(answer, new int[]{1,3});
        int[] answer2 = task2(new int[] {3, 10, 5, 6, 9});
        assert Arrays.equals(answer2, new int[]{1,4});
    }

    public int[] task1(int[] toFind) {
        //In order to know the smallest window to sort to be sorted - we need to know the sorted array
        int[] sorted = toFind.clone();
        sortArray(sorted, 0, toFind.length - 1);

        //Now we have the sorted array - if the main array differs from the sorted array, update our variable
        int firstIndex = 0, lastIndex = 0;
        for (int i=0; i<toFind.length; i++) {
            if (sorted[i] != toFind[i]) {
                if (firstIndex == 0) firstIndex = i;
                else lastIndex = i;
            }
        }

        return new int[] {firstIndex, lastIndex};
    }

    public int[] task2(int[] toFind) {
        int firstIndex = 0, lastIndex = 0;

        //Provide values which will almost certainly be overwritten
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;


        for (int i = 0; i < toFind.length; i++) {

            //Set value to maximum if current index larger than max
            max = Math.max(max, toFind[i]);

            //If the current value is less than the maximum - set to index
            if (toFind[i] < max) lastIndex = i;
        }


        for (int i = toFind.length - 1; i >= 0; i--) {

            //Set value to maximum if current index larger than max
            min = Math.min(min, toFind[i]);

            //If the current value is greater than the maximum - set to index
            if (toFind[i] > min) firstIndex = i;
        }

        System.out.println(firstIndex);
        System.out.println(lastIndex);

        return new int[] {firstIndex, lastIndex};
    }

    //Implement a quick sort to sort the array
    private int[] sortArray(int[] toSort, int begin, int end) {

        //If beginning < end - we need to sort these two
        if (begin < end) {
            //Find the index of the partition
            int partitionIndex = partition(toSort, begin, end);

            //Sort to the left
            sortArray(toSort, begin, partitionIndex-1);

            //Sort to the right
            sortArray(toSort, partitionIndex+1, end);
        }

        return toSort;
    }

    private int partition(int[] toSort, int begin, int end) {
        //Always pivot around the last element
        int pivot = toSort[end];
        int i = (begin-1);

        for (int j=0; j<end; j++) {

            //If element at the start is less than the pivot, swap to the left
            if(toSort[j] <= pivot) {
                i++;

                int swapTemp = toSort[i];
                toSort[i] = toSort[j];
                toSort[j] = swapTemp;
            }
        }

        //All smaller elements are on the left of the pivot - so swap the end element and return the index we stopped swapping at
        int swapTemp = toSort[i+1];
        toSort[i+1] = toSort[end];
        toSort[end] = swapTemp;

        return i+1;
    }

}
