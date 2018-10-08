package com.example.user.netbbangver1;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MyPageFragment extends Fragment {
    RelativeLayout btnPayment, btnWithdraw, btnCashList, btnChangeAccount;
    TextView txtTotal, txtNickname;
    DecimalFormat myFormatter = new DecimalFormat("###,###");
    LinearLayout linearLayoutTitle, linearLayoutNameAndCash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false );

        linearLayoutTitle = (LinearLayout)view.findViewById(R.id.linear_title_mypage);
        linearLayoutNameAndCash = (LinearLayout)view.findViewById(R.id.linear_name_cash_mypage);

        btnChangeAccount = (RelativeLayout)view.findViewById(R.id.btn_change_account_mypage);

        if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
            linearLayoutTitle.setBackgroundResource(R.color.colorGreen);
            linearLayoutNameAndCash.setBackgroundResource(R.color.colorGreen);
        }
        else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
            linearLayoutTitle.setBackgroundResource(R.color.colorNavy);
            linearLayoutNameAndCash.setBackgroundResource(R.color.colorNavy);
        }

        btnChangeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setChangeAccount();
                if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
                    linearLayoutTitle.setBackgroundResource(R.color.colorGreen);
                    linearLayoutNameAndCash.setBackgroundResource(R.color.colorGreen);
                }
                else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
                    linearLayoutTitle.setBackgroundResource(R.color.colorNavy);
                    linearLayoutNameAndCash.setBackgroundResource(R.color.colorNavy);
                }
            }
        });

        btnPayment = (RelativeLayout)view.findViewById(R.id.btn_mypage_payment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).mainMenuClick(view);
            }
        });
        btnWithdraw = (RelativeLayout)view.findViewById(R.id.btn_mypage_withdraw);
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).mainMenuClick(view);
            }
        });
        btnCashList = (RelativeLayout)view.findViewById(R.id.btn_mypage_cash_list);
        btnCashList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).mainMenuClick(view);
            }
        });

        txtNickname = (TextView)view.findViewById(R.id.txt_nickname_mypage);
        txtNickname.setText(((MainActivity)getActivity()).getNickname());

        txtTotal = (TextView)view.findViewById(R.id.txt_total_mypage);
        txtTotal.setText(myFormatter.format(((MainActivity)getActivity()).getTotal()));

        return view;
    }

}
