package com.example.ekram.imagemap4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import static com.example.ekram.imagemap4.R.layout.database_layout;


public class Mydatabase extends Activity implements RadioGroup.OnCheckedChangeListener {


    private static int id=1;
    private SimpleDBAdapter mDbHelper;
    private ListView list;
    private Button btnnext;
    private String[] values;

    private TextView hint;
    private EditText from,to;
    private String FROM,TOM;

    private RadioGroup category;
    private RadioButton subcategory1;
    private RadioButton subcategory2;
    private RadioButton subcategory3;
    private RadioButton subcategory4;


    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(database_layout);

        list=(ListView )findViewById(R.id.myListView);
        btnnext=(Button)findViewById(R.id.button);
        hint=(TextView)findViewById(R.id.hint);
        from=(EditText)findViewById(R.id.from);
        to=(EditText)findViewById(R.id.to);
        category=(RadioGroup) findViewById(R.id.SearchCat);
        mDbHelper = new SimpleDBAdapter(Mydatabase.this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        subcategory1=(RadioButton)findViewById(R.id.planner);
        subcategory2=(RadioButton)findViewById(R.id.bus);
        subcategory3=(RadioButton)findViewById(R.id.stop);
        subcategory4=(RadioButton)findViewById(R.id.bus_details);

        category.setOnCheckedChangeListener(this);

        FROM=from.getText().toString();
        TOM=to.getText().toString();
        mDbHelper = new SimpleDBAdapter(Mydatabase.this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        list.setVisibility(View.INVISIBLE);
        hint.setVisibility(View.INVISIBLE);


    }
public void add(View v){
    if(id==1) {
        FROM = from.getText().toString();
        TOM = to.getText().toString();
        values = mDbHelper.getEditTextValue(FROM,TOM);
        Toast.makeText(getApplicationContext(), FROM + " " + TOM, Toast.LENGTH_SHORT).show();
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
        hint.setText("Bus Name/Stop Name");
        hint.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);
    }
    if(id==2) {
        FROM = from.getText().toString();
        values = mDbHelper.getEditTextValueBus(FROM, "");
        Toast.makeText(getApplicationContext(), FROM + " " + TOM, Toast.LENGTH_SHORT).show();
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
        hint.setText("Stop Name");
        hint.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);
    }
    if(id==3) {
        FROM = from.getText().toString();
        values = mDbHelper.getEditTextValueStop(FROM, "");
        Toast.makeText(getApplicationContext(), FROM + " " + TOM, Toast.LENGTH_SHORT).show();
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
        hint.setText("Bus Name");
        hint.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);
    }
    if(id==4) {
        FROM = from.getText().toString();
        values = mDbHelper.getEditTextValueBusDetails(FROM, "");
        Toast.makeText(getApplicationContext(), FROM + " " + TOM, Toast.LENGTH_SHORT).show();
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
        hint.setText("Route ID/Start Point/End Point");
        hint.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);
    }
    View view = this.getCurrentFocus();
    if (view != null) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(subcategory1.isChecked()==true){
            to.setVisibility(View.VISIBLE);
            to.setHint("TO");
            from.setHint("FROM");
            from.setText("");
            to.setText("");
            id=1;
        }
        if(subcategory2.isChecked()==true){
            to.setVisibility(View.INVISIBLE);
            from.setHint("Enter Bus Name");
            from.setText("");
            to.setText("");
            id=2;
        }
        if(subcategory3.isChecked()==true){
            to.setVisibility(View.INVISIBLE);
            from.setHint("Enter Stop Name");
            from.setText("");
            to.setText("");
            id=3;
        }
        if(subcategory4.isChecked()==true){
            to.setVisibility(View.INVISIBLE);
            from.setHint("Enter Bus Name");
            from.setText("");
            to.setText("");
            id=4;
        }
    }

    }


