package com.example.myapplication.Entity;

import com.example.myapplication.R;

public class Ranking {
    String purposeName;
    int score;

    public Ranking(String purposeName, int score) {
        this.purposeName = purposeName;
        this.score = score;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public int getScore() {
        return score;
    }
    public int getScoreColor(int score)
    {
        float ratio = (float) score/this.score;
        if(ratio >=1.5)
        {
            return R.color.r_green;
        }
        else if(ratio >=1.25f)
        {
            return R.color.r_greenYellow;
        }
        else if(ratio >=1f)
        {
            return R.color.r_yellow;
        }
        else if(ratio >=0.75f)
        {
            return R.color.r_orange;
        }
        else
        {
            return R.color.r_red;
        }
    }

    public String getScoreText(int score)
    {
        float ratio = (float) score/this.score;
        if(ratio >=1.5)
        {
            return "outstanding";
        }
        else if(ratio >=1.25f)
        {
            return "good";
        }
        else if(ratio >=1f)
        {
            return "standard";
        }
        else if(ratio >=0.75f)
        {
            return "acceptable";
        }
        else
        {
            return "underperforming";
        }
    }
}
