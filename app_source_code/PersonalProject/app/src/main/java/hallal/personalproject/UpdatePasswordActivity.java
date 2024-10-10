package hallal.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePasswordActivity extends AppCompatActivity {

    public final static String EMAILORPHONE="email or phone";
    private String emailOrPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Intent intent=getIntent();
        emailOrPhone=intent.getStringExtra(EMAILORPHONE);

        Toolbar toolbar=findViewById(R.id.my_toolbar_profile);
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
            Intent intent=new Intent(this, HelpActivity.class);
            intent.putExtra(HelpActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);
            return true;
        }


        else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void onConfirmClick(View view)
    {
        try {

            SQLiteOpenHelper sqLiteOpenHelper=new CredentialsSQLiteOpenHelper(this);
            SQLiteDatabase db=sqLiteOpenHelper.getWritableDatabase();

            EditText editText=findViewById(R.id.new_password_edit_text);

            Editable new_password=editText.getText();

            ContentValues contentValues=new ContentValues();
            contentValues.put("PASSWORD", new_password.toString());

            db.update("CREDENTIAL", contentValues, "EMAIL=?", new String[]{emailOrPhone});
            db.update("CREDENTIAL", contentValues, "PHONE_NUMBER=?", new String[]{emailOrPhone});

            Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show();
            db.close();

            Intent intent=new Intent(this, ProfileActivity.class);
            intent.putExtra(ProfileActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);

        }

        catch(Exception e)
        {
            Toast.makeText(this, "Database Not Available", Toast.LENGTH_SHORT).show();
        }

    }
}