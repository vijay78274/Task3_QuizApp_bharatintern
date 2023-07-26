package com.example.quizapp;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static int value;
    static int score = 0;
    String valueChoose="";
    int current_question = 0;
    TextView questions_view,que;
    Button choice1, choice2, choice3, choice4;
    Button submit;
    static String[] questions = {
            "Which is the slowest programming language?",
            "Which data structure is based on LIFO principle?",
            "Which one is not a programming language?",
            "Which loop is executed at least once?",
            "Which header file includes string functions?",
            "Who developed C programming language?"
    };
    String[][] choices = {
            {"Python", "Java", "C", "Javascript"},
            {"Queue", "Stack", "Linked-list", "Array"},
            {"Java", "HTML", "Sql", "Python"},
            {"do-while", "for", "while", "Nested"},
            {"math.h", "stdin.h", "string.h", "stdlib.h"},
            {"Guido van Rossum", "James Gosling", "Bjarne Stroustrup", "Dennis Richie"}
    };
    String[] answers = {
            "Python",
            "Stack",
            "HTML",
            "do-while",
            "string.h",
            "Dennis Richie"
    };
     static int total_questions = questions.length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions_view = (TextView) findViewById(R.id.questions);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        submit = findViewById(R.id.submit);
        que = findViewById(R.id.que);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);
        submit.setOnClickListener(this);

        change_questions();
    }
    @Override
    public void onClick(View v) {
        choice1.setBackgroundColor(getResources().getColor(R.color.quiz_back));
        choice2.setBackgroundColor(getResources().getColor(R.color.quiz_back));
        choice3.setBackgroundColor(getResources().getColor(R.color.quiz_back));
        choice4.setBackgroundColor(getResources().getColor(R.color.quiz_back));
        Button click = (Button)v;

        if(click.getId()==R.id.submit){
            if(valueChoose.equals(answers[current_question])){
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                score++;
            }
            else{
                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            }
            if(current_question<total_questions){
            current_question++;
            change_questions();
            }
        }
        else{
            valueChoose = click.getText().toString();
            click.setBackgroundColor(getResources().getColor(android.R.color.black));
        }
    }
    @SuppressLint("DefaultLocale")
    void change_questions() {
        if(current_question==total_questions){
           finishQuiz();
           return;
        }
        else {
            que.setText(String.format("%d/%d", current_question + 1, total_questions));
            questions_view.setText(questions[current_question]);
            choice1.setText(choices[current_question][0]);
            choice2.setText(choices[current_question][1]);
            choice3.setText(choices[current_question][2]);
            choice4.setText(choices[current_question][3]);
        }
    }
    void finishQuiz(){
        String status = "";
        if(score>total_questions*0.5){
            status="Passed";
        }
        else{
            status="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(status).setMessage("Score is "+ score+ " out of "+total_questions)
                .setPositiveButton("Restart",(dialogInterface,i)->restartquiz()).setCancelable(false)
                .show();
    }

    void restartquiz(){
        score=0;
        current_question=0;
        change_questions();
    }
}