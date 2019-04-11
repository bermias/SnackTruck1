package com.ermias.snacktruck;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
       ArrayList<String> selectedItems=new ArrayList<>();
       CheckedTextView checkedTextView;
    ArrayList<SnackClass> snacks=new ArrayList<>();
    SnackViewAdapter snackViewAdapter;
    ListView listView1;
    public  boolean isVeg=true, isNonVeg=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CheckBox veggieChBox=(CheckBox) findViewById(R.id.veggie_check_box);
        final CheckBox nonVeggieChBox=(CheckBox) findViewById(R.id.non_veggie_check_box);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        checkedTextView=(CheckedTextView) findViewById(R.id.check_text);
        Toolbar toolbar=(Toolbar) findViewById(R.id.tool_bar);
        SnackClass snackClass=new SnackClass("French fries ","Veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Milkshake ","Veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Veggieburger ","Veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Carrots","Veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Apples","Veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Banana","Veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Cheeseburger","Non-veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Hamburger","Non-veggie");
        snacks.add(snackClass);
        snackClass=new SnackClass("Hot dog","Non-veggie");
        snacks.add(snackClass);

        listView1=(ListView) findViewById(R.id.list_view);
        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final ArrayAdapter adapter=new ArrayAdapter<SnackClass>(this,R.layout.check_list,
                R.id.check_text,snacks);
         snackViewAdapter =new SnackViewAdapter(this,snacks);

        listView1.setAdapter(snackViewAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=((TextView)view).getText().toString();

                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItem);
                }
                else{
                    selectedItems.add(selectedItem);
                }
            }
        });
        veggieChBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isVeg=veggieChBox.isChecked();
                    ArrayList<SnackClass> s = getFilterSnacks(snacks, isVeg,isNonVeg);
                    Context context = getApplicationContext();
                    snackViewAdapter = new SnackViewAdapter(context, s);

                    listView1.setAdapter(snackViewAdapter);
                    snackViewAdapter.notifyDataSetChanged();
                }
        });

        nonVeggieChBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNonVeg=nonVeggieChBox.isChecked();
                    ArrayList<SnackClass> s = getFilterSnacks(snacks,isVeg,isNonVeg);
                    Context context = getApplicationContext();
                    snackViewAdapter = new SnackViewAdapter(context, s);

                    listView1.setAdapter(snackViewAdapter);
                    snackViewAdapter.notifyDataSetChanged();
                }
        });
    }

    public void showSelectedItems(View view){
        String items="";
        for(String item:selectedItems){
            items+="-"+item+"\n";
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Selected Snack");
        alert.setMessage(items);//getMultiplicationResult(buttonText));
        alert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();

        CheckedTextView checkedTextView1;
        for(int i=0;i<listView1.getChildCount();i++){
            checkedTextView1=(CheckedTextView) listView1.getChildAt(i).findViewById(R.id.check_text);
            checkedTextView1.setChecked(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    private ArrayList<SnackClass> getFilterSnacks(ArrayList<SnackClass> sns,boolean v,boolean n){
        ArrayList<SnackClass> sn=new ArrayList<>();

        for(int i=0;i<sns.size();i++){
            if(sns.get(i).get_snackType().contains("Veggie") && v){
                sn.add(sns.get(i));
            }
            else if(sns.get(i).get_snackType().contains("Non-veggie") && n){
                sn.add(sns.get(i));
            }
        }

       return sn;
    }
}
