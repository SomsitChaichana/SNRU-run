package snru.somsit.chaichanat.snrurun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;

/**
 * Created by Administrator on 17/5/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    //Explicit ทะลุกำแพง
    public static final String database_name = "snru.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE(" +
            "_id integer primary key," +
            "Name text," +
            "User text," +
            "Password text" +
            "Avata text);";



    public MyOpenHelper(Context context) {

        super(context,database_name,null,database_version);
    }   //Constructon

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}//Main Class
