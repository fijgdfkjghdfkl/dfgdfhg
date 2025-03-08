
package task2;

import java.io.Serializable;

/**
 * Клас для зберігання параметрів та результатів обчислень.
 * Реалізує інтерфейс Serializable для можливості серіалізації.
 */
public class NumberData implements Serializable {
    /** Серіалізаційний ідентифікатор */
    private static final long serialVersionUID = 1L;
    
    /** Перший аргумент обчислень */
    private double x;
    
    /** Другий аргумент обчислень */
    // Ілюстрація використання transient - це поле не буде серіалізоване
    transient private double y;
    
    /** Результат обчислень */
    private double result;
    
    /**
     * Конструктор без параметрів.
     * Ініціалізує поля значеннями за замовчуванням.
     */
    public NumberData() {
        this.x = 0.0;
        this.y = 0.0;
        this.result = 0.0;
    }
    
    /**
     * Конструктор з параметрами.
     * @param x перший аргумент
     * @param y другий аргумент
     */
    public NumberData(double x, double y) {
        this.x = x;
        this.y = y;
        this.calculate();
    }
    
    /**
     * Встановлює значення поля x.
     * @param x нове значення
     * @return поточне значення x
     */
    public double setX(double x) {
        this.x = x;
        return this.x;
    }
    
    /**
     * Отримання значення поля x.
     * @return поточне значення x
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Встановлює значення поля y.
     * @param y нове значення
     * @return поточне значення y
     */
    public double setY(double y) {
        this.y = y;
        return this.y;
    }
    
    /**
     * Отримання значення поля y.
     * @return поточне значення y
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Отримання значення результату обчислень.
     * @return поточне значення result
     */
    public double getResult() {
        return this.result;
    }
    
    /**
     * Встановлює значення результату обчислень.
     * @param result нове значення
     * @return поточне значення result
     */
    public double setResult(double result) {
        this.result = result;
        return this.result;
    }
    
    /**
     * Обчислює результат на основі значень x та y.
     * В даному випадку результатом є сума квадратів аргументів.
     * @return обчислений результат
     */
    public double calculate() {
        this.result = Math.pow(x, 2) + Math.pow(y, 2);
        return this.result;
    }
    
    /**
     * Перевизначення методу toString для відображення даних.
     * @return рядок з інформацією про об'єкт
     */
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + ", result = " + result;
    }
    
    /**
     * Перевизначення методу equals для порівняння об'єктів.
     * @param obj об'єкт для порівняння
     * @return true, якщо об'єкти рівні, інакше false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        NumberData other = (NumberData) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
        if (Double.doubleToLongBits(result) != Double.doubleToLongBits(other.result)) return false;
        
        return true;
    }
}
