package com.example.user.moodleapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by USER on 19-02-2016.
 */
public class CustomList extends ArrayAdapter<String>
{
    private String[] codesc;
    private String[] namesc;
    private String[] descriptionsc;
    private String[] creditsc;
    private String[] idsc;
    private String[] ltpsc;
    private Activity context;

    public CustomList(Activity context, String[] codes,String[] names,String[] descriptions,String[] credits,String[] ids,String[] ltps)
    {
        super(context, R.layout.list_view_layout,ids);
        this.context=context;
        this.codesc=codes;
        this.namesc=names;
        this.descriptionsc=descriptions;
        this.creditsc=credits;
        this.idsc=ids;
        this.ltpsc=ltps;
    }
    @Override
    public View getView(int position,View convetView,ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View listVIewItem=inflater.inflate(R.layout.list_view_layout, null, true);
        TextView tvcode=(TextView)listVIewItem.findViewById(R.id.code);
        TextView tvname=(TextView)listVIewItem.findViewById(R.id.name);
        TextView tvdescription=(TextView)listVIewItem.findViewById(R.id.description);
        TextView tvcredit=(TextView)listVIewItem.findViewById(R.id.credit);
        TextView tvid=(TextView)listVIewItem.findViewById(R.id.identity);
        TextView tvltp=(TextView)listVIewItem.findViewById(R.id.ltp);

        tvcode.setText(codesc[position]);
        tvname.setText(namesc[position]);
        tvdescription.setText(descriptionsc[position]);
        tvcredit.setText(creditsc[position]);
        tvid.setText(idsc[position]);
        tvltp.setText(ltpsc[position]);
        return listVIewItem;
    }


}
