package hallal.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    public final static String EMAILORPHONE="email or phone";
    private String emailOrPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent=getIntent();

        emailOrPhone=intent.getStringExtra(EMAILORPHONE);

        Toolbar toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
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
            Toast.makeText(this, "You Are Already Here!", Toast.LENGTH_SHORT).show();
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

    public void onClickRequest(View view)
    {
        Intent intent=new Intent(this, RequestActivity.class);
        intent.putExtra(RequestActivity.EMAILORPHONE, emailOrPhone);
        startActivity(intent);
    }

    public void onClickDonate(View view)
    {
        Intent intent=new Intent(this, DonateActivity.class);
        intent.putExtra(DonateActivity.EMAILORPHONE, emailOrPhone);
        startActivity(intent);
    }
}