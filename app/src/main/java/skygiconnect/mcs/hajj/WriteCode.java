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

public class WriteCode extends Fragment {

    @InjectView(R.id.writeId)
    EditText writeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_code, container, false);

        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        final String id = writeId.getText().toString();
        if (id.equals("")) {
            writeId.setError("Please write Pin Code !");
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Hajj").child("Users").child(getArguments().getString("id"));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.child("pinCode").getValue().toString().equals(id)) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    SuccessPay fragment = new SuccessPay();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment, fragment, "SuccessPay");
                    ft.addToBackStack("SuccessPay");
                    ft.commit();
                } else {
                    writeId.setError("Invalid Pin Code !");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
