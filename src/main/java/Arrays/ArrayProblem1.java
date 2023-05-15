package Arrays;

import java.util.*;

public class ArrayProblem1 {

    public ArrayProblem1() {
        int[] answer1 = task1(new int[]{1, 2, 3, 4, 5});
        assert Arrays.equals(answer1, new int[]{120, 60, 40, 30, 24});
        int[] answer2 = task2(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(answer2));
        assert Arrays.equals(answer2, new int[]{120, 60, 40, 30, 24});
    }


    //With division
    private int[] task1(int[] input) {

        //Create new array of length equal to input
        int[] output = new int[input.length];

        //Find product of array
        int prod = 1;
        for(int i: input) {
            prod *= i;
        }

        //Divide product by value at index i
        for(int i = 0; i<input.length; i++) {
            output[i] = prod / input[i];
        }

        return output;
    }


    //Without division
    private int[] task2(int[] input) {
        //Here we do not divide so in order to stay in O(n) time complexity - we need to be clever
        //As multiples are the same regardless of the order multiplied in, i.e. 1 * 2 * 3 is the same as 3 * 1 * 2
        //We need to find all the multiples before the index - and the multiples after the index
        ArrayList<Integer> prefixProducts = new ArrayList<>();
        ArrayList<Integer> suffixProducts = new ArrayList<>();

        //Create our forward and backward arrays
        for(int i = 0; i<input.length; i++) {

            //If empty - just add first element - else multiply with previous element
            int preMultiple = prefixProducts.isEmpty() ? input[i] : input[i] * prefixProducts.get(i-1);
            prefixProducts.add(preMultiple);

            //Create reversed index
            //Take away one as Java is 0-indexed - then take away i to get current position
            int reversedIndex = input.length - 1 - i;
            int suffMultiple = suffixProducts.isEmpty() ? input[reversedIndex] : input[reversedIndex] * suffixProducts.get(i-1);
            suffixProducts.add(suffMultiple);
        }

        //Now flip the suffixProducts
        Collections.reverse(suffixProducts);

        //Create output array
        int[] desiredArray = new int[input.length];

        //With that we can implement our solution
        for(int i=0; i<input.length; i++) {
            if (i == 0) {
                //Our first element in the desired array is the second element in the backward array
                desiredArray[i] = suffixProducts.get(i+1);
            }else if (i == input.length - 1) {
                //Our final element in the desired array is the second to last element in the forward array
                desiredArray[i] = prefixProducts.get(i-1);
            }else {
                desiredArray[i] = prefixProducts.get(i-1) * suffixProducts.get(i+1);
            }
        }

        return desiredArray;
    }

}
