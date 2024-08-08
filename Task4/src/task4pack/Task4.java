package task4pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Должен быть передан 1 аргумент");
			System.exit(1);
		}
		 String inputFile = args[0];
	        List<Integer> numbers = new ArrayList<>();

	        // Считываем числа из файла
	        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                numbers.add(Integer.parseInt(line.trim()));
	            }
	        } catch (Exception e) {
	            System.err.println("В файле допущена ошибка(элементы массива должны быть целыми числами и каждое записано с новой строки)");
	            return;
	        }
	        if (numbers.isEmpty()) {
	            System.out.println("Файл пуст");
	            return;
	        }

	        // Сортировка списка для нахождения медианы
	        Collections.sort(numbers);
	        // Нахождение медиану
	        int median;
	        int size = numbers.size();
	        if (size % 2 == 0) {
	            median = numbers.get(size / 2 - 1); // Медиана для четного числа элементов
	        } else {
	            median = numbers.get(size / 2); // Медиана для нечетного числа элементов
	        }

	        // Вычисление минимального количества ходов
	        int minMoves = 0;
	        for (int num : numbers) {
	            minMoves += Math.abs(num - median);
	        }

	        System.out.println(minMoves);
	    }
	}