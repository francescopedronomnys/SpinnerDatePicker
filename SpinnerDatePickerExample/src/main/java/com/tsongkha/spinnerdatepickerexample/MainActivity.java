package com.tsongkha.spinnerdatepickerexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.OnDateChangedListener;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by rawsond on 25/08/17.
 */

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DatePickerDialog.OnDateCancelListener, OnDateChangedListener {

    TextView dateTextView;
    Button dateButton;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateButton = (Button) findViewById(R.id.set_date_button);
        dateTextView = (TextView) findViewById(R.id.date_textview);
        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.US);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate(1980, 0, 1, R.style.DatePickerSpinner);
            }
        });


        FrameLayout frameLayout = findViewById(R.id.lay_picker);

        Calendar defaultDate = new GregorianCalendar(1980, 0, 1);
        Calendar minDate = new GregorianCalendar(1900, 0, 1);
        Calendar maxDate = new GregorianCalendar(2100, 0, 1);

        DatePicker picker = new DatePicker(frameLayout, R.style.DatePickerSpinner);
        picker.setMinDate(minDate.getTimeInMillis());
        picker.setMaxDate(maxDate.getTimeInMillis());
        picker.init(defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(Calendar.DAY_OF_MONTH), true, MainActivity.this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        dateTextView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onCancelled(DatePicker view) {
        dateTextView.setText(R.string.cancelled);
    }


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(MainActivity.this)
                .callback(MainActivity.this)
                .onCancel(MainActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }


    @Override public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        onDateSet(view, year, monthOfYear, dayOfMonth);
    }
}
