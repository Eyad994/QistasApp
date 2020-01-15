package com.example.qistas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextInterface, AdapterView.OnItemSelectedListener {

    private List<String> firstList, secondList, thirdList;
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    String titles1[] = {"صلح حقوق4", "بداية جزاء1", "صلح حقوق1", "صلح حقوق3", "صلح حقوق2", "بداية جزاء5", "صلح حقوق6", "صلح حقوق7", "بداية جزاء8"};
    String titles2[] = {" مستأنف ضده", "مستأنف", "مدعي عليه", "مدعي", " مستأنف ضده", "مستأنف", "مدعي عليه", "مدعي"};
    String titles3[] = {"asd", "مستأنف مستأنف ضده", "مدعي عليه مستأنف ضده", "مدعي مستأنف ضده", " مستأنف ضده مستأنف ضده", "مستأنف", "مدعي عليه", "مدعي"};

    EditText search, calendarDialog;
    TextView textView, textView2, textView3, endDateCalculated;
    Spinner spinner;
    String first = "";
    String second = "";
    Button topRightBtn, downRightBtn, topLeftBtn, downLeftBtn, calcualteBtn;
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.app_bar_layout);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView2 = findViewById(R.id.recycler_view2);
        recyclerView3 = findViewById(R.id.recycler_view3);
        search = findViewById(R.id.search_edit_text);
        textView = findViewById(R.id.main_text);
        textView2 = findViewById(R.id.second_text);
        textView3 = findViewById(R.id.third_text);
        spinner = findViewById(R.id.spinner);
        topRightBtn = findViewById(R.id.top_right_btn);
        downRightBtn = findViewById(R.id.down_right_btn);
        topLeftBtn = findViewById(R.id.top_left_btn);
        downLeftBtn = findViewById(R.id.down_left_btn);
        calendarDialog = findViewById(R.id.calender_dialog);
        calcualteBtn = findViewById(R.id.calculate_btn);
        endDateCalculated = findViewById(R.id.end_date_calculated);

        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        thirdList = new ArrayList<>();

        addFirstList();
        addSecondList();
        addThirdList();


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        calcualteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (calcualteBtn.isEnabled())
                {
                    Date date = myCalendar.getTime();
                    endDateCalculated.setText(addDays(date, 10));
                } else {
                    Toast.makeText(MainActivity.this, "يجب تعبئة تاريخ الحكم", Toast.LENGTH_SHORT).show();
                }

            }
        });


         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        calendarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void filter(String text) {
        ArrayList<String> searchItems = new ArrayList<>();
        for (int i=0; i< titles3.length; i++)
        {
            if (thirdList.get(i).contains(text.toLowerCase()))
            {
                searchItems.add(thirdList.get(i));
            }
        }

        ThirdAdapter adapter = new ThirdAdapter(getApplicationContext(),searchItems, this);
        adapter.filterList(searchItems);
        recyclerView3.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


    public void addFirstList() {

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < titles1.length; i++) {
            firstList.add(titles1[i]);
        }


        final FirstAdapter adapter = new FirstAdapter(getApplicationContext(), (ArrayList<String>) firstList, this);
        recyclerView.setAdapter(adapter);

       topRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                    layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                }
            }
        });

         topLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager.scrollToPosition(layoutManager.findFirstCompletelyVisibleItemPosition() - 1);
            }
        });

    }

    public void addSecondList() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < titles2.length; i++) {
            secondList.add(titles2[i]);
        }

        final SecondAdapter adapter = new SecondAdapter(getApplicationContext(), (ArrayList<String>) secondList, this);
        recyclerView2.setAdapter(adapter);


        downRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                    layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                }
            }
        });

        downLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager.scrollToPosition(layoutManager.findFirstCompletelyVisibleItemPosition() - 1);
            }
        });
    }

    public void addThirdList() {
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < titles3.length; i++) {
            thirdList.add(titles3[i]);
        }

        ThirdAdapter adapter = new ThirdAdapter(getApplicationContext(), (ArrayList<String>) thirdList, this);
        recyclerView3.setAdapter(adapter);
    }


    @Override
    public void setFirstText(String firstText) {
        textView.setText(firstText + "/ ");
        this.first = firstText;
    }

    @Override
    public void setSecondText(String secondText) {
        textView2.setText(secondText);
        this.second = secondText;
    }

    @Override
    public void setThirdText(String thirdText) {
        textView3.setText(first + "/" + second + "/" + thirdText);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Log.d("xxxx", "onItemSelected: "+ item);
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        calendarDialog.setText(sdf.format(myCalendar.getTime()));
        calcualteBtn.setEnabled(true);

    }

    public static String addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        Log.d("TEST", "BEFORE CHECKING: " + cal.getTime().toString());
        // SATURDAY is the last day of week so add 2 days
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            cal.add(Calendar.DATE, 2);
            // SUNDAY is the first day of week so add 1 day
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DATE, 1);
        } // else not required as it means its one of the week day
        Log.d("TEST", "AFTER UPDATING: " + cal.getTime().toString());

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Log.d("Finally", "addDays: "+ sdf.format(cal.getTime()));

        return sdf.format(cal.getTime());
    }
}
