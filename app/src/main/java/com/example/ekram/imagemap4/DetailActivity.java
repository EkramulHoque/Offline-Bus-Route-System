package com.example.ekram.imagemap4;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ExpandableListView;
import android.widget.Toast;

/**
 * Created by ekram on 7/29/2015.
 */
public class DetailActivity extends Activity implements AdapterView.OnItemClickListener {
    String[] member_names_ShishuMela;
    String[] destination_ShishuMela;
    String[] bus1_ShishuMela;
    String[] bus2_ShishuMela;
    String[] bus3_ShishuMela;
    TypedArray profile_pics_ShishuMela;

    String[] member_names_Farmgate;
    String[] destination_Farmgate;
    String[] bus1_Farmgate;
    String[] bus2_Farmgate;
    String[] bus3_Farmgate;
    TypedArray profile_pics_Farmgate;

    String[] member_names_Mohakhali;
    String[] destination_Mohakhali;
    String[] bus1_Mohakhali;
    String[] bus2_Mohakhali;
    String[] bus3_Mohakhali;
    TypedArray profile_pics_Mohakhali;

    Float []x;
    Float []y;


    CustomAdapter adapter;

    List<RowItem> rowItems;


    ListView mylistview;
    EditText editsearch;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        rowItems = new ArrayList<RowItem>();

        x=new Float[]{50f,30f,80f,102f,600f};
        y=new Float[]{40.2f,50.4f,90.2f,502.4f,700.5f};


        member_names_ShishuMela = getResources().getStringArray(R.array.Bus_Number_ShishuMela);
        profile_pics_ShishuMela = getResources().obtainTypedArray(R.array.profile_pics_ShishuMela);
        destination_ShishuMela=getResources().getStringArray(R.array.Destination_ShishuMela);
        bus1_ShishuMela=getResources().getStringArray(R.array.bus1_ShishuMela);
        bus2_ShishuMela=getResources().getStringArray(R.array.bus2_ShishuMela);
        bus3_ShishuMela=getResources().getStringArray(R.array.bus3_ShishuMela);

        member_names_Farmgate = getResources().getStringArray(R.array.Bus_Number_Farmgate);
        profile_pics_Farmgate = getResources().obtainTypedArray(R.array.profile_pics_Farmgate);
        destination_Farmgate=getResources().getStringArray(R.array.Destination_Farmgate);
        bus1_Farmgate=getResources().getStringArray(R.array.bus1_Farmgate);
        bus2_Farmgate=getResources().getStringArray(R.array.bus2_Farmgate);
        bus3_Farmgate=getResources().getStringArray(R.array.bus3_Farmgate);

        member_names_Mohakhali = getResources().getStringArray(R.array.Bus_Number_Mohakhali);
        profile_pics_Mohakhali = getResources().obtainTypedArray(R.array.profile_pics_Mohakhali);
        destination_Mohakhali=getResources().getStringArray(R.array.Destination_Mohakhali);
        bus1_Mohakhali=getResources().getStringArray(R.array.bus1_Mohakhali);
        bus2_Mohakhali=getResources().getStringArray(R.array.bus2_Mohakhali);
        bus3_Mohakhali=getResources().getStringArray(R.array.bus3_Mohakhali);




        editsearch = (EditText) findViewById(R.id.editText);

        for (int i = 0; i < member_names_ShishuMela.length; i++) {
            Log.v("Inside Shishumela", "shishumela");
            RowItem item = new RowItem(member_names_ShishuMela[i],
                    profile_pics_ShishuMela.getResourceId(i, -1),destination_ShishuMela[i]
                    ,bus1_ShishuMela[i],bus2_ShishuMela[i],bus3_ShishuMela[i]);
            item.setX(x[i]);
            item.setX(y[i]);

            rowItems.add(item);
        }
        for (int i = 0; i < member_names_Farmgate.length; i++) {
            Log.v("Inside Farmgate", "farmgate");
            RowItem item = new RowItem(member_names_Farmgate[i],
                    profile_pics_Farmgate.getResourceId(i, -1),destination_Farmgate[i]
                    ,bus1_Farmgate[i],bus2_Farmgate[i],bus3_Farmgate[i]);
            rowItems.add(item);
            item.setX(x[i]);
            item.setX(y[i]);
        }
        for (int i =0; i < member_names_Mohakhali.length; i++) {
            Log.v("Inside mohakhali", "mohakhali");
            RowItem item = new RowItem(member_names_Mohakhali[i],
                    profile_pics_Mohakhali.getResourceId(i, -1),destination_Mohakhali[i]
                    ,bus1_Mohakhali[i],bus2_Mohakhali[i],bus3_Mohakhali[i]);
            rowItems.add(item);
            item.setX(x[i]);
            item.setX(y[i]);
        }

        mylistview = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);
        profile_pics_ShishuMela.recycle();
        profile_pics_Farmgate.recycle();
        profile_pics_Mohakhali.recycle();
        mylistview.setOnItemClickListener(this);
       editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String member_name = rowItems.get(position).getMember_name();
        Toast.makeText(getApplicationContext(), "" + member_name,
                Toast.LENGTH_SHORT).show();

        Float xfound=rowItems.get(position).getX();
        Float yfound=rowItems.get(position).getY();

        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        intent.putExtra("X", xfound);
        intent.putExtra("Y", yfound);
        startActivity(intent);


    }

}

