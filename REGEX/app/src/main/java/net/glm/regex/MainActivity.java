package net.glm.regex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView stringText, resultText;

    //text for first example
    private static final String REGEX="adam";
    private static final String INPUT="adam adam michal noa adam adamous anadam adam";

    //text for second example
    private static final String REGEX2="kill";
    private static final String REPLACE="feed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPointer();
        //regexTest1();
        regexText2();
    }

    private void setPointer()
    {
        stringText=(TextView)findViewById(R.id.txtOurString);
        resultText=(TextView)findViewById(R.id.txtResult);
    }

    private void regexTest1() //find
    {
        //create a pattern from our regex
        Pattern p = Pattern.compile(REGEX);
        //create a matcher from our regex
        Matcher m = p.matcher(INPUT);

        //init our counter
        int counter=0;
        while (m.find())
        {
            //increase counter by one
            counter+=1;
        }

        stringText.setText(INPUT);
        //display the result
        resultText.setText("adam found : "+counter+" times");
    }

    private void regexText2() //replace
    {

        //our string for manipulation
        String killFlora="we need to kill flora today, and also kill adam";
        stringText.setText(killFlora);
        //create a pattren from our regex
        Pattern p = Pattern.compile(REGEX2);
        //create a matcher for our pattren
        Matcher m = p.matcher(killFlora);
        //replace all the words inside
        killFlora=m.replaceAll(REPLACE);

        //display the result
        resultText.setText(killFlora);
    }

}

