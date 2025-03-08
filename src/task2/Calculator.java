
package task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Клас для виконання обчислень та операцій серіалізації/десеріалізації.
 * Використовує агрегування класу NumberData.
 */
public class Calculator {
    /** Ім'я файлу для серіалізації */
    private static final String FNAME = "NumberData.bin";
    
    /** Зберігає дані для обчислень. Об'єкт класу {@linkplain NumberData} */
    private NumberData data;
    
    /**
     * Конструктор без параметрів.
     * Ініціалізує об'єкт {@linkplain Calculator#data}
     */
    public Calculator() {
        data = new NumberData();
    }
    
    /**
     * Встановлює значення {@linkplain Calculator#data}
     * @param data нове значення посилання на об'єкт {@linkplain NumberData}
     */
    public void setData(NumberData data) {
        this.data = data;
    }
    
    /**
     * Отримує значення {@linkplain Calculator#data}
     * @return поточне значення посилання на об'єкт {@linkplain NumberData}
     */
    public NumberData getData() {
        return data;
    }
    
    /**
     * Ініціалізує дані та виконує обчислення.
     * @param x перший аргумент
     * @param y другий аргумент
     * @return результат обчислень
     */
    public double calculate(double x, double y) {
        data.setX(x);
        data.setY(y);
        return data.calculate();
    }
    
    /**
     * Виводить результат обчислень.
     */
    public void show() {
        System.out.println(data);
    }
    
    /**
     * Зберігає {@linkplain Calculator#data} в файлі {@linkplain Calculator#FNAME}
     * @throws IOException при помилці введення/виведення
     */
    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(data);
        os.flush();
        os.close();
    }
    
    /**
     * Відновлює {@linkplain Calculator#data} з файлу {@linkplain Calculator#FNAME}
     * @throws Exception при помилці десеріалізації
     */
    public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        data = (NumberData) is.readObject();
        is.close();
    }
}
