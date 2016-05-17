package snru.somsit.chaichanat.snrurun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SingUp extends AppCompatActivity {

    //Explicit ประกาศตัวแปร
    private EditText nameEditText,userEditText,passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton, choice3RadioButton,
            choice4RadioButton, choice5RadioButton;
    private String nameString, userString, passwordString, avataString = "0";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //Blind Widget
        blindWidget();

        //Radio Button Con
        radioButtonController();

    }//main method

    private void radioButtonController() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override//ตรวจสอบเลือกรูป
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.radioButton:
                        avataString = "0";
                        break;
                    case R.id.radioButton2:
                        avataString = "1";
                        break;
                    case R.id.radioButton3:
                        avataString = "2";
                        break;
                    case R.id.radioButton4:
                        avataString = "3";
                        break;
                    case R.id.radioButton5:
                        avataString = "4";
                        break;
                }
            }
        });

    }

    private void blindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        radioGroup = (RadioGroup) findViewById(R.id.ragAvata);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        choice5RadioButton = (RadioButton) findViewById(R.id.radioButton5);


    }

    public void clickSignUpSign(View view) {
    //Get Value From EditText
        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("") || userString.equals("")  || passwordString.equals("") ) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง", "กรุณากรอกทุกช่อง");

        } else {

        }

    }
}//main Class
