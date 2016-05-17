package snru.somsit.chaichanat.snrurun;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private ImageView imageView;
    private EditText userEdiText, PasswordEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Blind Widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEdiText = (EditText) findViewById(R.id.editText4);
        PasswordEdiText = (EditText) findViewById(R.id.editText5);



        myManage = new MyManage(MainActivity.this);

        //Test add
        ///myManage.addUser("ชัยชนะ", "โสมสิทธื","12345", "4");

        deletaAllSQLite();

        MySynchronize mySynchronize = new MySynchronize();
        mySynchronize.execute();

        //Show logo
        Picasso.with(MainActivity.this)
                .load("http://swiftcodingthai.com/snru/image/logo_snru.png")
                .resize(200,500)
                .into(imageView);

    } //main Method

    //Creat inner Class
    public class MySynchronize extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/snru/get_user.php").build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();

            } catch (Exception e) {

            }
            return null;
        }//doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("Snru", "JSON==" + s);

            try {

                JSONArray jsonArray = new JSONArray();
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(myManage.column_name);
                    String strUser = jsonObject.getString(myManage.column_user);
                    String strPassword = jsonObject.getString(myManage.column_Password);
                    String strAvata = jsonObject.getString(myManage.column_avata);

                    myManage.addUser(strName, strUser, strPassword, strAvata);

                }//for

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//onPost


    }

    private void deletaAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
    }


    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SingUp.class));
    }

}  //main Class  หน้าหลัก
