package task3;

/**
 * ConcreteCreator
 * (Шаблон проектування
 * Factory Method)<br>
 * Оголошує метод, що 
 * "фабрикує" об'єкт ViewResult
 * @see Viewable
 * @see ViewResult
 */
public class ViewableResult implements Viewable {
    /**
     * Створює об'єкт ViewResult
     * @return посилання на об'єкт, що реалізує інтерфейс View
     */
    @Override
    public View getView() {
        return new ViewResult();
    }
}