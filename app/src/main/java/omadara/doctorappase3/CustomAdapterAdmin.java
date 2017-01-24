package omadara.doctorappase3;

/**
 * Created by plzdo on 24/1/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterAdmin extends ArrayAdapter<AdminItem>{

    private ArrayList<AdminItem> list;
    private Context context;

    public CustomAdapterAdmin(Context context, int resource, ArrayList<AdminItem> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
        list = new ArrayList<AdminItem>();
        list.addAll(objects);
    }

    private class ViewHolder {
        TextView code;
        CheckBox reserved;
    }

}
