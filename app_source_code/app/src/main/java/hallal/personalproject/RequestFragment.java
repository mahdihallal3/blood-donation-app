package hallal.personalproject;

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
import android.widget.TextView;
import android.widget.Toast;


public class RequestFragment extends Fragment {



    public RequestFragment() {
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
        return inflater.inflate(R.layout.fragment_request, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view=getView();


        EditText editText_name=view.findViewById(R.id.name_edit_text_request);
        EditText editText_bloodType=view.findViewById(R.id.blood_type_edit_text_request);
        EditText editText_location=view.findViewById(R.id.location_edit_text_request);
        EditText editText_phone=view.findViewById(R.id.phone_edit_text_request);
        EditText editText_alternative_name=view.findViewById(R.id.alternative_contact_name_edit_text_request);
        EditText editText_alternative_phone=view.findViewById(R.id.alternative_contanct_phone_edit_text_request);

        Editable nameEditable=editText_name.getText();
        Editable bloodTypeEditable=editText_bloodType.getText();
        Editable locationEditable=editText_location.getText();
        Editable phoneEditable=editText_phone.getText();
        Editable alternativeNameEditable=editText_alternative_name.getText();
        Editable alternativePhoneEditable=editText_alternative_phone.getText();

        String name=nameEditable.toString();
        String bloodType=bloodTypeEditable.toString();
        String location=locationEditable.toString();
        String phone=phoneEditable.toString();
        String alternativeName=alternativeNameEditable.toString();
        String alternativePhone=alternativePhoneEditable.toString();



        if (view!=null)
        {
            try {

                SQLiteOpenHelper sqLiteOpenHelper=new CredentialsSQLiteOpenHelper(getLayoutInflater().getContext());
                SQLiteDatabase db=sqLiteOpenHelper.getReadableDatabase();

                Cursor cursor=db.query("CREDENTIAL", new String[]{"NAME", "PHONE_NUMBER"}, "EMAIL=?", new String[]{RequestActivity.emailOrPhone}, null, null, null );
                Cursor cursor_2=db.query("CREDENTIAL", new String[]{"NAME", "PHONE_NUMBER"}, "PHONE_NUMBER=?", new String[]{RequestActivity.emailOrPhone}, null, null, null );

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

            try{

                Button confirm_button=view.findViewById(R.id.request_confirm_button);

                confirm_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean proceed=true;

                        Editable nameEditable=editText_name.getText();
                        Editable bloodTypeEditable=editText_bloodType.getText();
                        Editable locationEditable=editText_location.getText();
                        Editable phoneEditable=editText_phone.getText();
                        Editable alternativeNameEditable=editText_alternative_name.getText();
                        Editable alternativePhoneEditable=editText_alternative_phone.getText();

                        TextView textView=view.findViewById(R.id.request_success_text_view);




                        String name=nameEditable.toString();
                        String bloodType=bloodTypeEditable.toString();
                        String location=locationEditable.toString();
                        String phone=phoneEditable.toString();
                        String alternativeName=alternativeNameEditable.toString();
                        String alternativePhone=alternativePhoneEditable.toString();


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

                        if (bloodType==null || bloodType.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Blood Type", Toast.LENGTH_SHORT).show();

                        }

                        if (location==null || location.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Location", Toast.LENGTH_SHORT).show();

                        }


                        if (alternativeName==null || alternativeName.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Alternative Name", Toast.LENGTH_SHORT).show();

                        }

                        if (alternativePhone==null || alternativePhone.equalsIgnoreCase(""))
                        {
                            proceed=false;
                            Toast.makeText(getLayoutInflater().getContext(), "Enter a Valid Alternative Phone Number", Toast.LENGTH_SHORT).show();

                        }

                        if (proceed)
                        {
                            SQLiteOpenHelper sqLiteOpenHelper=new RequestsSQLiteOpenHelper(getLayoutInflater().getContext());

                            SQLiteDatabase db=sqLiteOpenHelper.getWritableDatabase();

                            String output= "Name: " + name + '\t' + '\t' + "Phone Number: " + phone + '\t' + '\t' + "Location: "+ location;

                            RequestsSQLiteOpenHelper.insertRequest(db, name, bloodType, location, phone, alternativeName, alternativePhone, output);
                            textView.setText("REQUEST SENT!");

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