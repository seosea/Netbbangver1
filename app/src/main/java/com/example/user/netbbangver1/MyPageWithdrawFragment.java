package com.example.user.netbbangver1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MyPageWithdrawFragment extends Fragment{
    ImageButton btnBack;
    DecimalFormat myFormatter = new DecimalFormat("###,###");
    EditText editCash;
    Button btnWithdraw;
    TextView txtTotal,txtWithdrawable,txtInputKoreans;
    LinearLayout linearLayoutCash;
    RelativeLayout relativeLayoutTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_my_page_withdraw, container, false );

        linearLayoutCash = (LinearLayout)view.findViewById(R.id.linear_cash_withdraw);
        relativeLayoutTitle = (RelativeLayout)view.findViewById(R.id.relative_title_withdraw);
        btnWithdraw = (Button)view.findViewById(R.id.btn_withdraw_cash);
        btnBack = (ImageButton)view.findViewById(R.id.btn_back_from_withdraw);
        txtInputKoreans = (TextView)view.findViewById(R.id.txt_input_number_for_koreans);

        if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
            linearLayoutCash.setBackgroundResource(R.color.colorGreen);
            relativeLayoutTitle.setBackgroundResource(R.color.colorGreen);
            btnWithdraw.setBackgroundResource(R.color.colorGreen);
            btnBack.setBackgroundResource(R.color.colorGreen);
        }
        else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
            linearLayoutCash.setBackgroundResource(R.color.colorNavy);
            relativeLayoutTitle.setBackgroundResource(R.color.colorNavy);
            btnWithdraw.setBackgroundResource(R.color.colorNavy);
            btnBack.setBackgroundResource(R.color.colorNavy);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MyPageWithdrawFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        editCash = (EditText)view.findViewById(R.id.edit_txt_withdraw_cash);
        editCash.addTextChangedListener(new NumberTextWatcher(editCash));
        editCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int a, int i1, int i2) {
                String str = new String();
                for (int i = 0; i < editCash.getText().toString().length(); i++) {
                    if (48 <= editCash.getText().toString().charAt(i) && editCash.getText().toString().charAt(i) <= 57)
                        str += editCash.getText().toString().charAt(i);
                }
                txtInputKoreans.setText(convertHangul(str));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editCash.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocus) {
                if(isFocus) ((MainActivity)getActivity()).linearMainMenu.setVisibility(View.GONE);
                else ((MainActivity)getActivity()).linearMainMenu.setVisibility(View.VISIBLE);
            }
        });
        editCash.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ((MainActivity)getActivity()).linearMainMenu.setVisibility(View.VISIBLE);
                } else if (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    // KeyCODE Back 들어왔을시
                    ((MainActivity)getActivity()).linearMainMenu.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });

        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEdit = editCash.getText().toString();
                getEdit = getEdit.trim();

                if(getEdit.getBytes().length <= 0){
                    Toast.makeText(getActivity(),"금액을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    String str = new String();
                    for (int i = 0; i < editCash.getText().toString().length(); i++) {
                        if (48 <= editCash.getText().toString().charAt(i) && editCash.getText().toString().charAt(i) <= 57)
                            str += editCash.getText().toString().charAt(i);
                    }

                    ((MainActivity) getActivity()).getPerson().getCash()
                            .add(new Cash("출금",
                                    -Integer.parseInt(str),
                                    ((MainActivity) getActivity()).getPerson().getTotal() - Integer.parseInt(str)));
                    ((MainActivity) getActivity()).setTotal(-Integer.parseInt(str));
                    ((MainActivity) getActivity()).mainMenuClick(view);
                }
            }
        });

        txtTotal = (TextView)view.findViewById(R.id.txt_total_withdraw);
        txtTotal.setText(myFormatter.format(((MainActivity)getActivity()).getTotal()));
        txtWithdrawable = (TextView)view.findViewById(R.id.txt_withdrawable_withdraw);
        txtWithdrawable.setText(myFormatter.format(((MainActivity)getActivity()).getTotal()));

        return view;
    }

    public String convertHangul(String money){
        String[] han1 = {"","일","이","삼","사","오","육","칠","팔","구"};
        String[] han2 = {"","십","백","천"};
        String[] han3 = {"","만","억","조","경"};

        StringBuffer result = new StringBuffer();
        int len = money.length();

        for(int i=len-1; i>=0; i--){
            result.append(han1[Integer.parseInt(money.substring(len-i-1, len-i))]);
            if(Integer.parseInt(money.substring(len-i-1, len-i)) > 0) result.append(han2[i%4]);
            if(i%4 == 0) result.append(han3[i/4]);
        }

        return result.toString();

    }

}
