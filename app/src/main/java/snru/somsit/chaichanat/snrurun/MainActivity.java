package snru.somsit.chaichanat.snrurun;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(MainActivity.this);

        //Test add
        ///myManage.addUser("ชัยชนะ", "โสมสิทธื","12345", "4");

        deletaAllSQLite();

        MySynchronize mySynchronize = new MySynchronize();
        mySynchronize.execute();

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
