package com.example.user.netbbangver1;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class CashAdapter extends BaseAdapter implements Filterable{

    private ArrayList<Cash> mItems;
    ArrayList<Cash> filteredItemList;

    Filter listFilter ;

    public interface GetAccountInterface {
        public String getChangeAccount();
    }

    public CashAdapter(){
        mItems = new ArrayList<Cash>();
        filteredItemList = mItems ;
    }

    private GetAccountInterface getAccountInterface;

    public void setGetAccountInterface(GetAccountInterface getAccountInterface){
        this.getAccountInterface = getAccountInterface;
    }

    /* 아이템을 세트로 담기 위한 어레이 */

    DecimalFormat myFormatter = new DecimalFormat("###,###");

    public void resetList(){
        mItems.clear();
    }

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public Cash getItem(int position) {
        return filteredItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cash_list, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView txt_cash_value = (TextView)convertView.findViewById(R.id.txt_cash_value);
        TextView txt_cash_calc = (TextView)convertView.findViewById(R.id.txt_cash_calc);
        TextView txt_cash_total = (TextView)convertView.findViewById(R.id.txt_cash_total);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mCash 재활용 */
        Cash myItem = (Cash) filteredItemList.get(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        txt_cash_value.setText(myItem.getValue());

        String strAccountState = getAccountInterface.getChangeAccount();
        if(strAccountState.equals("w")){
            txt_cash_value.setTextColor(Color.parseColor("#25285f"));
        } else if(strAccountState.equals("r")){
            txt_cash_value.setTextColor(Color.parseColor("#8fbd3c"));
        }

        if(myItem.getCharge()>0) {
            txt_cash_calc.setText("+"+myFormatter.format(myItem.getCharge()));
        }
        else {
            txt_cash_calc.setText(myFormatter.format(myItem.getCharge()));
        }
        if(myItem.getTotal()>0) txt_cash_total.setText("+"+myFormatter.format(myItem.getTotal()));
        else txt_cash_total.setText(myFormatter.format(myItem.getTotal()));

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */


        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String value, int cash, int total) {

        Cash mItem = new Cash();

        /* Cash에 아이템을 setting한다. */
        mItem.setValue(value);
        mItem.setCharge(cash);
        mItem.setTotal(total);

        /* mItems에 Cash을 추가한다. */
        mItems.add(mItem);

    }

    public void addItem(Cash cash) {
        /* mItems에 Cash을 추가한다. */
        mItems.add(cash);

    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }


    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = mItems;
                results.count = mItems.size();
                Log.i("알림 : ", constraint.toString());
            } else if(constraint.toString().equals("All")){
                results.values = mItems;
                results.count = mItems.size();
                Log.i("알림1 : ", constraint.toString());
            } else {
                ArrayList<Cash> itemList = new ArrayList<Cash>();
                Log.i("알림2 : ", constraint.toString());

                String[] data = constraint.toString().split(" ");
                for (Cash item : mItems) {
                    for(int i=0;i<data.length;i++){
                        if (item.getValue().toUpperCase().contains(data[i].toUpperCase())) {
                            itemList.add(item);
                        }
                    }
                }

                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItemList = (ArrayList<Cash>) results.values ;
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }

        }
    }


}