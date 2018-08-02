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

public class WriteCode extends Fragment {

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
        FragmentManager fm = getActivity().getSupportFragmentManager();
        SuccessPay fragment = new SuccessPay();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment, "SuccessPay");
        ft.addToBackStack("SuccessPay");
        ft.commit();
    }
}
