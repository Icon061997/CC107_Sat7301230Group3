package com.example.voterslimit;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnDate, btnCheck;
    EditText Fname, Lname;
    TextView text_print, text_date, tvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.btnCheck);


        Fname = findViewById(R.id.eFname);
        Lname = findViewById(R.id.eLname);
        text_print = findViewById(R.id.text_print);
        text_date = findViewById(R.id.text_date);
        btnDate = findViewById(R.id.btnDate);
        tvAge = findViewById(R.id.tvAge);
        btnCheck = findViewById(R.id.btnCheck);

        btnDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int sYear = calendar.get(Calendar.YEAR);
                int sMonth = calendar.get(Calendar.MONTH);
                int sDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog;
                dateDialog = new DatePickerDialog(view.getContext(), datePickerListener, sYear, sMonth, sDay);
                dateDialog.show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd MM YYYY").format(c.getTime());
            text_date.setText(format);
            tvAge.setText(Integer.toString(calCulateAge(c.getTimeInMillis()))) ;

        }
    };

    int calCulateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String First = Fname.getText().toString();
                String Last = Lname.getText().toString();
                double print = Double.parseDouble(tvAge.getText().toString());

                if (print <= 17) {
                    text_print.setText(First  +" "+  Last  + " Sorry! voters must be 18 above, you are not eligible to vote for the election.");
                }
                if (print >= 18) {
                    text_print.setText(First  +" "+  Last +  "  you have the right to vote for the election.");
                }
            }
        });
        return age;
    }

}