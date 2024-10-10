package hallal.personalproject;

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


public class DonateFragment extends Fragment {



    public DonateFragment() {
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
        return inflater.inflate(R.layout.fragment_donate, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view=getView();

        EditText editText_name=view.findViewById(R.id.name_edit_text_donate);
        EditText editText_phone=view.findViewById(R.id.phone_edit_text_donate);
        EditText editText_age=view.findViewById(R.id.age_edit_text_donate);
        EditText editText_bloodType=view.findViewById(R.id.blood_type_text_donate);
        EditText editText_healthIssues=view.findViewById(R.id.health_issues_edit_text_donate);

        Editable nameEditable=editText_name.getText();
        Editable phoneEditable=editText_phone.getText();
        Editable ageEditable=editText_age.getText();
        Editable bloodTypeEditable=editText_bloodType.getText();
        Editable healthIssuesEditable=editText_healthIssues.getText();

        String name=nameEditable.toString();
        String phone=nameEditable.toString();
        String age=ageEditable.toString();
        String healthIssues=healthIssuesEditable.toString();

        if (view!=null)
        {
            try {

                SQLiteOpenHelper sqLiteOpenHelper=new CredentialsSQLiteOpenHelper(getLayoutInflater().getContext());
                SQLiteDatabase db=sqLiteOpenHelper.getReadableDatabase();


                Cursor cursor=db.query("CREDENTIAL", new String[]{"NAME", "PHONE_NUMBER"}, "EMAIL=?", new String[]{DonateActivity.emailOrPhone}, null, null, null );
                Cursor cursor_2=db.query("CREDENTIAL", new String[]{"NAME", "PHONE_NUMBER"}, "PHONE_NUMBER=?", new String[]{DonateActivity.emailOrPhone}, null, null, null );

                if (cursor.moveToFirst())
                {
                    String userName=cursor.getString(0);
                    String userPhone=cursor.getString(1);

                    editText_name.setText(userName);
                    editText_phone.setText(userPhone);
                }

                if (cursor_2.moveToFirst())
                {
                    String userName=cursor_2.getString(0);
                    String userPhone=cursor_2.getString(1);

                    editText_name.setText(userName);
                    editText_phone.setText(userPhone);
                }


                cursor.close();
                cursor_2.close();
                db.close();

            }
            catch (Exception e)
            {
                Toast.makeText(getLayoutInflater().getContext(), "Database Not Available", Toast.LENGTH_SHORT).show();
            }

            Button confirm_button=view.findViewById(R.id.donate_confirm_button);

            confirm_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean proceed=true;

                    Editable nameEditable=editText_name.getText();
                    Editable phoneEditable=editText_phone.getText();
                    Editable ageEditable=editText_age.getText();
                    Editable bloodTypeEditable=editText_bloodType.getText();
                    Editable healthIssuesEditable=editText_healthIssues.getText();

                    String name=nameEditable.toString();
                    String phone=nameEditable.toString();
                    String age=ageEditable.toString();
                    String bloodType=bloodTypeEditable.toString();
                    String healthIssues=healthIssuesEditable.toString();

                    if (name==null || name.equalsIgnoreCase(""))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Name", Toast.LENGTH_SHORT).show();

                    }

                    if (phone==null || phone.equalsIgnoreCase(""))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
                    }

                    if (age==null || age.equalsIgnoreCase(""))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Age", Toast.LENGTH_SHORT).show();
                    }

                    if (bloodType==null || bloodType.equalsIgnoreCase(""))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Blood Type", Toast.LENGTH_SHORT).show();

                    }

                    if (healthIssues==null || healthIssues.equalsIgnoreCase(""))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Health Issue", Toast.LENGTH_SHORT).show();

                    }

                    int age_number=new Integer(age);

                    if (age_number<18)
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "You Need To Be over 18 To Donate!", Toast.LENGTH_SHORT).show();

                    }

                    if (!healthIssues.equalsIgnoreCase("yes") && !healthIssues.equalsIgnoreCase("no"))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "Enter Either Yes or No Please!", Toast.LENGTH_SHORT).show();
                    }

                    if (healthIssues.equalsIgnoreCase("yes"))
                    {
                        proceed=false;
                        Toast.makeText(getLayoutInflater().getContext(), "You Cannot Donate Due To Health Issues!", Toast.LENGTH_SHORT).show();
                    }

                    if (proceed)
                    {

                        Intent intent=new Intent(getLayoutInflater().getContext(), RequestListActivity.class);
                        intent.putExtra(RequestListActivity.EMAILORPHONE, DonateActivity.emailOrPhone);
                        intent.putExtra(RequestListActivity.BLOODTYPE, bloodType);
                        startActivity(intent);

                    }


                }
            });


        }


    }
}