package com.example.bttuan9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    ArrayList<ArrBean> arr_bean;
    Vector<Integer> index=new Vector<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Võ Văn Thắng - 1811505310142");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Listview
        listView = (ListView) findViewById(R.id.lv);
        arr_bean = new ArrayList<ArrBean>();

        arr_bean.add(new ArrBean(R.drawable.vietnam, "Android","Việt Nam là 1 quốc gia hùng mạnh"));
        arr_bean.add(new ArrBean(R.drawable.duc, "Android","Đây là nước Đức"));
        arr_bean.add(new ArrBean(R.drawable.phap, "Android","Đây là nước Pháp"));
        arr_bean.add(new ArrBean(R.drawable.usa, "Android","Đây là nước Mỹ"));
        arr_bean.add(new ArrBean(R.drawable.italya, "Android","Đây là nước Italya"));
        arr_bean.add(new ArrBean(R.drawable.england, "Android","Đây là nước England"));
        adapter=new Adapter(arr_bean,this);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        index.add(-1);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                try {
                    if (index.get(0) == -1)
                        index.set(0, position);
                    else {
                        int yn = 0, vt = -1;
                        for (int i = 0; i < index.size(); ++i) {
                            if (index.get(i) == position) {
                                yn = 1;
                                vt = i;
                                break;
                            }
                        }
                        if (yn == 1) index.remove(vt);
                        else index.add(position);
                    }
                }
                catch (Exception e){
                    index.add(position);
                }

                for (int i = 0; i < listView.getChildCount(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }

                for (int i = 0; i < listView.getChildCount(); i++) {
                    for(int j=0;j<index.size();++j)
                        if(index.get(j) == i){
                            listView.getChildAt(i).setBackgroundColor(Color.GREEN);
                        }
                }
                mode.setTitle(index.size()+" đã chọn");

            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        index.clear();
                        for (int i = 0; i < listView.getChildCount(); i++) {
                            listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        return true;
                    case R.id.them:
                        Toast.makeText(MainActivity.this,"Thêm",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sua:
                        Toast.makeText(MainActivity.this,"Sửa",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.xoa:
                        try {
                            Collections.sort(index);
                            for (int i = index.size()-1; i >=0 ; --i) {
                                arr_bean.remove(Integer.parseInt(index.get(i).toString()));
                                adapter.notifyDataSetChanged();
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this,"Bạn chưa chọn dòng nào cả",Toast.LENGTH_SHORT).show();
                        }

                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < listView.getChildCount(); i++) {
                            listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        Log.d("Xóa Mảng",Integer.toString(index.size()));
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_item, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
    }
}