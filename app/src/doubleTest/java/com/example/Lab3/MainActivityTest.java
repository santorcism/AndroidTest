package com.example.Lab3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivity = new MainActivity();

        // Налаштуйте мок для SharedPreferences
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockSharedPreferences.getInt(anyString(), anyInt())).thenReturn(1);

        // Замініть реальні SharedPreferences на моки
        mainActivity.setSharedPreferences(mockSharedPreferences);
    }

    @Test
    public void testArray2dict() {
        String[] testTags = {"Red:16711680", "Green:65280", "Blue:255"};
        Map<String, Integer> result = MainActivity.array2dict(testTags);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(16711680), result.get("Red"));
        assertEquals(Integer.valueOf(65280), result.get("Green"));
        assertEquals(Integer.valueOf(255), result.get("Blue"));
    }

    @Test
    public void testSetTestMode() {
        int testModeStatus = 1;
        MainActivity.setTestMode(mockSharedPreferences, testModeStatus);

        // Перевірте, чи було викликано метод putInt з правильними параметрами
        verify(mockEditor).putInt("testing_mode", testModeStatus);
        verify(mockEditor).apply(); // Перевірте, чи був викликаний apply()
    }

    @Test
    public void testRandomChangeColors() {
        // Установка необхідних даних для тесту
        mainActivity.keyList = new ArrayList<>(List.of("Red", "Green", "Blue"));
        mainActivity.values = new Integer[]{16711680, 65280, 255}; // кольори в ARGB
        mainActivity.size = mainActivity.keyList.size();

        // Встановіть для color1 та color2 текстовий вигляд, щоб не було NullPointerException
        mainActivity.color1 = mock(TextView.class);
        mainActivity.color2 = mock(TextView.class);

        // Викличте метод randomChangeColors
        mainActivity.randomChangeColors();

        // Перевірте, чи були викликані методи setText та setTextColor
        verify(mainActivity.color1).setText(anyString());
        verify(mainActivity.color1).setTextColor(anyInt());
        verify(mainActivity.color2).setText(anyString());
        verify(mainActivity.color2).setTextColor(anyInt());
    }
}
