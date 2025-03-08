
package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Демонстрація серіалізації та десеріалізації.
 * Містить реалізацію статичного методу main().
 */
public class Main {
    /** Об'єкт класу {@linkplain Calculator}. Виконує обчислення. */
    private Calculator calculator = new Calculator();
    
    /**
     * Відображає меню для користувача.
     */
    private void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            do {
                System.out.println("\nВведіть команду...");
                System.out.print("'q' - вихід, 'v' - перегляд, 'g' - генерація випадкових значень, 's' - зберегти, 'r' - відновити: ");
                try {
                    s = in.readLine();
                } catch(IOException e) {
                    System.out.println("Помилка: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Вихід з програми.");
                    break;
                case 'v':
                    System.out.println("Поточні дані:");
                    calculator.show();
                    break;
                case 'g':
                    System.out.println("Генерація випадкових значень...");
                    double x = Math.random() * 100;
                    double y = Math.random() * 100;
                    
                    System.out.println("Згенеровано: x = " + x + ", y = " + y);
                    calculator.getData().setX(x);
                    calculator.getData().setY(y);
                    calculator.getData().calculate();
                    System.out.println("Результат згенеровано:");
                    calculator.show();
                    break;
                case 's':
                    System.out.println("Збереження даних...");
                    try {
                        calculator.save();
                        System.out.println("Дані збережено успішно!");
                    } catch (IOException e) {
                        System.out.println("Помилка серіалізації: " + e);
                    }
                    break;
                case 'r':
                    System.out.println("Відновлення даних...");
                    try {
                        calculator.restore();
                        System.out.println("Дані відновлено успішно!");
                        System.out.println("Примітка: поле y не серіалізується (transient), тому воно має значення 0.0");
                        calculator.show();
                    } catch (Exception e) {
                        System.out.println("Помилка десеріалізації: " + e);
                    }
                    break;
                default:
                    System.out.print("Невірна команда. ");
            }
        } while(s.charAt(0) != 'q');
    }
    
    /**
     * Допоміжний метод для зчитування дійсного числа.
     * @param reader об'єкт BufferedReader для зчитування вводу
     * @return зчитане число
     */
    private double readDouble(BufferedReader reader) {
        double value = 0.0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = reader.readLine();
                value = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Некоректне число. Спробуйте ще раз:");
            } catch (IOException e) {
                System.out.println("Помилка вводу: " + e);
                System.exit(0);
            }
        }
        
        return value;
    }
    
    /**
     * Точка входу в програму.
     * @param args параметри командного рядка
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}
