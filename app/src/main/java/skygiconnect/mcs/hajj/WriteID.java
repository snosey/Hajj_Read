package skygiconnect.mcs.hajj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WriteID extends Fragment {

    @InjectView(R.id.writeId)
    EditText writeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.write_id, container, false);

        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
        final String id = writeId.getText().toString();
        if (id.equals("")) {
            writeId.setError("Please write ID !");
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Hajj").child("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.hasChild(id)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Pay fragment = new Pay();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment, fragment, "Pay");
                    ft.addToBackStack("Pay");
                    ft.commit();
                } else {
                    writeId.setError("Invalid ID !");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
