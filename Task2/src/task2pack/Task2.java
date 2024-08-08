package task2pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

public class Task2 {
	public static void main(String[] args) {
		if (args.length == 2) {
			String circle = args[0];
			String points = args[1];
			BigDecimal circleX = BigDecimal.ZERO, circleY = BigDecimal.ZERO, circleR = BigDecimal.ZERO;

			try (BufferedReader br = new BufferedReader(new FileReader(circle))) {
				String line1 = br.readLine();// считывание первой строки с координатами центра окружности
				String line2 = br.readLine();// считывание второй строки с радиусом окружности
				String[] partsLine1 = line1.split("[\\s]+");// разбиение первой строки на части по пробелам

				circleX = new BigDecimal(partsLine1[0]);
				circleY = new BigDecimal(partsLine1[1]);
				circleR = new BigDecimal(line2);

			} catch (Exception e) {
				System.out.println("Неправильно введена информация об окружности");
				return;
			}
			try (BufferedReader br = new BufferedReader(new FileReader(points))) {
				int lineCount = 0;
				String line;
				while ((line = br.readLine()) != null) { // подсчет и обработка строк
					lineCount++;
					if (lineCount > 100) {
						System.out.println("Превышено максимальное количество точек (100)");
						return;
					}

					String[] partsLine = line.split("[\\s]+"); // разбиение строки на части по пробелам
					BigDecimal pointX = new BigDecimal(partsLine[0]);
					BigDecimal pointY = new BigDecimal(partsLine[1]);
					BigDecimal distance = circleX.subtract(pointX).pow(2).add(circleY.subtract(pointY).pow(2))
							.sqrt(MathContext.DECIMAL64);

					switch (distance.compareTo(circleR)) {
					case -1:
						System.out.println(1); // Точка внутри окружности
						break;
					case 0:
						System.out.println(0); // Точка на окружности
						break;
					case 1:
						System.out.println(2); // Точка вне окружности
						break;

					}
				}

				if (lineCount < 1) {
					System.out.println("Недостаточное количество точек (минимум 1)");
				}

			} catch (Exception e) {
				System.out.println("Неправильно введены координаты точек");
			}

		}
	}
}