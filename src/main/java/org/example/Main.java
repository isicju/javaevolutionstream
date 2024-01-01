package org.example;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.Random;

public class Main {
    static VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static void main(String[] args) {
        // Create two arrays
        int[] arr1 = fillArrayWithRandomInts();
        int[] arr2 = fillArrayWithRandomInts();

        IntVector vector1 = IntVector.fromArray(SPECIES, arr1, 0);
        IntVector vector2 = IntVector.fromArray(SPECIES, arr2, 0);

        long before = System.currentTimeMillis();
        addTwoScalarArrays(arr1,arr2);
        System.out.println("simple sum" + (System.currentTimeMillis() - before)/1000f + " seconds");
        long before2 = System.currentTimeMillis();
        vector1.add(vector2);
        System.out.println("vector sum sum" + (System.currentTimeMillis() - before2)/1000f + " seconds");
        System.out.println("");
    }

    public static int[] addTwoScalarArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        for(int i = 0; i< arr1.length; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }

    public int[] addTwoVectorArrays(int[] arr1, int[] arr2) {
        var v1 = IntVector.fromArray(SPECIES, arr1, 0);
        var v2 = IntVector.fromArray(SPECIES, arr2, 0);
        var result = v1.add(v2);
        return result.toArray();
    }

    // Method to fill an array with random integers
    private static int[] fillArrayWithRandomInts() {
        var array = new int[100_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();  // Fills with random integers
        }
        return array;
    }
}