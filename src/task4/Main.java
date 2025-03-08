
package task4;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

/** Обчислення та відображення результатів<br>
* Містить реалізацію статичного методу main()
* @see Main#main
*/
public class Main {
    /** Ім'я файлу для збереження результатів */
    private static final String FNAME = "items.bin";
    
    /** Поле для відображення результатів */
    protected View view;
    
    /** Ініціалізує поле {@linkplain Main#view view} */
    public Main(View view) {
        this.view = view;
    }
    
    /** Ініціалізує поле {@linkplain ViewTable#items}
    * початковими значеннями
    */
    public void viewInit() {
        view.viewInit();
    }
    
    /** Зберігає поле {@linkplain ViewTable#items} у файлі
    * @throws IOException якщо виникає помилка введення-виведення
    */
    public void viewSave() throws IOException {
        if (view instanceof ViewTable) {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME))) {
                os.writeObject(((ViewTable)view).getItems());
                System.out.println("Дані збережено в файлі " + FNAME);
            }
        }
    }
    
    /** Відновлює поле {@linkplain ViewTable#items} із файлу
    * @throws IOException якщо виникає помилка введення-виведення
    * @throws ClassNotFoundException якщо клас не знайдено
    */
    @SuppressWarnings("unchecked")
    public void viewRestore() throws IOException, ClassNotFoundException {
        if (view instanceof ViewTable) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME))) {
                ((ViewTable)view).setItems((ArrayList<ViewTable.Item2d>) is.readObject());
                System.out.println("Дані відновлено з файлу " + FNAME);
            }
        }
    }
    
    /** Відображає меню користувача та обробляє вибір
    * @throws IOException у випадку помилки введення-виведення
    */
    public void menu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int width = 20; // Ширина таблиці за замовчуванням
        
        String menu = "\nМеню програми:"
                    + "\n1. Переглянути поточні дані"
                    + "\n2. Випадкова генерація даних"
                    + "\n3. Зберегти поточні дані"
                    + "\n4. Відновити останні збережені дані"
                    + "\n5. Табличний перегляд результатів"
                    + "\n6. Змінити ширину таблиці"
                    + "\n7. Вихід з програми"
                    + "\nВаш вибір > ";
        
        while (true) {
            System.out.print(menu);
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Помилка введення! Спробуйте ще раз.");
                continue;
            }
            
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Поточні дані:");
                        view.viewShow();
                        break;
                    case 2:
                        System.out.println("Генерація випадкових даних...");
                        viewInit();
                        view.viewShow();
                        break;
                    case 3:
                        System.out.println("Збереження даних...");
                        viewSave();
                        break;
                    case 4:
                        System.out.println("Відновлення даних...");
                        viewRestore();
                        view.viewShow();
                        break;
                    case 5:
                        System.out.println("Табличний перегляд:");
                        if (view instanceof ViewTable) {
                            ((ViewTable)view).init(width);
                        }
                        view.viewShow();
                        break;
                    case 6:
                        System.out.print("Введіть нову ширину таблиці: ");
                        try {
                            width = Integer.parseInt(scanner.nextLine());
                            if (view instanceof ViewTable) {
                                ((ViewTable)view).setWidth(width);
                                System.out.println("Ширина таблиці змінена на " + width);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Помилка введення! Використовується попереднє значення: " + width);
                        }
                        break;
                    case 7:
                        System.out.println("Завершення роботи...");
                        return;
                    default:
                        System.out.println("Невідомий вибір! Спробуйте ще раз.");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
    
    /** Виконується при запуску програми;
    * викликає метод {@linkplain Main#menu menu()}
    * @param args - параметри запуску програми
    */
    public static void main(String[] args) {
        Main main = new Main(new ViewableTable().getView());
        try {
            main.menu();
        } catch (IOException e) {
            System.out.println("Помилка введення-виведення: " + e.getMessage());
        }
    }
}
