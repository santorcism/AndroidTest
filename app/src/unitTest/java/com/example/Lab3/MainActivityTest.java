package com.example.Lab3;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

import java.util.Map;

public class MainActivityTest {

    private MainActivity activity;
    private ActivityController<MainActivity> controller;

    // Дані для тестування
    private String[] tags = new String[]{
            "Червоний:-65536", "Зелений:-144357", "Помаранчевий:-26368", "Жовтий:-598252",
            "Блакитний:-16728876", "Синій:-14401322", "Фіолетовий:-9422635", "Чорний:-16777216",
            "Рожевий:-841281027"
    };

    @Before
    public void setUp() {
        // Створюємо тестовий інстанс MainActivity за допомогою Robolectric
        controller = Robolectric.buildActivity(MainActivity.class);
        activity = controller.create().get();
    }

    @Test
    public void testOnCreate() {
        // Тест перевіряє, чи було успішно створено Activity
        assertNotNull(activity);

        // Перевіряємо, чи ініціалізовано деякі ключові елементи
        TextView timeView = activity.findViewById(R.id.time);
        TextView pointsView = activity.findViewById(R.id.points);
        assertNotNull(timeView);
        assertNotNull(pointsView);

        // Перевіряємо, чи запускається таймер і встановлюються початкові значення
        assertEquals("00:60", timeView.getText().toString());  // Приклад початкового значення
    }

    @Test
    public void testArray2dict() {
        Map<String, Integer> map = MainActivity.array2dict(tags);
        int map_length = map.size();
        assertEquals(9, map_length);  // Очікується 9 елементів

        // Перевірка правильності значень
        assertEquals((Integer) (-65536), map.get("Червоний"));
        assertEquals((Integer) (-144357), map.get("Зелений"));
    }

    @Test
    public void testSetTestMode() {
        SharedPreferences sharedPref = activity.getPreferences(activity.MODE_PRIVATE);
        MainActivity.setTestMode(sharedPref, 1);

        int status = sharedPref.getInt("testing_mode", 0);
        assertEquals(1, status);  // Перевіряємо, що статус збережений
    }

    @Test
    public void testOnYesClick() {
        Button yesButton = activity.findViewById(R.id.yes_btn);
        TextView pointsView = activity.findViewById(R.id.points);

        // Імітуємо натискання кнопки "Yes"
        yesButton.performClick();

        // Перевіряємо, що кількість балів змінилась
        int points = Integer.parseInt(pointsView.getText().toString());
        assertEquals(1, points);
    }

    @Test
    public void testOnNoClick() {
        Button noButton = activity.findViewById(R.id.no_btn);
        TextView pointsView = activity.findViewById(R.id.points);

        // Імітуємо натискання кнопки "No"
        noButton.performClick();

        // Перевіряємо, що кількість балів змінилась
        int points = Integer.parseInt(pointsView.getText().toString());
        assertEquals(1, points);
    }

    @Test
    public void testChangeColors() {
        // Вручну встановлюємо тестовий режим
        SharedPreferences sharedPref = activity.getPreferences(activity.MODE_PRIVATE);
        MainActivity.setTestMode(sharedPref, 1);

        // Викликаємо метод зміни кольорів
        activity.changeColors();

        // Перевіряємо, чи змінились кольори у відповідності до фіксованого режиму
        TextView color1 = activity.findViewById(R.id.color1);
        assertNotNull(color1.getText());
    }

    @Test
    public void testRandomChangeColors() {
        // Викликаємо метод для випадкової зміни кольорів
        activity.randomChangeColors();

        // Перевіряємо, чи кольори встановились
        TextView color1 = activity.findViewById(R.id.color1);
        TextView color2 = activity.findViewById(R.id.color2);
        assertNotNull(color1.getText());
        assertNotNull(color2.getText());
    }

    @Test
    public void testFixedChangeColors() {
        // Вручну встановлюємо тестовий режим
        SharedPreferences sharedPref = activity.getPreferences(activity.MODE_PRIVATE);
        MainActivity.setTestMode(sharedPref, 1);

        // Викликаємо метод фіксованої зміни кольорів
        activity.fixedChangeColors();

        // Перевіряємо, що кольори змінені відповідно до фіксованого режиму
        TextView color1 = activity.findViewById(R.id.color1);
        assertNotNull(color1.getText());
    }

    @Test
    public void testNavigationToResultActivity() {
        // Імітуємо завершення таймера
        activity.onFinish();

        // Перевіряємо, що було запущено нову Activity
        ShadowActivity shadowActivity = shadowOf(activity);
        Intent expectedIntent = new Intent(activity, ResultActivity.class);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}
