package com.kosmo.a25listview02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO61";

    //리스트뷰의 데이터로 사용할 문자열배열 및 정수형배열
    String[] idolGroup = { "엑소", "방탄소년단", "워너원", "뉴이스트", "갓세븐", "비투비", "빅스" };
    int[] teamCount = { 9, 7, 11, 5, 7, 7, 6 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView)findViewById(R.id.listView1);

//        ArrayAdapter<String> adapter1;
//        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, idolGroup);
//        listView1.setAdapter(adapter1);

        //2단계
        final MyAdapter adapter = new MyAdapter();
        listView1.setAdapter(adapter);

        //4단계
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "선택한 항목 : " + idolGroup[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    개발자 정의 커스텀 어댑터 객체 선언
        : BaseAdapter를 상속받아서 선언한다. 그리고 관련 메소드를 오버라이딩 해야 한다.
     */
    class MyAdapter extends BaseAdapter {

        //어댑터 객체가 가진 항목의 갯수를 반환함.
        @Override
        public int getCount() {
            return idolGroup.length;
        }

        //어댑터객체가 가진 항목중에 하나를 반환함
        @Override
        public Object getItem(int position) {
            return idolGroup[position];
        }

        //항목의 인덱스값 반환
        @Override
        public long getItemId(int position) {
            return position;
        }

        //어댑터가 가진 뷰중에 하나를 반환
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //JAVA코드를 통해서 레이아웃 및 위젯생성

            //텍스트뷰1생성 : 아이돌 그룹명을 출력한다.
            TextView view1 = new TextView(getApplicationContext());
            view1.setText(idolGroup[position]); // 텍스트입력
            view1.setTextSize(40.0f); // 크기설정
            view1.setTextColor(Color.BLUE); // 컬러설정

            //텍스트뷰2생성 : 멤버의 인원수 출력
            TextView view2 = new TextView(getApplicationContext());

            /*
            setText()는 기본자료형을 삽입할 수 없기때문에 정수타입의 변수가
            설정되면 앱 실행 시 예외가 발생된다. 따라서 반드시 Wrapper 클래스를
            통해 Boxing 처리 후 입력되어야 한다.
             */
            //view2.setText(teamCount[position]);
            view2.setText(new Integer(teamCount[position]).toString() + "명");

            view2.setTextSize(40.0f);
            view2.setTextColor(Color.RED);

            //리니어레이아웃 생성 및 방향설정
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            //레이아웃에 텍스트뷰를 추가함.
            layout.addView(view1);
            layout.addView(view2);

            //레이아웃을 반환함.
            return layout;
        }
    }

}




























