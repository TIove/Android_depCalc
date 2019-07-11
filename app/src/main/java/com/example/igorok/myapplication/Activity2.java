package com.example.igorok.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        String a;
        int b;
        EditText DateSt = (EditText) findViewById(R.id.editText13);
        EditText AmountOst = (EditText) findViewById(R.id.editText10);

    }

    public void TaddRow(int c0, int c1, int c2, int c3, int c4, int c5) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        //Создаём экземпляр инфлейтера, который понадобится для создания строки таблицы из шаблона. В качестве контекста у нас используется сама активити
        LayoutInflater inflater = LayoutInflater.from(this);
        //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        //Находим ячейку для номера дня по идентификатору
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        //Обязательно приводим число к строке, иначе оно будет воспринято как идентификатор ресурса
        tv.setText(Integer.toString(c0));
        //Ищем следующую ячейку и устанавливаем её значение
        tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(Integer.toString(c1));
        //...и так далее для всех значений
        tableLayout.addView(tr); //добавляем созданную строку в таблицу
    }

    public void addRow(String c0, int c1, int c2) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);

        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(c0);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(Integer.toString(c1));
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(Integer.toString(c2));
        tableLayout.addView(tr);
    }
}
