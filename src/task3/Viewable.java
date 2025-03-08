
package task3;

/**
 * Creator
 * (шаблон проектування
 * Factory Method)<br>
 * Оголошує метод,
 * що "фабрикує" об'єкти
 * @see Viewable#getView()
 */
public interface Viewable {
    /** Створює об'єкт, що реалізує {@linkplain View} */
    public View getView();
}
