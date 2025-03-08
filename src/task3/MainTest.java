
package task3;

import java.io.IOException;

/**
 * Демонстраційний тестовий приклад
 */
public class MainTest {
    /** Точність порівняння дійсних чисел */
    private static final double DELTA = 1e-10;
    
    /** 
     * Перевірка результату обчислення синуса
     */
    public void testCalc() {
        ViewResult view = new ViewResult(5);
        view.init(90.0);
        Item2d item = new Item2d();
        int ctr = 0;
        
        item.setXY(0.0, 0.0);
        System.out.println("Очікується: " + item + ", отримано: " + view.getItems().get(ctr));
        if (!item.equals(view.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
        
        item.setXY(90.0, 1.0);
        System.out.println("Очікується: " + item + ", отримано: " + view.getItems().get(ctr));
        if (!item.equals(view.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
        
        item.setXY(180.0, 0.0);
        System.out.println("Очікується: " + item + ", отримано: " + view.getItems().get(ctr));
        if (!item.equals(view.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
        
        item.setXY(270.0, -1.0);
        System.out.println("Очікується: " + item + ", отримано: " + view.getItems().get(ctr));
        if (!item.equals(view.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
        
        item.setXY(360.0, 0.0);
        System.out.println("Очікується: " + item + ", отримано: " + view.getItems().get(ctr));
        if (!item.equals(view.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
    }
    
    /** Перевірка збереження/відновлення серіалізованого об'єкта */
    public void testRestore() {
        ViewResult view1 = new ViewResult(1000);
        ViewResult view2 = new ViewResult();
        // Обчислюємо значення функції з випадковим кроком приросту аргументу
        view1.init(Math.random() * 100.0);
        // Зберігаємо колекцію view1.items
        try {
            view1.viewSave();
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
            return;
        }
        // Завантажуємо колекцію view2.items
        try {
            view2.viewRestore();
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
            return;
        }
        // Повинні завантажити стільки ж елементів, скільки зберегли
        System.out.println("Розмір колекції view1: " + view1.getItems().size());
        System.out.println("Розмір колекції view2: " + view2.getItems().size());
        if (view1.getItems().size() != view2.getItems().size()) {
            System.out.println("Помилка: розміри колекцій не співпадають!");
        }
        // Причому ці елементи повинні бути рівні.
        // Для цього потрібно визначити метод equals
        if (!view1.getItems().containsAll(view2.getItems())) {
            System.out.println("Помилка: вміст колекцій не співпадає!");
        } else {
            System.out.println("Колекції ідентичні.");
        }
    }
    
    /** Перевірка табличного представлення */
    public void testViewTable() {
        ViewResult viewTable = new ViewResult(5);
        viewTable.init(90.0);
        Item2d item = new Item2d();
        int ctr = 0;
        
        item.setXY(0.0, 0.0);
        System.out.println("Очікується: " + item + ", отримано: " + viewTable.getItems().get(ctr));
        if (!item.equals(viewTable.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
        
        item.setXY(90.0, 1.0);
        System.out.println("Очікується: " + item + ", отримано: " + viewTable.getItems().get(ctr));
        if (!item.equals(viewTable.getItems().get(ctr++))) {
            System.out.println("Помилка порівняння!");
        }
        
        // Відображення таблиці для тесту
        viewTable.viewTableHeader();
        viewTable.viewTableBody();
        viewTable.viewTableFooter();
    }
    
    /** Перевірка фабричного методу */
    public void testFactory() {
        Viewable viewable1 = new ViewableResult();
        View view1 = viewable1.getView();
        
        System.out.println("view1 є екземпляром ViewResult: " + (view1 instanceof ViewResult));
        
        // Для другого тесту також використовуємо ViewableResult
        Viewable viewable2 = new ViewableResult();
        View view2 = viewable2.getView();
        
        System.out.println("view2 є екземпляром ViewResult: " + (view2 instanceof ViewResult));
    }
    
    /** Запуск тестів */
    public static void main(String[] args) {
        MainTest test = new MainTest();
        System.out.println("Тест 1: Перевірка обчислень");
        test.testCalc();
        
        System.out.println("\nТест 2: Перевірка збереження/відновлення");
        test.testRestore();
        
        System.out.println("\nТест 3: Перевірка табличного представлення");
        test.testViewTable();
        
        System.out.println("\nТест 4: Перевірка фабричного методу");
        test.testFactory();
    }
}
