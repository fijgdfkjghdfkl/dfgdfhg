
package task2;

import java.io.IOException;

/**
 * Клас для тестування коректності результатів обчислень та серіалізації/десеріалізації.
 */
public class CalculatorTest {
    /** Об'єкт класу {@linkplain Calculator}. Тестується. */
    private Calculator calculator = new Calculator();
    
    /**
     * Виконує тестування класів {@linkplain Calculator} та {@linkplain NumberData}.
     * Тестує коректність роботи методів та серіалізацію/десеріалізацію.
     */
    public void runTest() {
        // Тестування методу calculate
        System.out.println("Тестування обчислень...");
        testCalculations();
        
        // Тестування серіалізації/десеріалізації
        System.out.println("\nТестування серіалізації/десеріалізації...");
        testSerialization();
        
        System.out.println("\nВсі тести завершено!");
    }
    
    /**
     * Тестує коректність обчислень.
     */
    private void testCalculations() {
        double x = 3.0;
        double y = 4.0;
        double expectedResult = 25.0; // 3² + 4² = 9 + 16 = 25
        
        calculator.calculate(x, y);
        double actualResult = calculator.getData().getResult();
        
        System.out.println("Очікуваний результат: " + expectedResult);
        System.out.println("Фактичний результат: " + actualResult);
        
        if (Math.abs(expectedResult - actualResult) < 0.00001) {
            System.out.println("Тест обчислень пройдено успішно!");
        } else {
            System.out.println("Помилка: результати обчислень не співпадають!");
        }
    }
    
    /**
     * Тестує серіалізацію та десеріалізацію.
     */
    private void testSerialization() {
        // Встановлюємо початкові значення
        double x = 5.0;
        double y = 12.0;
        calculator.calculate(x, y);
        
        System.out.println("Початковий об'єкт:");
        calculator.show();
        
        // Зберігаємо об'єкт
        try {
            calculator.save();
            System.out.println("Об'єкт збережено успішно!");
        } catch (IOException e) {
            System.out.println("Помилка при серіалізації: " + e);
            return;
        }
        
        // Змінюємо значення для перевірки
        calculator.getData().setX(0.0);
        calculator.getData().setY(0.0);
        calculator.getData().setResult(0.0);
        
        System.out.println("Об'єкт після модифікації:");
        calculator.show();
        
        // Відновлюємо об'єкт
        try {
            calculator.restore();
            System.out.println("Об'єкт відновлено успішно!");
        } catch (Exception e) {
            System.out.println("Помилка при десеріалізації: " + e);
            return;
        }
        
        System.out.println("Відновлений об'єкт:");
        calculator.show();
        
        // Перевіряємо, чи коректно відновлено поля
        NumberData data = calculator.getData();
        
        if (Math.abs(data.getX() - x) < 0.00001) {
            System.out.println("Тест серіалізації поля x пройдено успішно!");
        } else {
            System.out.println("Помилка серіалізації: значення x не відновлено");
        }
        
        if (Math.abs(data.getY()) < 0.00001) { // y має бути 0, оскільки воно transient
            System.out.println("Тест transient поля y пройдено успішно - поле не серіалізовано!");
        } else {
            System.out.println("Помилка: transient поле y було серіалізовано!");
        }
        
        if (Math.abs(data.getResult() - 169.0) < 0.00001) { // 5² + 12² = 25 + 144 = 169
            System.out.println("Тест серіалізації поля result пройдено успішно!");
        } else {
            System.out.println("Помилка серіалізації: значення result не відновлено");
        }
    }
    
    /**
     * Точка входу для автономного тестування.
     * @param args параметри командного рядка
     */
    public static void main(String[] args) {
        CalculatorTest test = new CalculatorTest();
        test.runTest();
    }
}
