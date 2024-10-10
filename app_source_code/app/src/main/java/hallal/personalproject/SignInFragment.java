package hallal.personalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignInFragment extends Fragment {



    public SignInFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }


    public void onStart() {
        super.onStart();

        View view=getView();

        if (view!=null)
        {
            try {

                Button confirm_button=view.findViewById(R.id.sign_in_confirm_button);

                confirm_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean access=false;
                        boolean exist=false;
                        boolean proceed=true;

                        EditText editText_emailOrphone = view.findViewById(R.id.emailOrPhone_edit_text_signIn);
                        EditText editText_password = view.findViewById(R.id.password_edit_text_signIn);

                        Editable emailOrphoneEditable=editText_emailOrphone.getText();
                        Editable passwordEditable=editText_password.getText();

                        String emailOrphone = emailOrphoneEditable.toString();
                        String password = passwordEditable.toString();

                        if (emailOrphone==null || emailOrphone.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Email or Phone Number", Toast.LENGTH_SHORT).show();
                        }

                        if (password==null || password.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Password", Toast.LENGTH_SHORT).show();
                        }

                        if (proceed)
                        {
                            SQLiteOpenHelper sqLiteOpenHelper = new CredentialsSQLiteOpenHelper(getLayoutInflater().getContext());

                            SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

                            Cursor cursor = db.query("CREDENTIAL", new String[]{"EMAIL", "PHONE_NUMBER", "PASSWORD"}, null, null, null, null, null);

                            if (cursor.moveToFirst())
                            {
                                String user_email = cursor.getString(0);
                                String user_phone=cursor.getString(1);

                                if (user_email.equalsIgnoreCase(emailOrphone) || user_phone.equalsIgnoreCase(emailOrphone))
                                {
                                    exist=true;
                                }

                                while (cursor.moveToNext())
                                {
                                    user_email = cursor.getString(0);
                                    user_phone=cursor.getString(1);

                                    if (user_email.equalsIgnoreCase(emailOrphone) || user_phone.equalsIgnoreCase(emailOrphone))
                                    {
                                        exist=true;
                                    }

                                }

                                if(exist==false)
                                {
                                    Toast.makeText(getLayoutInflater().getContext(), "Account Does Not Exist", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getLayoutInflater().getContext(), "Please Sign Up!", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(getLayoutInflater().getContext(), MainActivity.class);
                                    startActivity(intent);
                                }


                            }

                            if (exist)
                            {
                                if (cursor.moveToFirst())
                                {
                                    String user_email = cursor.getString(0);
                                    String user_phone=cursor.getString(1);
                                    String user_password=cursor.getString(2);

                                    if (user_password.equals(password) && (user_email.equalsIgnoreCase(emailOrphone) || user_phone.equalsIgnoreCase(emailOrphone)))
                                    {
                                        access=true;
                                    }

                                    while (cursor.moveToNext())
                                    {
                                        user_email = cursor.getString(0);
                                        user_phone=cursor.getString(1);
                                        user_password=cursor.getString(2);

                                        if (user_password.equals(password) && (user_email.equalsIgnoreCase(emailOrphone) || user_phone.equalsIgnoreCase(emailOrphone)))
                                        {
                                            access=true;
                                        }

                                    }

                                    if (access==false)
                                    {

                                        Toast.makeText(getLayoutInflater().getContext(), "Incorrect Password or Email/Phone", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }

                            if (access)
                            {
                                Intent intent=new Intent(getLayoutInflater().getContext(), HomeActivity.class);
                                intent.putExtra(HomeActivity.EMAILORPHONE, emailOrphone);
                                startActivity(intent);
                                Toast.makeText(getLayoutInflater().getContext(), "Sign In Successful", Toast.LENGTH_SHORT).show();

                            }


                            cursor.close();
                            db.close();

                        }

                    }
                });

            }

            catch (Exception e)
            {
                Toast.makeText(getLayoutInflater().getContext(), "Database Not Available", Toast.LENGTH_SHORT).show();
            }
        }
    }
}