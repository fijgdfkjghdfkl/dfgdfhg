
package task4;

/**
 * Клас для тестування основної функціональності
 */
public class MainTest {
    /** Екземпляр класу {@linkplain Main} для тестування */
    private Main main;
    /** Екземпляр класу {@linkplain ViewTable} для тестування */
    private ViewTable viewTable;
    
    /**
     * Ініціалізація об'єктів для тестування
     */
    public void init() {
        viewTable = new ViewTable(30);
        main = new Main(viewTable);
    }
    
    /**
     * Тестування перевантаження методів
     */
    public void testOverloading() {
        System.out.println("Тестування перевантаження методів:");
        
        // Тест перевантаження методу init
        viewTable.init(25);
        System.out.println("Ширина після init(25): " + viewTable.getWidth());
        
        viewTable.init(35, 0.5);
        System.out.println("Ширина після init(35, 0.5): " + viewTable.getWidth());
    }
    
    /**
     * Тестування перевизначення методів
     */
    public void testOverriding() {
        System.out.println("Тестування перевизначення методів:");
        
        // Тест перевизначення методу init
        viewTable.init(0.5);
    }
    
    /**
     * Тестування поліморфізму
     */
    public void testPolymorphism() {
        System.out.println("Тестування поліморфізму:");
        
        // Використовуємо змінну суперкласу для посилання на різні об'єкти
        View view;
        
        // Використання поліморфізму - різні реалізації методу viewShow
        view = new ViewTable(40);
        System.out.println("ViewTable #1 з шириною 40:");
        view.viewInit();
        view.viewShow();
        
        view = new ViewTable(25);
        System.out.println("ViewTable #2 з шириною 25:");
        view.viewInit();
        view.viewShow();
    }
    
    /**
     * Виконання всіх тестів
     */
    public void runAllTests() {
        init();
        testOverloading();
        System.out.println();
        testOverriding();
        System.out.println();
        testPolymorphism();
    }
    
    /**
     * Головний метод для запуску тестів
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        MainTest test = new MainTest();
        test.runAllTests();
    }
}
