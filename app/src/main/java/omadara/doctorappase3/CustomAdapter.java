package omadara.doctorappase3;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import java.util.ArrayList;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Availables>{

    private ArrayList<Availables> list;
    private Context context;

    public CustomAdapter(Context context, int resource, ArrayList<Availables> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
        list = new ArrayList<Availables>();
        list.addAll(objects);
    }

    private class ViewHolder {
        TextView code;
        CheckBox reserved;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
<<<<<<< HEAD
            convertView = vi.inflate(R.layout.bridge_listview, null);
            holder = new ViewHolder();
            holder.reserved= (CheckBox) convertView.findViewById(R.id.reserved);
            holder.code = (TextView) convertView.findViewById(R.id.avs_info);
=======
            convertView = vi.inflate(R.layout.listview_layout, null);
            holder = new ViewHolder();
            holder.reserved= (CheckBox) convertView.findViewById(R.id.reserved);
            holder.code = (TextView) convertView.findViewById(R.id.events_info);
>>>>>>> 319af5e4196ee0d035f9359a92be54704cc2207d
            convertView.setTag(holder);

            holder.reserved.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    CheckBox cb = (CheckBox)v;
                    Availables available = (Availables) cb.getTag();
                    available.setReservation()(cb.isChecked());
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Availables available = list.get(position);
<<<<<<< HEAD
        holder.code.setText(" (" +  available.getName() + ")");
=======
        holder.code.setText(" (" +  available.getAvailablesName() + ")");
>>>>>>> 319af5e4196ee0d035f9359a92be54704cc2207d
        holder.attend.setChecked(available.getReservation());
        holder.attend.setTag(available);
        return convertView;
    }
}