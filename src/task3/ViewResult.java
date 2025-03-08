
package task3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * ConcreteProduct
 * (Шаблон проектування
 * Factory Method)<br>
 * Обчислення функції,
 * збереження та відображення
 * результатів
 * @see View
 */
 
/**
 * Клас для зберігання координат точок
 */
class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    /** Координата x */
    private double x;
    /** Координата y */
    private double y;
    
    /**
     * Конструктор за замовчуванням
     */
    public Item2d() {
        x = 0.0;
        y = 0.0;
    }
    
    /**
     * Встановлює значення координат
     * @param x значення x
     * @param y значення y
     */
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Отримує значення x
     * @return поточне значення x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Отримує значення y
     * @return поточне значення y
     */
    public double getY() {
        return y;
    }
    
    /**
     * Перевизначення методу toString()
     * @return рядкове представлення об'єкта
     */
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
    
    /**
     * Перевизначення методу equals()
     * @param obj об'єкт для порівняння
     * @return true, якщо об'єкти рівні
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item2d item = (Item2d) obj;
        // Порівнюємо з урахуванням похибки для дійсних чисел
        return Math.abs(x - item.x) < 1e-10 && Math.abs(y - item.y) < 1e-10;
    }
}

/**
 * ConcreteProduct
 * (Шаблон проектування
 * Factory Method)<br>
 * Обчислення функції,
 * збереження та відображення
 * результатів
 * @see View
 */
public class ViewResult implements View {
    /** Ім'я файлу, що використовується при серіалізації */
    private static final String FNAME = "items.bin";
    /** Визначає кількість значень для обчислення за замовчуванням */
    private static final int DEFAULT_NUM = 10;
    /** Колекція аргументів та результатів обчислень */
    private ArrayList<Item2d> items = new ArrayList<Item2d>();
    
    /**
     * Викликає {@linkplain ViewResult#ViewResult(int n) ViewResult(int n)}
     * з параметром {@linkplain ViewResult#DEFAULT_NUM DEFAULT_NUM}
     */
    public ViewResult() {
        this(DEFAULT_NUM);
    }
    
    /**
     * Ініціалізує колекцію {@linkplain ViewResult#items}
     * @param n початкова кількість елементів
     */
    public ViewResult(int n) {
        for(int ctr = 0; ctr < n; ctr++) {
            items.add(new Item2d());
        }
    }
    
    /**
     * Отримати значення {@linkplain ViewResult#items}
     * @return поточне значення посилання на об'єкт {@linkplain ArrayList}
     */
    public ArrayList<Item2d> getItems() {
        return items;
    }
    
    /**
     * Обчислює значення функції
     * @param x аргумент обчислюваної функції
     * @return результат обчислення функції
     */
    private double calc(double x) {
        return Math.sin(x * Math.PI / 180);
    }
    
    /**
     * Обчислює значення функції та зберігає
     * результат у колекції {@linkplain ViewResult#items}
     * @param stepX крок приросту аргументу
     */
    public void init(double stepX) {
        double x = 0.0;
        for(Item2d item : items) {
            item.setXY(x, calc(x));
            x += stepX;
        }
    }
    
    /**
     * Викликає <b>init(double stepX)</b> з випадковим значенням аргументу<br>
     * {@inheritDoc}
     */
    @Override
    public void viewInit() {
        init(Math.random() * 360.0);
    }
    
    /**
     * Реалізація методу {@linkplain View#viewSave()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }
    
    /**
     * Реалізація методу {@linkplain View#viewRestore()}<br>
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }
    
    /**
     * Реалізація методу {@linkplain View#viewHeader()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewHeader() {
        System.out.println("Результати:");
    }
    
    /**
     * Реалізація методу {@linkplain View#viewBody()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewBody() {
        for(Item2d item : items) {
            System.out.printf("(%.0f; %.3f) ", item.getX(), item.getY());
        }
        System.out.println();
    }
    
    /**
     * Реалізація методу {@linkplain View#viewFooter()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewFooter() {
        System.out.println("Кінець.");
    }
    
    /**
     * Реалізація методу {@linkplain View#viewShow()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
    
    /**
     * Відображає заголовок для табличного режиму
     */
    public void viewTableHeader() {
        System.out.println("Таблиця значень функції sin(x) (кут в градусах):");
        System.out.println("===============================");
        System.out.println("|\tX\t|\tY\t|");
        System.out.println("===============================");
    }
    
    /**
     * Відображає дані в табличному режимі
     */
    public void viewTableBody() {
        for(Item2d item : items) {
            System.out.printf("|\t%.0f\t|\t%.3f\t|\n", item.getX(), item.getY());
        }
    }
    
    /**
     * Відображає закінчення таблиці
     */
    public void viewTableFooter() {
        System.out.println("===============================");
        System.out.println("Кінець таблиці.");
    }
}
