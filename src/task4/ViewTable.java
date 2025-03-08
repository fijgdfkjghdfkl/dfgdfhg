
package task4;
import java.util.Formatter;
import java.util.ArrayList;
import java.io.Serializable;

/** ConcreteProduct
* (шаблон проектування
* Factory Method)<br>
* Виведення у вигляді таблиці
*/
public class ViewTable implements View {
    /** Визначає ширину таблиці за замовчуванням */
    private static final int DEFAULT_WIDTH = 20;
    /** Поточна ширина таблиці */
    private int width;
    /** Масив елементів даних */
    private ArrayList<Item2d> items = new ArrayList<>();
    
    /** Встановлює {@linkplain ViewTable#width width}
    * значенням {@linkplain ViewTable#DEFAULT_WIDTH DEFAULT_WIDTH}
    */
    public ViewTable() {
        width = DEFAULT_WIDTH;
    }
    
    /** Встановлює {@linkplain ViewTable#width} значенням <b>width</b>
    * @param width визначає ширину таблиці
    */
    public ViewTable(int width) {
        this.width = width;
    }
    
    /** Встановлює {@linkplain ViewTable#width} значенням <b>width</b>
    * @param width визначає ширину таблиці
    * @param n кількість елементів колекції
    */
    public ViewTable(int width, int n) {
        this.width = width;
        for(int i = 0; i < n; i++) {
            items.add(new Item2d());
        }
    }
    
    /** Обчислення значення функції sin(x) (кут в градусах)
    * @param x аргумент функції
    * @return результат обчислення
    */
    protected double function(double x) {
        return Math.sin(Math.toRadians(x));
    }
    
    /** Встановлює поле {@linkplain ViewTable#width} значенням <b>width</b>
    * @param width нова ширина таблиці
    * @return задана параметром <b>width</b> ширина таблиці
    */
    public int setWidth(int width) {
        return this.width = width;
    }
    
    /** Повертає значення поля {@linkplain ViewTable#width}
    * @return поточна ширина таблиці
    */
    public int getWidth() {
        return width;
    }
    
    /** Виводить вертикальний роздільник шириною {@linkplain ViewTable#width} символів */
    private void outLine() {
        for(int i = width; i > 0; i--) {
            System.out.print('-');
        }
    }
    
    /** Викликає {@linkplain ViewTable#outLine()}; завершує виведення роздільником рядка */
    private void outLineLn() {
        outLine();
        System.out.println();
    }
    
    /** Виводить заголовок таблиці шириною {@linkplain ViewTable#width} символів */
    private void outHeader() {
        Formatter fmt = new Formatter();
        fmt.format("%s%d%s%2$d%s", "%", (width-3)/2, "s | %", "s\n");
        System.out.printf(fmt.toString(), "x ", "y ");
    }
    
    /** Виводить тіло таблиці шириною {@linkplain ViewTable#width} символів */
    private void outBody() {
        Formatter fmt = new Formatter();
        fmt.format("%s%d%s%2$d%s", "%", (width-3)/2, ".0f | %", ".3f\n");
        for(Item2d item : getItems()) {
            System.out.printf(fmt.toString(), item.getX(), item.getY());
        }
    }
    
    /** Повертає колекцію елементів
    * @return колекція елементів
    */
    public ArrayList<Item2d> getItems() {
        return items;
    }
    
    /** Встановлює нову колекцію
    * @param items нова колекція
    * @return попередня колекція
    */
    public ArrayList<Item2d> setItems(ArrayList<Item2d> items) {
        ArrayList<Item2d> oldItems = this.items;
        this.items = items;
        return oldItems;
    }
    
    /** Перевантаження (суміщення, overloading) методу;
    * встановлює поле {@linkplain ViewTable#width} значенням <b>width</b><br>
    * Викликає метод {@linkplain ViewTable#viewInit() viewInit()}
    * @param width нова ширина таблиці
    */
    public final void init(int width) { // перевантаження методу (method overloading)
        this.width = width;
        viewInit();
    }
    
    /** Перевантаження методу;
    * встановлює поле {@linkplain ViewTable#width} значенням <b>width</b><br>
    * Для об'єкта {@linkplain ViewTable} викликає метод {@linkplain ViewTable#init(double stepX)}
    * @param width нова ширина таблиці.
    * @param stepX передається методу <b>init(double)</b>
    */
    public final void init(int width, double stepX) { // перевантаження методу (method overloading)
        this.width = width;
        init(stepX);
    }
    
    /** Ініціалізація початковими значеннями
    * @param stepX крок аргументу функції
    */
    public void init(double stepX) { // перевизначення методу (method overriding)
        System.out.print("Ініціалізація... ");
        items.clear();
        for(double x = 0.0; x <= 360.0; x += stepX) {
            items.add(new Item2d(x, function(x)));
        }
        System.out.println("виконано. ");
    }
    
    /** Ініціалізація даних. */
    @Override
    public void viewInit() {
        init(10.0);
    }
    
    /** Виведення заголовку даних. */
    @Override
    public void viewHeader() {
        outHeader();
        outLineLn();
    }
    
    /** Виведення тіла даних. */
    @Override
    public void viewBody() {
        outBody();
    }
    
    /** Виведення кінця даних. */
    @Override
    public void viewFooter() {
        outLineLn();
    }
    
    /** Загальний метод відображення результатів */
    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
    
    /** Внутрішній клас для зберігання даних */
    public static class Item2d implements Serializable {
        /** X-координата точки */
        private double x;
        /** Результат обчислення функції */
        private double y;
        
        /** Конструктор за замовчуванням */
        public Item2d() {}
        
        /**
         * Конструктор з параметрами
         * @param x аргумент функції
         * @param y значення функції
         */
        public Item2d(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        /**
         * Отримати значення аргументу
         * @return аргумент функції
         */
        public double getX() {
            return x;
        }
        
        /**
         * Отримати значення функції
         * @return значення функції
         */
        public double getY() {
            return y;
        }
        
        /**
         * Встановити значення аргументу
         * @param x нове значення аргументу
         * @return попереднє значення
         */
        public double setX(double x) {
            double oldX = this.x;
            this.x = x;
            return oldX;
        }
        
        /**
         * Встановити значення функції
         * @param y нове значення функції
         * @return попереднє значення
         */
        public double setY(double y) {
            double oldY = this.y;
            this.y = y;
            return oldY;
        }
        
        /**
         * Представлення об'єкта у вигляді рядка
         * @return рядок у форматі x: [значення x], y: [значення y]
         */
        @Override
        public String toString() {
            return "x: " + x + ", y: " + y;
        }
    }
}
