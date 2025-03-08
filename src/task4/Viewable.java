
package task4;

/**
 * Creator
 * (шаблон проектування
 * Factory Method)
 * @see ViewableTable
 */
public interface Viewable {
    /** Створює об'єкт, який відображається */
    View getView();
}
