package com.example.Lab3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ResultActivityTest {

    @Mock
    Context mockContext;
    @Mock
    Intent mockIntent;

    private ResultActivity resultActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resultActivity = new ResultActivity();

        // Створюємо мокований Intent
        mockIntent = mock(Intent.class);
        when(mockIntent.getStringExtra("result")).thenReturn("Test Result");

        // Встановлюємо мокований Intent в активності
        resultActivity.setIntent(mockIntent);
    }

    @Test
    public void testOnCreate() {
        resultActivity.onCreate(null);

        // Перевіряємо, що текст результату встановлений
        TextView resultTextView = resultActivity.findViewById(R.id.result);
        assertEquals("Test Result", resultTextView.getText().toString());
    }

    @Test
    public void testAddListenerOnButton() {
        resultActivity.onCreate(null); // Викликаємо onCreate для ініціалізації UI
        resultActivity.addListenerOnButton();

        // Перевіряємо, що кнопки не є null
        Button playButton = resultActivity.findViewById(R.id.play_btn);
        Button mainButton = resultActivity.findViewById(R.id.main_btn);
        Button exitButton = resultActivity.findViewById(R.id.exit_btn);

        assertNotNull(playButton);
        assertNotNull(mainButton);
        assertNotNull(exitButton);
    }
}
