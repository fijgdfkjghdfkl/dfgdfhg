
/**
 * Клас для демонстрації простої функції множення двох чисел.
 * 
 * @author Replit User
 * @version 1.0
 */
public class Main {
    
    /**
     * Головний метод програми.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        // Приклад використання функції множення
        int a = 5;
        int b = 7;
        
        // Викликаємо функцію множення та виводимо результат
        System.out.println("Результат множення " + a + " та " + b + ": " + multiply(a, b));
        
        // Ще один приклад
        double x = 2.5;
        double y = 3.0;
        System.out.println("Результат множення " + x + " та " + y + ": " + multiply(x, y));
    }
    
    /**
     * Функція для множення двох цілих чисел.
     * 
     * @param a перше число
     * @param b друге число
     * @return результат множення a та b
     */
    public static int multiply(int a, int b) {
        // Множимо два цілих числа та повертаємо результат
        return a * b;
    }
    
    /**
     * Перевантажена функція для множення двох чисел з плаваючою крапкою.
     * 
     * @param a перше число
     * @param b друге число
     * @return результат множення a та b
     */
    public static double multiply(double a, double b) {
        // Множимо два числа з плаваючою крапкою та повертаємо результат
        return a * b;
    }
}
