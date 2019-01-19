package archishmaan.com.scoutingappv2.Listeners;

import android.view.View;

import static archishmaan.com.scoutingappv2.Activities.ScoutingActivity.matches;

public class UndoListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        matches.remove(matches.size()-1);
    }
}
