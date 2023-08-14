package a0814.camp;

import java.util.*;
import java.io.*;

public class ArraysBinarySearch {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] ia = {1, 3, 5, 7, 9};
		
		System.out.println("*-10:\t" + Arrays.binarySearch(ia, -10));
		System.out.println("*-4:\t" + Arrays.binarySearch(ia, -4));
		System.out.println("*-1:\t" + Arrays.binarySearch(ia, -1));
		System.out.println("*0:\t" + Arrays.binarySearch(ia, 0));
		
		System.out.println();
		System.out.println("*1:\t" + Arrays.binarySearch(ia, 1));
		System.out.println("*2:\t" + Arrays.binarySearch(ia, 2));
		System.out.println("*3:\t" + Arrays.binarySearch(ia, 3));
		System.out.println("*4:\t" + Arrays.binarySearch(ia, 4));
		System.out.println("*5:\t" + Arrays.binarySearch(ia, 5));
		System.out.println("*6:\t" + Arrays.binarySearch(ia, 6));
		System.out.println("*7:\t" + Arrays.binarySearch(ia, 7));
		System.out.println("*8:\t" + Arrays.binarySearch(ia, 8));
		System.out.println("*9:\t" + Arrays.binarySearch(ia, 9));
		
		System.out.println();
		System.out.println("*10:\t" + Arrays.binarySearch(ia, 10));
		System.out.println("*12:\t" + Arrays.binarySearch(ia, 12));
		System.out.println("*99:\t" + Arrays.binarySearch(ia, 99));
	}

}
