package task1pack;

import java.util.Scanner;

public class Task1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m, path;
		try {
			System.out.print("Введите длину массива:");
			n = scanner.nextInt();
			System.out.print("Введите длину интервала:");
			m = scanner.nextInt();
			scanner.close();
			// массив для вычислений не нужен
			/*
			 * int[] arr=new int[n]; for(int i=0;i<n;i++) { arr[i]=i+1; }
			 */
			System.out.println("Путь:");
			path = 1;
			do {
				System.out.print(path);
				
				path = (path + m - 2) % n + 1;

			} while (path != 1);
		} catch (Exception e) {
			System.out.println("Можно вводить только натуральные числа");
		}
	}
}
