package com.example.user.netbbangver1;

import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MyPagePaymentFragment extends Fragment implements View.OnClickListener{
    ImageButton btnBack;
    DecimalFormat myFormatter = new DecimalFormat("###,###");
    EditText editCash;
    Button btnPayment;
    TextView txtTotal;
    Button btnHundredThousand, btnFiftyThousand, btnTenThousand;
    LinearLayout linearLayoutCash;
    RelativeLayout relativeLayoutTitle;
    private Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_my_page_payment, container, false );

        linearLayoutCash = (LinearLayout)view.findViewById(R.id.linear_cash_payment);
        relativeLayoutTitle = (RelativeLayout)view.findViewById(R.id.relative_title_payment);
        btnPayment = (Button)view.findViewById(R.id.btn_payment_cash);
        btnBack = (ImageButton)view.findViewById(R.id.btn_back_from_payment);

        if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
            linearLayoutCash.setBackgroundResource(R.color.colorGreen);
            relativeLayoutTitle.setBackgroundResource(R.color.colorGreen);
            btnPayment.setBackgroundResource(R.color.colorGreen);
            btnBack.setBackgroundResource(R.color.colorGreen);
        }
        else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
            linearLayoutCash.setBackgroundResource(R.color.colorNavy);
            relativeLayoutTitle.setBackgroundResource(R.color.colorNavy);
            btnPayment.setBackgroundResource(R.color.colorNavy);
            btnBack.setBackgroundResource(R.color.colorNavy);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MyPagePaymentFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        editCash = (EditText)view.findViewById(R.id.edit_txt_payment_cash);
        editCash.addTextChangedListener(new NumberTextWatcher(editCash));
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

        btnPayment.setOnClickListener(this);

        txtTotal = (TextView)view.findViewById(R.id.txt_total_payment);
        txtTotal.setText(myFormatter.format(((MainActivity)getActivity()).getTotal()));


        btnHundredThousand = (Button)view.findViewById(R.id.btn_hundred_thousand);
        btnHundredThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEdit = editCash.getText().toString();
                getEdit = getEdit.trim();

                if(getEdit.getBytes().length <= 0){
                    editCash.setText(String.valueOf(100000));
                }

                else {
                    String str = new String();
                    for (int i = 0; i < editCash.getText().toString().length(); i++) {
                        if (48 <= editCash.getText().toString().charAt(i) && editCash.getText().toString().charAt(i) <= 57)
                            str += editCash.getText().toString().charAt(i);
                    }
                    editCash.setText(String.valueOf(Integer.parseInt(str) + 100000));
                }
            }
        });
        btnFiftyThousand = (Button)view.findViewById(R.id.btn_fifty_thousand);
        btnFiftyThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEdit = editCash.getText().toString();
                getEdit = getEdit.trim();

                if(getEdit.getBytes().length <= 0){
                    editCash.setText(String.valueOf(50000));
                }
                else {
                    String str = new String();
                    for (int i = 0; i < editCash.getText().toString().length(); i++) {
                        if (48 <= editCash.getText().toString().charAt(i) && editCash.getText().toString().charAt(i) <= 57)
                            str += editCash.getText().toString().charAt(i);
                    }
                    editCash.setText(String.valueOf(Integer.parseInt(str) + 50000));
                }

            }
        });
        btnTenThousand = (Button)view.findViewById(R.id.btn_ten_thousand);
        btnTenThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEdit = editCash.getText().toString();
                getEdit = getEdit.trim();

                if(getEdit.getBytes().length <= 0){
                    editCash.setText(String.valueOf(10000));
                }
                else {
                    String str = new String();
                    for (int i = 0; i < editCash.getText().toString().length(); i++) {
                        if (48 <= editCash.getText().toString().charAt(i) && editCash.getText().toString().charAt(i) <= 57)
                            str += editCash.getText().toString().charAt(i);
                    }
                    editCash.setText(String.valueOf(Integer.parseInt(str) + 10000));
                }

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_payment_cash){
            String getEdit = editCash.getText().toString();
            getEdit = getEdit.trim();

            if(getEdit.getBytes().length <= 0){
                Toast.makeText(getActivity(),"금액을 입력해주세요.",Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                View customLayout=View.inflate(getActivity(),R.layout.dialog_payment_confirm,null);
                builder.setView(customLayout);
                TextView txtCash = customLayout.findViewById(R.id.txt_cash_confirm_dialog);
                txtCash.setText(editCash.getText().toString());
                customLayout.findViewById(R.id.btn_cancel_confrim_dialog).setOnClickListener(this);
                customLayout.findViewById(R.id.btn_payment_confirm_dialog).setOnClickListener(this);

                if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
                    customLayout.findViewById(R.id.btn_payment_confirm_dialog).setBackgroundResource(R.color.colorGreen);
                }

                else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
                    customLayout.findViewById(R.id.btn_payment_confirm_dialog).setBackgroundResource(R.color.colorNavy);
                }


                dialog=builder.create();
                dialog.show();

            }
        }
        else if(id == R.id.btn_cancel_confrim_dialog){
            dialog.dismiss();
        }else if(id == R.id.btn_payment_confirm_dialog){
            dialog.dismiss();

            String str = new String();
            for (int i = 0; i < editCash.getText().toString().length(); i++) {
                if (48 <= editCash.getText().toString().charAt(i) && editCash.getText().toString().charAt(i) <= 57)
                    str += editCash.getText().toString().charAt(i);
            }

            int total = ((MainActivity)getActivity()).getTotal()+Integer.parseInt(str);
            ((MainActivity)getActivity()).getPerson().getCash()
                    .add(new Cash("충전",
                            Integer.parseInt(str),
                            total));
            ((MainActivity)getActivity()).setTotal(Integer.parseInt(str));
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(MyPagePaymentFragment.this).commit();
            fragmentManager.popBackStack();
        }

    }


}
