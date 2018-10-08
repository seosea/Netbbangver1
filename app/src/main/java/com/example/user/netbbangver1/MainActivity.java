package com.example.user.netbbangver1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Person person;
    public static Context context;
    public CashAdapter cashAdapter;
    ImageButton btnHome, btnMyProject, btnChatting, btnMyPage;
    LinearLayout linearMainMenu;

    public MainActivity(){
        context = this;
        person = new Person("admin", "1234", "admin", "admin", "010-0000-0000", "r");
        cashAdapter = new CashAdapter();

        cashAdapter.setGetAccountInterface(new CashAdapter.GetAccountInterface() {
            @Override
            public String getChangeAccount() {
                return person.getWho();
            }
        });

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTotal(int cash){
        this.person.setTotal(this.person.getTotal()+cash);
    }

    public int getTotal(){
        return this.person.getTotal();
    }

    public String getNickname(){
        return this.person.getNickname();
    }

    public void setChangeAccount(){
        if(person.getWho().equals("r")) person.setWho("w");
        else if(person.getWho().equals("w")) person.setWho("r");
    }

    public String getChangeAccount(){
        return this.person.getWho();
    }

    public CashAdapter getCashAdapter() {
        return cashAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.main_fragment, fragment );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        btnHome = (ImageButton)findViewById(R.id.btn_home);
        btnMyProject = (ImageButton)findViewById(R.id.btn_my_project);
        btnChatting = (ImageButton)findViewById(R.id.btn_chatting);
        btnMyPage = (ImageButton)findViewById(R.id.btn_my_page);

        linearMainMenu = (LinearLayout)findViewById(R.id.linear_main_menu);
    }

    public void mainMenuClick(View view){
        int id = view.getId();

        final int sdk = android.os.Build.VERSION.SDK_INT;

        switch(id){
            case R.id.btn_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new HomeFragment())
                        .addToBackStack(null)
                        .commit();


                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    btnHome.setBackgroundDrawable( getResources().getDrawable(R.drawable.main_meun_box) );
                } else {
                    btnHome.setBackground(ContextCompat.getDrawable(this, R.drawable.main_meun_box));
                }

                btnMyProject.setBackgroundResource(R.color.colorWhite);
                btnChatting.setBackgroundResource(R.color.colorWhite);
                btnMyPage.setBackgroundResource(R.color.colorWhite);

                break;
            case R.id.btn_my_project:

                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    btnMyProject.setBackgroundDrawable( getResources().getDrawable(R.drawable.main_meun_box) );
                } else {
                    btnMyProject.setBackground(ContextCompat.getDrawable(this, R.drawable.main_meun_box));
                }

                btnHome.setBackgroundResource(R.color.colorWhite);
                btnChatting.setBackgroundResource(R.color.colorWhite);
                btnMyPage.setBackgroundResource(R.color.colorWhite);

                break;
            case R.id.btn_chatting:

                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    btnChatting.setBackgroundDrawable( getResources().getDrawable(R.drawable.main_meun_box) );
                } else {
                    btnChatting.setBackground(ContextCompat.getDrawable(this, R.drawable.main_meun_box));
                }

                btnHome.setBackgroundResource(R.color.colorWhite);
                btnMyProject.setBackgroundResource(R.color.colorWhite);
                btnMyPage.setBackgroundResource(R.color.colorWhite);

                break;
            case R.id.btn_my_page:
            case R.id.btn_withdraw_confirm:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new MyPageFragment())
                        .addToBackStack(null)
                        .commit();

                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    btnMyPage.setBackgroundDrawable( getResources().getDrawable(R.drawable.main_meun_box) );
                } else {
                    btnMyPage.setBackground(ContextCompat.getDrawable(this, R.drawable.main_meun_box));
                }

                btnHome.setBackgroundResource(R.color.colorWhite);
                btnMyProject.setBackgroundResource(R.color.colorWhite);
                btnChatting.setBackgroundResource(R.color.colorWhite);
                break;
            case R.id.btn_mypage_payment:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new MyPagePaymentFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_mypage_withdraw:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new MyPageWithdrawFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_mypage_cash_list:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new  MyPageCashListFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_withdraw_cash:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new MyPageWithdrawConfirmFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
