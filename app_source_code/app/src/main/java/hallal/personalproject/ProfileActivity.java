package hallal.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    public final static String EMAILORPHONE="email or phone";
    private String emailOrPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent=getIntent();
        emailOrPhone=intent.getStringExtra(EMAILORPHONE);

        Toolbar toolbar=findViewById(R.id.my_toolbar_profile);
        setSupportActionBar(toolbar);

        TextView name=findViewById(R.id.name_text_view);
        TextView email=findViewById(R.id.email_text_view);
        TextView password=findViewById(R.id.password_text_view);

        try {

            SQLiteOpenHelper sqLiteOpenHelper=new CredentialsSQLiteOpenHelper(this);

            SQLiteDatabase db=sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor=db.query("CREDENTIAL", new String[]{"NAME", "EMAIL", "PASSWORD"}, "EMAIL=?", new String[]{emailOrPhone}, null, null, null );
            Cursor cursor_2=db.query("CREDENTIAL", new String[]{"NAME", "EMAIL", "PASSWORD"}, "PHONE_NUMBER=?", new String[]{emailOrPhone}, null, null, null );

            if (cursor.moveToFirst())
            {
                String userName=cursor.getString(0);
                String userEmail=cursor.getString(1);
                String userPassword=cursor.getString(2);

                userName="-NAME: " +userName;
                userEmail="-EMAIL: " +userEmail;
                userPassword="-PASSWORD: " +userPassword;

                name.setText(userName);
                email.setText(userEmail);
                password.setText(userPassword);
            }

            if (cursor_2.moveToFirst())
            {
                String userName=cursor_2.getString(0);
                String userEmail=cursor_2.getString(1);
                String userPassword=cursor_2.getString(2);

                userName="-NAME: " +userName;
                userEmail="-EMAIL: " +userEmail;
                userPassword="-PASSWORD: " +userPassword;

                name.setText(userName);
                email.setText(userEmail);
                password.setText(userPassword);

            }

            cursor.close();
            cursor_2.close();
            db.close();


        }

        catch (Exception e)
        {

            Toast.makeText(this, "Database Not Available", Toast.LENGTH_SHORT).show();

        }
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
            intent.putExtra(HelpActivity.EMAILORPHONE, emailOrPhone);
            startActivity(intent);
            return true;
        }

        else if (item.getItemId()==R.id.action_profile)
        {
            Toast.makeText(this, "You Are Already Here!", Toast.LENGTH_SHORT).show();
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

    public void onChangePasswordClick(View view)
    {
        Intent intent=new Intent(this, UpdatePasswordActivity.class);
        intent.putExtra(UpdatePasswordActivity.EMAILORPHONE, emailOrPhone);
        startActivity(intent);

    }
}