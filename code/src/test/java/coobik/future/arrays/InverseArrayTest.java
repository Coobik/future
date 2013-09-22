package coobik.future.arrays;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


public class InverseArrayTest {

    @Test(expectedExceptions = { NullPointerException.class })
    public void checkInverseNullArray() {
        Object[] array = null;
        inverseArray(array);
    }

    @Test
    public void checkInverseStringArray() {
        String[] array = prepareStringArray(0);
        inverseArrayAndCheck(array);

        array = prepareStringArray(1);
        inverseArrayAndCheck(array);

        array = prepareStringArray(2);
        inverseArrayAndCheck(array);

        array = prepareStringArray(3);
        inverseArrayAndCheck(array);

        array = prepareStringArray(5);
        inverseArrayAndCheck(array);

        array = prepareStringArray(10);
        inverseArrayAndCheck(array);

        array = prepareStringArray(10);
        setRandomNullElement(array);
        inverseArrayAndCheck(array);

        array = prepareStringArray(11);
        inverseArrayAndCheck(array);

        array = prepareStringArray(11);
        setRandomNullElement(array);
        inverseArrayAndCheck(array);

        array = prepareStringArray(20);
        inverseArrayAndCheck(array);

        array = prepareStringArray(997);
        inverseArrayAndCheck(array);

        array = prepareStringArray(1000);
        inverseArrayAndCheck(array);
    }

    @Test
    public void checkInverseIntArray() {
        Integer[] array = prepareIntArray(0);
        inverseArrayAndCheck(array);

        array = prepareIntArray(1);
        inverseArrayAndCheck(array);

        array = prepareIntArray(2);
        inverseArrayAndCheck(array);

        array = prepareIntArray(3);
        inverseArrayAndCheck(array);

        array = prepareIntArray(5);
        inverseArrayAndCheck(array);

        array = prepareIntArray(10);
        inverseArrayAndCheck(array);

        array = prepareIntArray(11);
        inverseArrayAndCheck(array);

        array = prepareIntArray(20);
        inverseArrayAndCheck(array);

        array = prepareIntArray(997);
        inverseArrayAndCheck(array);

        array = prepareIntArray(1000);
        inverseArrayAndCheck(array);
    }

    private void inverseArrayAndCheck(Object[] array) {
        Object[] sampleArray = copyArray(array);
        inverseArray(array);
        checkArrayInversed(array, sampleArray);
    }

    private static void checkArrayInversed(Object[] inversedArray, Object[] originalArray) {
        Assert.assertEquals(inversedArray.length, originalArray.length);

        for (int i = 0; i < originalArray.length; i++) {
            int mirrorIndex = getMirrorIndex(inversedArray, i);
            Assert.assertEquals(originalArray[i], inversedArray[mirrorIndex]);
        }
    }

    private static void setRandomNullElement(Object[] array) {
        int indexOfNull = (new Random()).nextInt(array.length);
        array[indexOfNull] = null;
    }

    private static String[] prepareStringArray(int length) {
        String[] array = new String[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.toString(i);
        }

        return array;
    }

    private static Integer[] prepareIntArray(int length) {
        Integer[] array = new Integer[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        return array;
    }

    /**
     * Create a copy of the original array to preserve the original order
     * for the future checks.
     * Copying of the elements themselves is not needed.
     */
    private static Object[] copyArray(Object[] originalArray) {
        // we can use Arrays.copyOf(original, newLength)
        Object[] newArray = new Object[originalArray.length];

        for (int i = 0; i < originalArray.length; i++) {
            newArray[i] = originalArray[i];
        }

        return newArray;
    }

    /*
     * array[0] = 0
     * array[1] = 1
     * array[2] = 2
     * ...
     * 
     * |||
     * vvv
     * 
     * array[0] = 2
     * array[1] = 1
     * array[2] = 0
     * ...
     */

    private static void inverseArray(Object[] array) {
        if ((array == null)) {
            throw new NullPointerException("array");
        }

        if (array.length < 2) {
            return;
        }

        int middleIndex = array.length / 2;
        int indexToSwap;

        for (int i = 0; i < middleIndex; i++) {
            indexToSwap = getMirrorIndex(array, i);
            swapElements(array, i, indexToSwap);
        }

    }

    private static int getMirrorIndex(Object[] array, int i) {
        return (array.length - 1 - i);
    }

    private static void swapElements(Object[] array, int firstIndex, int secondIndex) {
        Object temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

}
