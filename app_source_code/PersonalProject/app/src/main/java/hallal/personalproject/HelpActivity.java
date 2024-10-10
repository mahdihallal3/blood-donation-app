package hallal.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    public final static String EMAILORPHONE="email or phone";
    private String emailOrPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Intent intent=getIntent();
        emailOrPhone=intent.getStringExtra(EMAILORPHONE);

        Toolbar toolbar=findViewById(R.id.my_toolbar_help);
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
            Toast.makeText(this, "You Are Already Here!", Toast.LENGTH_SHORT).show();
            return true;
        }

        else {
            return super.onOptionsItemSelected(item);
        }
    }
}