
package task4;

/**
 * Product
 * (шаблон проектування
 * Factory Method)
 */
public interface View {
    /** Відображення даних */
    void viewShow();
    
    /** Ініціалізація даних */
    void viewInit();
    
    /** Заголовок відображення даних */
    void viewHeader();
    
    /** Основна частина відображення даних */
    void viewBody();
    
    /** Завершення відображення даних */
    void viewFooter();
}
