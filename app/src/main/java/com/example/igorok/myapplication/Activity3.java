package com.example.igorok.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        int Date = getIntent().getExtras().getInt("Date1");
        int Month = getIntent().getExtras().getInt("Date2");
        int year = getIntent().getExtras().getInt("Date3");
        double Amount = (double) getIntent().getExtras().getInt("Am");
        int term = getIntent().getExtras().getInt("term");
        double perc = getIntent().getExtras().getDouble("perc");
        int Kap = getIntent().getExtras().getInt("Kap");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        double Endperc = 0;
        double TotInterests = 0;
        int DateMonth = 0;
        int EndTerm = term;
        int Flags = 0;



//Right Vers
        for (int i = 0; i < term / 30 - 1; i++) {
            switch (Month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    DateMonth = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    DateMonth = 30;
                    break;
                case 2:
                    if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)){
                        DateMonth = 29;
                    }
                    else
                        DateMonth = 28;
                    break;
            }
            EndTerm = EndTerm - DateMonth;
            DateMonth = DateMonth - Date;


            if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)){
                Endperc = (Amount *DateMonth*perc)/36600;
            }else{
                Endperc = (Amount *DateMonth*perc)/36500;
            }
            if (Month < 12) {
                Month++;
            } else {
                Month = 1;
                year++;
            }
            if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)){
                Endperc = Endperc + (Amount *Date*perc)/36600;
            }else{
                Endperc = Endperc + (Amount *Date*perc)/36500;
            }
            try {
                TotInterests = TotInterests + Math.rint(100.0 *  Endperc) / 100.0;
                if (Kap == 1) {
                    Amount = Amount + Endperc;
                }
                java.util.Date DD = dateFormat.parse(Date + "." + Month + "." + year);
                addRow(dateFormat.format(DD),Math.rint(100.0 *  Endperc) / 100.0,  Math.rint(100.0 *  Amount) / 100.0);
            } catch (Exception ignored) {
            }


        }


        if (EndTerm != 0) {
            switch (Month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    DateMonth = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    DateMonth = 30;
                    break;
                case 2:
                    if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)){
                        DateMonth = 29;
                    }
                    else
                        DateMonth = 28;
                    break;
            }
            Flags = DateMonth;
            if (DateMonth >= EndTerm+Date) {
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
                    Endperc = (Amount * EndTerm * perc) / 36600;
                } else {
                    Endperc = (Amount * EndTerm * perc) / 36500;
                }

                try {
                    TotInterests = TotInterests + Math.rint(100.0 * Endperc) / 100.0;
                    if (Kap == 1) {
                        Amount = Amount + Endperc;
                    }
                    java.util.Date DD = dateFormat.parse(EndTerm+Date + "." + Month + "." + year);
                    addRow(dateFormat.format(DD), Math.rint(100.0 * Endperc) / 100.0, Math.rint(100.0 * Amount) / 100.0);
                } catch (Exception ignored) {
                }
            }else{
                DateMonth = DateMonth - Date;
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)){
                    Endperc = (Amount *DateMonth*perc)/36600;
                }else{
                    Endperc = (Amount *DateMonth*perc)/36500;
                }
                if (Month < 12) {
                    Month++;
                } else {
                    Month = 1;
                    year++;
                }
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)){
                    Endperc = Endperc + (Amount *(EndTerm-DateMonth)*perc)/36600;
                }else{
                    Endperc = Endperc + (Amount *(EndTerm-DateMonth)*perc)/36500;
                }
                try {
                    TotInterests = TotInterests + Math.rint(100.0 *  Endperc) / 100.0;
                    if (Kap == 1) {
                        Amount = Amount + Endperc;
                    }
                    java.util.Date DD = dateFormat.parse(EndTerm - Flags + Date + "." + Month + "." + year);
                    addRow(dateFormat.format(DD),Math.rint(100.0 *  Endperc) / 100.0,  Math.rint(100.0 *  Amount) / 100.0);
                } catch (Exception ignored) {
                }
            }
        }

        addRow("Итого:", Math.rint(100.0 * TotInterests) /100.0,  Math.rint(100.0 *  Amount) / 100.0);


}

    @SuppressLint("SetTextI18n")
    public void addRow(String c0, double c1, double c2) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);

        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText((CharSequence) c0);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(Double.toString(c1));
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(Double.toString(c2));
        tableLayout.addView(tr);
    }
}
