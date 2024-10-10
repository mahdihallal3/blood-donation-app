package hallal.personalproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import hallal.personalproject.dummy.DummyContent;


public class RequestListFragment extends ListFragment {

    SQLiteDatabase db;
    Cursor cursor;
    public String bloodtype;


    public RequestListFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {

            SQLiteOpenHelper sqLiteOpenHelper=new RequestsSQLiteOpenHelper(getLayoutInflater().getContext());
            db=sqLiteOpenHelper.getReadableDatabase();
            cursor=db.query("REQUEST", new String[]{"_id", "OUTPUT"}, "BLOODTYPE=?", new String[]{bloodtype}, null, null, null );
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, cursor, new String[] {"OUTPUT"}, new int[] {android.R.id.text1},0);

            setListAdapter(cursorAdapter);

        }
        catch(Exception e)
        {
            Toast.makeText(inflater.getContext(), "Database is not available", Toast.LENGTH_SHORT).show();

        }

        finally {

            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }


}


