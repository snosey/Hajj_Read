package skygiconnect.mcs.hajj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteAmount extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.write_amount, container, false);

        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.nfc)
    public void onNfcClicked() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ScanNFC fragment = new ScanNFC();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment, "ScanNFC");
        ft.addToBackStack("ScanNFC");
        ft.commit();
    }

    @OnClick(R.id.idNumber)
    public void onIdNumberClicked() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        WriteID fragment = new WriteID();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment, "WriteID");
        ft.addToBackStack("WriteID");
        ft.commit();
    }
}
