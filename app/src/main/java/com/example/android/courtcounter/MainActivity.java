package com.example.android.courtcounter;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    TextView score_teamA , score_teamB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        score_teamA = findViewById(R.id.team_a_score);
        score_teamB = findViewById(R.id.team_b_score);




    }
    // TO INSERT DATA
    public void database_add(View view)
    {
       boolean isInserted = myDb.insertData(score_teamA.getText().toString() , score_teamB.getText().toString());
       if(isInserted)
       {
           Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

       }

       else

           Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
    }

    // TO SHOW DATA

    public void Data_dikhao(View view)
    {
        Cursor res = myDb.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error", "Nothing Found");

            return ;
        }


            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append("Match : "+ res.getString(0)+"\n");
                buffer.append("Team A : "+ res.getString(1)+"\n");
                buffer.append("Team B : "+ res.getString(2)+"\n\n");

            }

            //show all data
        showMessage("Data", buffer.toString());

    }
    public void showMessage(String title , String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void deletekardunga(View view)
    {
        TextView del = findViewById(R.id.delid);
        Integer deletedRows = myDb.deletedata(del.getText().toString());
        if(deletedRows > 0)
            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();

        else
            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
    }

   int scoreTeamA = 0;
     public void addThreeForTeamA (View view)
    {    scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }
     public void addTwoForTeamA (View view)
    {    scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

       public void addOneForTeamA (View view)
    {    scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }



    int scoreTeamB = 0;
    public void addThreeForTeamB (View view)
    {    scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }
    public void addTwoForTeamB (View view)
    {    scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    public void addOneForTeamB (View view)
    {    scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }



    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }



    public void ResetScore(View view)
    {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
