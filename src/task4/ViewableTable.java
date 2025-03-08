
package task4;

/** ConcreteCreator
* (шаблон проектування
* Factory Method)<br>
* Оголошує метод,
* який "фабрикує" об'єкти
* @see Viewable
* @see ViewableTable#getView()
*/
public class ViewableTable implements Viewable {
    /** Створює об'єкт, що відображається {@linkplain ViewTable} */
    @Override
    public View getView() {
        return new ViewTable();
    }
}
