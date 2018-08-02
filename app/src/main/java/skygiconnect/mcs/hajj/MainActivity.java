package skygiconnect.mcs.hajj;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        FragmentManager fm = getSupportFragmentManager();
        Login fragment = new Login();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment, "Login");
        ft.commit();
    }
}
