package hallal.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class RequestListActivity extends AppCompatActivity  {

    public final static String EMAILORPHONE="email or phone";
    public final static String BLOODTYPE="blood type";
    public static String emailOrPhone;
    public static String bloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        Toolbar toolbar=findViewById(R.id.my_toolbar_request_list);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        emailOrPhone=intent.getStringExtra(EMAILORPHONE);
        bloodType=intent.getStringExtra(BLOODTYPE);




        RequestListFragment requestListFragment=new RequestListFragment();
        requestListFragment.bloodtype=bloodType;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container_request_list, requestListFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();


    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.action_home)
        {
            Intent intent=new Intent(this, HomeActivity.class);
            intent.putExtra(HomeActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);
            return true;
        }

        else if (item.getItemId()==R.id.action_profile)
        {
            Intent intent=new Intent(this, ProfileActivity.class);
            intent.putExtra(ProfileActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);
            return true;
        }

        else if (item.getItemId()==R.id.action_about_us)
        {

            Intent intent=new Intent(this, AboutUsActivity.class);
            intent.putExtra(AboutUsActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);
            return true;
        }

        else if (item.getItemId()==R.id.action_help)
        {
            Intent intent=new Intent(this, HelpActivity.class);
            intent.putExtra(HelpActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);
            return true;
        }


        else {
            return super.onOptionsItemSelected(item);
        }
    }


}