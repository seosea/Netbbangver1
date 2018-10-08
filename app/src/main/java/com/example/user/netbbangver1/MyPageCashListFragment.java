package com.example.user.netbbangver1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyPageCashListFragment extends Fragment{

    ImageButton btnBack;
    ListView listCash;
    CashAdapter cashAdapter;
    Person person; // 더미
    LinearLayout linearLayoutMenu;
    RelativeLayout relativeLayoutTitle;
    Button btnHold, btnTransaction, btnDefault;
    ImageButton btnFilter;
    LinearLayout linearTransaction;
    private Dialog dialog;
    View customLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_my_page_cash_list, container, false );

        linearLayoutMenu = (LinearLayout)view.findViewById(R.id.linear_menu_cash_list);
        relativeLayoutTitle = (RelativeLayout)view.findViewById(R.id.relative_title_cash_list);
        btnBack = (ImageButton)view.findViewById(R.id.btn_back_from_cash_list);
        btnHold = (Button)view.findViewById(R.id.btn_hold_amount_menu_cash_list);
        btnTransaction = (Button)view.findViewById(R.id.btn_transaction_menu_cash_list);
        btnFilter = (ImageButton)view.findViewById(R.id.btn_filter_cash_list);
        listCash = (ListView)view.findViewById(R.id.list_cash_list);
        linearTransaction = (LinearLayout) view.findViewById(R.id.linear_transaction_cash_list);
        btnDefault = (Button)view.findViewById(R.id.btn_default_cash_list);

        if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
            linearLayoutMenu.setBackgroundResource(R.color.colorGreen);
            relativeLayoutTitle.setBackgroundResource(R.color.colorGreen);
            btnBack.setBackgroundResource(R.color.colorGreen);
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                btnHold.setBackgroundDrawable( getResources().getDrawable(R.drawable.cash_list_box_green) );
            } else {
                btnHold.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cash_list_box_green));
            }
            btnTransaction.setBackgroundResource(R.color.colorGreen);
            btnFilter.setBackgroundResource(R.color.colorGreen);
            btnDefault.setBackgroundResource(R.color.colorGreen);
        }
        else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
            linearLayoutMenu.setBackgroundResource(R.color.colorNavy);
            relativeLayoutTitle.setBackgroundResource(R.color.colorNavy);
            btnBack.setBackgroundResource(R.color.colorNavy);final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                btnHold.setBackgroundDrawable( getResources().getDrawable(R.drawable.cash_list_box_navy) );
            } else {
                btnHold.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cash_list_box_navy));
            }
            btnTransaction.setBackgroundResource(R.color.colorNavy);
            btnFilter.setBackgroundResource(R.color.colorNavy);
            btnDefault.setBackgroundResource(R.color.colorNavy);
        }

        cashAdapter = ((MainActivity)getActivity()).cashAdapter;
        person = ((MainActivity)getActivity()).getPerson();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MyPageCashListFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });


        cashAdapter.resetList();

        for(int i=person.getCash().size()-1;i>=0;i--){
            cashAdapter.addItem(person.getCash().get(i));
        }
        listCash.setAdapter(cashAdapter);
        ((CashAdapter)listCash.getAdapter()).getFilter().filter("All");

        btnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 보유금 내역
                linearTransaction.setVisibility(View.GONE);
                btnFilter.setVisibility(View.VISIBLE);
                btnDefault.setVisibility(View.GONE);
                listCash.setVisibility(View.VISIBLE);

                if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        btnHold.setBackgroundDrawable( getResources().getDrawable(R.drawable.cash_list_box_green) );
                    } else {
                        btnHold.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cash_list_box_green));
                    }
                    btnTransaction.setBackgroundResource(R.color.colorGreen);
                }else {
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        btnHold.setBackgroundDrawable( getResources().getDrawable(R.drawable.cash_list_box_navy) );
                    } else {
                        btnHold.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cash_list_box_navy));
                    }
                    btnTransaction.setBackgroundResource(R.color.colorNavy);
                }

                cashAdapter.resetList();
                for(int i=person.getCash().size()-1;i>=0;i--){
                    cashAdapter.addItem(person.getCash().get(i));
                }
                listCash.setAdapter(cashAdapter);
            }
        });

        btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 거래별 내역
                linearTransaction.setVisibility(View.VISIBLE);
                btnFilter.setVisibility(View.GONE);
                btnDefault.setVisibility(View.VISIBLE);
                listCash.setVisibility(View.GONE);

                if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        btnTransaction.setBackgroundDrawable( getResources().getDrawable(R.drawable.cash_list_box_green) );
                    } else {
                        btnTransaction.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cash_list_box_green));
                    }
                    btnHold.setBackgroundResource(R.color.colorGreen);
                }else {
                    final int sdk = android.os.Build.VERSION.SDK_INT;
                    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        btnTransaction.setBackgroundDrawable( getResources().getDrawable(R.drawable.cash_list_box_navy) );
                    } else {
                        btnTransaction.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.cash_list_box_navy));
                    }
                    btnHold.setBackgroundResource(R.color.colorNavy);
                }

            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                customLayout=View.inflate(getActivity(),R.layout.dialog_filter_cash_list,null);
                builder.setView(customLayout);
                Button btnFilterConfirm = customLayout.findViewById(R.id.btn_filter_cash_list_dialog);
                btnFilterConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String strFilter = "";

                        CheckBox checkWithdraw = customLayout.findViewById(R.id.check_withdraw_cash_list_dialog);
                        CheckBox checkRefund = customLayout.findViewById(R.id.check_refund_cash_list_dialog);
                        CheckBox checkCharge = customLayout.findViewById(R.id.check_charge_cash_list_dialog);
                        CheckBox checkWork = customLayout.findViewById(R.id.check_work_cash_list_dialog);
                        CheckBox checkEvent = customLayout.findViewById(R.id.check_event_cash_list_dialog);

                        if(checkWithdraw.isChecked()) strFilter+="출금 ";
                        if(checkRefund.isChecked()) strFilter+="환불 ";
                        if(checkCharge.isChecked()) strFilter+="충전 ";
                        if(checkWork.isChecked()) strFilter+="작업비 ";
                        if(checkEvent.isChecked()) strFilter+="이벤트";
                        ((CashAdapter)listCash.getAdapter()).getFilter().filter(strFilter);
                    }
                });

                dialog=builder.create();
                dialog.show();
            }
        });

        return view;
    }

}
