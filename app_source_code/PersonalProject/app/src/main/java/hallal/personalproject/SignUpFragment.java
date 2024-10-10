package hallal.personalproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpFragment extends Fragment {





    public SignUpFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view=getView();

        if (view!=null)
        {


            try {

                Button confirm_button=view.findViewById(R.id.sign_up_confirm_button);

                confirm_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean add = true;
                        boolean proceed=true;

                        EditText editText_name = view.findViewById(R.id.name_edit_text_signUp);
                        EditText editText_email = view.findViewById(R.id.email_edit_text_signUp);
                        EditText editText_phone = view.findViewById(R.id.phone_edit_text_signUp);
                        EditText editText_password = view.findViewById(R.id.password_edit_text_signUp);


                        Editable nameEditable=editText_name.getText();
                        Editable emailEditable=editText_email.getText();
                        Editable phoneEditable=editText_phone.getText();
                        Editable passwordEditable=editText_password.getText();

                        String name = nameEditable.toString();
                        String email = emailEditable.toString();
                        String phone = phoneEditable.toString();
                        String password = passwordEditable.toString();


                        if (name==null || name.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Name", Toast.LENGTH_SHORT).show();

                        }

                        if (email==null || email.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Email", Toast.LENGTH_SHORT).show();
                        }

                        if (phone==null || phone.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
                        }

                        if (password==null || password.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Password", Toast.LENGTH_SHORT).show();
                        }

                        if (proceed) {

                            SQLiteOpenHelper sqLiteOpenHelper = new CredentialsSQLiteOpenHelper(getLayoutInflater().getContext());

                            SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

                            Cursor cursor = db.query("CREDENTIAL", new String[]{"EMAIL", "PHONE_NUMBER"}, null, null, null, null, null);


                            if (cursor.moveToFirst()) {


                                String user_email = cursor.getString(0);
                                String user_phone=cursor.getString(1);

                                if (user_email.equalsIgnoreCase(email) || user_phone.equalsIgnoreCase(phone)) {
                                    add = false;
                                    Toast.makeText(getLayoutInflater().getContext(), "Email or Phone already used", Toast.LENGTH_SHORT).show();
                                }

                                while (cursor.moveToNext()) {
                                    user_email = cursor.getString(0);
                                    user_phone=cursor.getString(1);

                                    if (user_email.equalsIgnoreCase(email) || user_phone.equalsIgnoreCase(phone)) {
                                        add = false;
                                        Toast.makeText(getLayoutInflater().getContext(), "Email or Phone already used", Toast.LENGTH_SHORT).show();
                                    }

                                }





                            }

                            if (add) {

                                CredentialsSQLiteOpenHelper.insertCredentials(db, name, email, phone, password);

                                Intent intent=new Intent(getLayoutInflater().getContext(), HomeActivity.class);
                                intent.putExtra(HomeActivity.EMAILORPHONE, phone);
                                startActivity(intent);
                                Toast.makeText(getLayoutInflater().getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();

                            }

                            cursor.close();
                            db.close();

                        }

                    }
                });



            }

            catch(Exception e)
            {
                Toast.makeText(getLayoutInflater().getContext(), "Database Not Available", Toast.LENGTH_SHORT).show();
            }
        }
    }
}