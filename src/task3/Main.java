
package task3;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Обчислення та відображення результатів<br>
 * Містить реалізацію статичного методу main()
 * @see Main#main
 */
public class Main {
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain Item2d}
     */
    private View view;
    
    /** Ініціалізує поле {@linkplain Main#view view}. */
    public Main(View view) {
        this.view = view;
    }
    
    /** Відображає меню. */
    protected void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            do {
                System.out.println("Введіть команду...");
                System.out.print("'q' - вихід, 'v' - перегляд, 'g' - генерація, 's' - зберегти, 'r' - відновити, 't' - таблиця: ");
                try {
                    s = in.readLine();
                } catch(IOException e) {
                    System.out.println("Помилка: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Вихід.");
                    break;
                case 'v':
                    System.out.println("Перегляд поточних даних.");
                    view.viewShow();
                    break;
                case 'g':
                    System.out.println("Випадкова генерація.");
                    view.viewInit();
                    view.viewShow();
                    break;
                case 's':
                    System.out.println("Збереження поточних даних.");
                    try {
                        view.viewSave();
                    } catch (IOException e) {
                        System.out.println("Помилка серіалізації: " + e);
                    }
                    view.viewShow();
                    break;
                case 'r':
                    System.out.println("Відновлення останніх збережених даних.");
                    try {
                        view.viewRestore();
                    } catch (Exception e) {
                        System.out.println("Помилка серіалізації: " + e);
                    }
                    view.viewShow();
                    break;
                case 't':
                    System.out.println("Перехід до табличного перегляду.");
                    ViewResult tableView = (ViewResult)view;
                    tableView.viewInit();
                    tableView.viewTableHeader();
                    tableView.viewTableBody();
                    tableView.viewTableFooter();
                    break;
                default:
                    System.out.println("Неправильна команда.");
            }
        } while(s.charAt(0) != 'q');
    }
    
    /**
     * Виконується при запуску програми;
     * викликає метод {@linkplain Main#menu() menu()}
     * @param args - параметри запуску програми.
     */
    public static void main(String[] args) {
        Main main = new Main(new ViewableResult().getView());
        main.menu();
    }
}
