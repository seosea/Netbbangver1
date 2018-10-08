package com.example.user.netbbangver1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyPageWithdrawConfirmFragment extends Fragment {
    Button btnWithdrawConfirm;
    ImageButton btnBack;
    RelativeLayout relativeLayoutTitle;
    ImageView imgCheck;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_my_page_withdraw_confirm, container, false );

        relativeLayoutTitle = (RelativeLayout)view.findViewById(R.id.relative_title_withdraw_confirm);
        btnWithdrawConfirm = (Button)view.findViewById(R.id.btn_withdraw_confirm);
        btnBack = (ImageButton)view.findViewById(R.id.btn_back_from_withdraw_confirm);
        imgCheck = (ImageView)view.findViewById(R.id.img_check_withdraw_confirm);

        if(((MainActivity)getActivity()).getChangeAccount().equals("w")) {
            relativeLayoutTitle.setBackgroundResource(R.color.colorGreen);
            btnWithdrawConfirm.setBackgroundResource(R.color.colorGreen);
            btnBack.setBackgroundResource(R.color.colorGreen);
            imgCheck.setImageResource(R.drawable.blue_check);
        }
        else if(((MainActivity)getActivity()).getChangeAccount().equals("r")) {
            relativeLayoutTitle.setBackgroundResource(R.color.colorNavy);
            btnWithdrawConfirm.setBackgroundResource(R.color.colorNavy);
            btnBack.setBackgroundResource(R.color.colorNavy);
            imgCheck.setImageResource(R.drawable.green_check);
        }

        btnWithdrawConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).mainMenuClick(view);
            }
        });
        return view;
    }
}
