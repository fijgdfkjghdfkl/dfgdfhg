
/**
 * Класс для демонстрации простой функции умножения двух чисел.
 * 
 * @author Replit User
 * @version 1.0
 */
public class Main {
    
    /**
     * Главный метод программы.
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Пример использования функции умножения
        int a = 5;
        int b = 7;
        
        // Вызываем функцию умножения и выводим результат
        System.out.println("Результат умножения " + a + " и " + b + ": " + multiply(a, b));
        
        // Еще один пример
        double x = 2.5;
        double y = 3.0;
        System.out.println("Результат умножения " + x + " и " + y + ": " + multiply(x, y));
    }
    
    /**
     * Функция для умножения двух целых чисел.
     * 
     * @param a первое число
     * @param b второе число
     * @return результат умножения a и b
     */
    public static int multiply(int a, int b) {
        // Умножаем два целых числа и возвращаем результат
        return a * b;
    }
    
    /**
     * Перегруженная функция для умножения двух чисел с плавающей точкой.
     * 
     * @param a первое число
     * @param b второе число
     * @return результат умножения a и b
     */
    public static double multiply(double a, double b) {
        // Умножаем два числа с плавающей точкой и возвращаем результат
        return a * b;
    }
}
