package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageButton exit_button_IB;
    Dialog exit_question_D;
    LinearLayout reset_button_LL;
    ImageView reset_image_view_IV,match_tabs_IV;
    Animation rotate_A;
    Button no_button_B,yes_button_B;
    ImageView indicator_IV;
    GLtab  tab_0_0 = new GLtab(),tab_0_1 = new GLtab(),tab_0_2 = new GLtab(),
            tab_1_0 = new GLtab(),tab_1_1 = new GLtab(),tab_1_2 = new GLtab(),
            tab_2_0 = new GLtab(),tab_2_1 = new GLtab(),tab_2_2 = new GLtab();
    String indicator_status_Str = "left"; //indicator status
    Integer step = 1,multiplied_score_I = 0,circle_score_I = 0;
    AnimatedVectorDrawable avd;
    TextView multiplied_score_TV,circle_score_TV;
    boolean click_on = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicator_IV = findViewById(R.id.indicator);
        exit_button_IB = findViewById(R.id.exit_button);

        tab_0_0.imageView = findViewById(R.id.iamge_view_0_0);
        tab_0_1.imageView = findViewById(R.id.iamge_view_0_1);
        tab_0_2.imageView = findViewById(R.id.iamge_view_0_2);
        tab_1_0.imageView = findViewById(R.id.iamge_view_1_0);
        tab_1_1.imageView = findViewById(R.id.iamge_view_1_1);
        tab_1_2.imageView = findViewById(R.id.iamge_view_1_2);
        tab_2_0.imageView = findViewById(R.id.iamge_view_2_0);
        tab_2_1.imageView = findViewById(R.id.iamge_view_2_1);
        tab_2_2.imageView = findViewById(R.id.iamge_view_2_2);

        multiplied_score_TV = findViewById(R.id.multiplied_text);
        circle_score_TV = findViewById(R.id.circle_text);

        //exit button press event
        exit_button_IB.setOnClickListener(v -> {exit_question();});

        reset_button_LL = findViewById(R.id.reset_layout);
        reset_image_view_IV = findViewById(R.id.reset_Imageview);
        rotate_A = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        match_tabs_IV = findViewById(R.id.match_tabs);

        reset_button_LL.setOnClickListener(v -> { reset_game();});

        tab_0_0.imageView.setOnClickListener(v -> {
            if (tab_0_0.mark_status == null && click_on){
                tab_click(tab_0_0);
                step++;
                check_game_status();
            }
        });
        tab_0_1.imageView.setOnClickListener(v -> {
            if (tab_0_1.mark_status == null && click_on){
                tab_click(tab_0_1);
                step++;
                check_game_status();
            }
        });
        tab_0_2.imageView.setOnClickListener(v -> {
            if (tab_0_2.mark_status == null && click_on){
                tab_click(tab_0_2);
                step++;
                check_game_status();
            }
        });
        tab_1_0.imageView.setOnClickListener(v -> {
            if (tab_1_0.mark_status == null && click_on){
                tab_click(tab_1_0);
                step++;
                check_game_status();
            }
        });
        tab_1_1.imageView.setOnClickListener(v -> {
            if (tab_1_1.mark_status == null && click_on){
                tab_click(tab_1_1);
                step++;
                check_game_status();
            }
        });
        tab_1_2.imageView.setOnClickListener(v -> {
            if (tab_1_2.mark_status == null && click_on){
                tab_click(tab_1_2);
                step++;
                check_game_status();
            }
        });
        tab_2_0.imageView.setOnClickListener(v -> {
            if (tab_2_0.mark_status == null && click_on){
                tab_click(tab_2_0);
                step++;
                check_game_status();
            }
        });
        tab_2_1.imageView.setOnClickListener(v -> {
            if (tab_2_1.mark_status == null && click_on){
                tab_click(tab_2_1);
                step++;
                check_game_status();
            }
        });
        tab_2_2.imageView.setOnClickListener(v -> {
            if (tab_2_2.mark_status == null && click_on){
                tab_click(tab_2_2);
                step++;
                check_game_status();
            }
        });
    }
    //change indicator status
    public void  indicator_move (){
        if (Objects.equals(indicator_status_Str, "left")){
            indicator_status_Str = "right";
            indicator_IV.setImageResource(R.drawable.indicator_left_to_right_anim);
            avd = (AnimatedVectorDrawable) indicator_IV.getDrawable();
            avd.start();
        }
        else if (Objects.equals(indicator_status_Str, "right")){
            indicator_status_Str = "left";
            indicator_IV.setImageResource(R.drawable.indicator_right_to_left_anim);
            avd = (AnimatedVectorDrawable) indicator_IV.getDrawable();
            avd.start();
        }
    }

    //exit question dialog definition
    public void exit_question(){
        exit_question_D = new Dialog(MainActivity.this);
        exit_question_D.show();
        exit_question_D.setContentView(R.layout.exit_question_layout);
        exit_question_D.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        no_button_B = exit_question_D.findViewById(R.id.no_button);
        yes_button_B = exit_question_D.findViewById(R.id.yes_button);

        no_button_B.setOnClickListener(v1 -> {
            exit_question_D.cancel();
        });
        yes_button_B.setOnClickListener(v1 -> {
            finishAffinity();
        });
    }
    // back button press event
    @Override
    public void onBackPressed() {
        exit_question();
    }
    public void tab_click(GLtab tab){
        if (Objects.equals(indicator_status_Str, "left")){
            tab.mark_status = "multiplied";
            tab.imageView.setImageResource(R.drawable.multiplied_vector_anim);
            avd = (AnimatedVectorDrawable) tab.imageView.getDrawable();
            avd.start();
        }
        else {
            tab.mark_status = "circle";
            tab.imageView.setImageResource(R.drawable.circle_vector_anim);
            avd = (AnimatedVectorDrawable) tab.imageView.getDrawable();
            avd.start();
        }
    }
    public void check_game_status(){
        if (step > 5 && click_on){
            click_on = false;
            if(tab_0_0.mark_status != null &&  Objects.equals(tab_0_0.mark_status, tab_0_1.mark_status) && Objects.equals(tab_0_0.mark_status, tab_0_2.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_00_01_02);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }
            else if(tab_1_0.mark_status != null && Objects.equals(tab_1_0.mark_status, tab_1_1.mark_status) && Objects.equals(tab_1_0.mark_status, tab_1_2.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_10_11_12);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }
            else if(tab_2_0.mark_status != null && Objects.equals(tab_2_0.mark_status, tab_2_1.mark_status) && Objects.equals(tab_2_0.mark_status, tab_2_2.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_20_21_22);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }

            else if(tab_0_0.mark_status != null && Objects.equals(tab_0_0.mark_status, tab_1_0.mark_status) && Objects.equals(tab_0_0.mark_status, tab_2_0.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_00_10_20);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }
            else if(tab_0_1.mark_status != null && Objects.equals(tab_0_1.mark_status, tab_1_1.mark_status) && Objects.equals(tab_0_1.mark_status, tab_2_1.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_01_11_21);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }
            else if(tab_0_2.mark_status != null && Objects.equals(tab_0_2.mark_status, tab_1_2.mark_status) && Objects.equals(tab_0_2.mark_status, tab_2_2.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_02_12_22);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }

            else if(tab_0_0.mark_status != null && Objects.equals(tab_0_0.mark_status, tab_1_1.mark_status) && Objects.equals(tab_0_0.mark_status, tab_2_2.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_00_11_22);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }
            else if(tab_0_2.mark_status != null && Objects.equals(tab_0_2.mark_status, tab_1_1.mark_status) && Objects.equals(tab_0_2.mark_status, tab_2_0.mark_status)){
                match_tabs_IV.setImageResource(R.drawable.line_02_11_20);
                avd = (AnimatedVectorDrawable) match_tabs_IV.getDrawable();
                avd.start();
                game_result();
            }
            else if (step == 10){
                Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_LONG).show();
                (new Handler()).postDelayed(() -> {
                    step = 1;
                    reset_board();
                    click_on = true;
                }, 400);
            }
            else {
                indicator_move();
                click_on = true;
            }
        }
        else { indicator_move(); }
    }
    public void reset_board(){
        tab_0_0.imageView.setImageDrawable(null);
        tab_0_1.imageView.setImageDrawable(null);
        tab_0_2.imageView.setImageDrawable(null);
        tab_1_0.imageView.setImageDrawable(null);
        tab_1_1.imageView.setImageDrawable(null);
        tab_1_2.imageView.setImageDrawable(null);
        tab_2_0.imageView.setImageDrawable(null);
        tab_2_1.imageView.setImageDrawable(null);
        tab_2_2.imageView.setImageDrawable(null);

        tab_0_0.mark_status = null;
        tab_0_1.mark_status = null;
        tab_0_2.mark_status = null;
        tab_1_0.mark_status = null;
        tab_1_1.mark_status = null;
        tab_1_2.mark_status = null;
        tab_2_0.mark_status = null;
        tab_2_1.mark_status = null;
        tab_2_2.mark_status = null;

        match_tabs_IV.setImageDrawable(null);
    }
    public void game_result(){
        if(Objects.equals(indicator_status_Str, "left")){
            multiplied_score_I++;
            multiplied_score_TV.setText(String.valueOf(multiplied_score_I));
            step = 1;

            (new Handler()).postDelayed(() -> {
                reset_board();
                click_on = true;
            }, 700);
            if(multiplied_score_I == 99 || circle_score_I == 99){
                (new Handler()).postDelayed(() -> {
                    reset_game();
                    click_on = true;
                },700);
            }
        }
        else{
            circle_score_I++;
            circle_score_TV.setText(String.valueOf(circle_score_I));
            step = 1;

            indicator_move();
            (new Handler()).postDelayed(() -> {
                reset_board();
                click_on = true;
            }, 700);
            if(multiplied_score_I == 99 || circle_score_I == 99){
                (new Handler()).postDelayed(() -> {
                    reset_game();
                    click_on = true;
                },700);
            }
        }
    }
    public void reset_game(){
        reset_image_view_IV.startAnimation(rotate_A);
        multiplied_score_I = 0;
        multiplied_score_TV.setText(String.valueOf(multiplied_score_I));
        circle_score_I = 0;
        circle_score_TV.setText(String.valueOf(circle_score_I));
        step = 1;
        if(Objects.equals(indicator_status_Str, "right")){
            indicator_move();
        }
        reset_board();
    }
}