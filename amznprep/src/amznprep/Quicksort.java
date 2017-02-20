package amznprep;

import java.util.Random;

public class Quicksort {
	
	public static void quicksort(int []a, int lo, int hi) {
		int p;
		if (lo < hi) {
			p = partition(a, lo, hi);
			quicksort(a, lo, p - 1);
			quicksort(a, p + 1, hi);
		}
	}
	
	public static int partition(int []a, int lo, int hi){
		int t;
		int p = a[hi];
		int i = lo - 1;
		for (int j = lo; j <= hi - 1; j++) {
			if (a[j] <= p) {
				i++;
				t = a[i];
				a[i] = a[j];
				a[j] = t;
				printA(a);
			}
		}
		t = a[i + 1];
		a[i + 1] = a[hi];
		a[hi] = t;
		printA(a);
		return i + 1;
	}
	
	public static void printA(int []a) {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < a.length; k++) {
			sb.append(a[k]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		int l = 10;
		int [] a = new int[l];
		Random r =  new Random(System.currentTimeMillis());
		for (int i = 0; i < l; i++) {
			a[i] = r.nextInt(100);
		}
		printA(a);
		quicksort(a, 0, l - 1);
//		printA(a);
	}
	
}


