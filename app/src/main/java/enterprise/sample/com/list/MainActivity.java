package enterprise.sample.com.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edFileName, edContent;
    Button btnSave;
    ListView listSavedFiles;


    String[] SavedFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edFileName = (EditText)findViewById(R.id.filename);
        edContent = (EditText)findViewById(R.id.content);
        btnSave = (Button)findViewById(R.id.save);
        listSavedFiles = (ListView)findViewById(R.id.list);

        ShowSavedFiles();

        btnSave.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String fileName = edFileName.getText().toString();
                String content = edContent.getText().toString();

                FileOutputStream fos;
                try {
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();

                    Toast.makeText(
                            getApplicationContext(),
                            fileName + " saved",
                            Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                ShowSavedFiles();

            }});

    }

    void ShowSavedFiles(){
        SavedFiles = getApplicationContext().fileList();
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                SavedFiles);

        listSavedFiles.setAdapter(adapter);
    }
}
