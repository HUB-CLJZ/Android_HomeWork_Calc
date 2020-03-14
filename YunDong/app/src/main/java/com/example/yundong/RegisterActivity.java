package com.example.yundong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {
    private Button mBtnreset,mBtnreg;
    private EditText mEdtname,mEdtpasswd,mEdtpasswdagain;
    private EditText mEdtemail,mEdtphone;
    private CheckBox mChkrun,mChkswim,mChkbicycl,mChkhill;
    private RadioButton mRdmale,mRdfemale;
    private ImageView mImvhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //对象关联
        mBtnreg = findViewById(R.id.button_reg);
        mBtnreset =  findViewById(R.id.button_reset);
        mEdtname = findViewById(R.id.editTextName);
        mEdtpasswd = findViewById(R.id.editTextPasswd);
        mEdtpasswdagain = findViewById(R.id.editTextPasswdagain);
        mEdtphone = findViewById(R.id.editTextPhone);
        mEdtemail = findViewById(R.id.editTextEmail);
        mRdmale = findViewById(R.id.radioButton_male);
        mRdfemale = findViewById(R.id.radioButton_femail);
        mChkrun = findViewById(R.id.checkBox_run);
        mChkswim = findViewById(R.id.checkBox_swim);
        mChkbicycl = findViewById(R.id.checkBox_bike);
        mChkhill = findViewById(R.id.checkBox_hill);
        mImvhead = findViewById(R.id.imageView_Head);

        mImvhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击头像图片，进入选择头像页面
                Intent intent = new Intent("com.example.yundong.SHOW_Headpic_Activity");
                startActivityForResult(intent,101);
            }
        });
        //监听事件
        mBtnreset.setOnClickListener(new View.OnClickListener() {//清空内容
            @Override
            public void onClick(View view) {
                AlertDialog dlg = new AlertDialog.Builder(RegisterActivity.this).create();
                dlg.setTitle("提示");
                dlg.setMessage("确定清空信息？");
                dlg.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mEdtpasswd.setText("");
                    }
                });
                dlg.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dlg.show();

                mEdtname.setText("");
                mEdtpasswd.setText("");
                mEdtpasswdagain.setText("");
                mChkrun.setChecked(false);
                mRdmale.setChecked(false);
                mRdfemale.setChecked(false);
            }
        });
        mBtnreg.setOnClickListener(new View.OnClickListener() {//提交注册
            @Override
            public void onClick(View view) {
                //注册过程，检查用户名、密码等操作


                //如果注册成功，跳转到登录页面
                Intent intent = new Intent("com.example.yundong.SHOW_Login_Activity");
                intent.putExtra("name",mEdtname.getText().toString());
                startActivity(intent);
                finish();
        }
        });

        this.registerForContextMenu(findViewById(R.id.layout_linearroot));//在注册按钮上注册上下文菜单
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(101==requestCode && 102==resultCode){
            mImvhead.setImageResource(data.getIntExtra("imagesel",R.drawable.headpic_default));
        }else if(101==requestCode && 103==resultCode){
            Bitmap bitmap = AppConfig.getImage(this,data.getStringExtra("imagesel"));
            mImvhead.setImageBitmap(bitmap);
        }
    }

    //上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0,0,0,"兴趣全选");
        menu.add(0,1,1,"密码明文显示");
        menu.add(0,2,2,"密码密文显示");
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    //上下文菜单监听事件方法
    @Override
    public boolean onContextItemSelected( MenuItem item){
        switch(item.getItemId()){
            case 0:
                mChkhill.setChecked(true);
                mChkbicycl.setChecked(true);
                mChkswim.setChecked(true);
                mChkrun.setChecked(true);
                break;
            case 1:
                mEdtpasswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                mEdtpasswdagain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case 2:
                mEdtpasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEdtpasswdagain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
        return super.onContextItemSelected(item);
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu( Menu menu ){
        getMenuInflater().inflate(R.menu.menu_common,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //选项菜单监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item ){

        switch (item.getItemId()){
            case R.id.menu_back:
                finish();
                break;
            case R.id.menu_backtomain:
                Intent intent2 = new Intent("com.example.yundong.SHOW_Main_Activity");
                startActivity(intent2);
                finish();
                break;
            case R.id.menu_exit:
                finish();
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
