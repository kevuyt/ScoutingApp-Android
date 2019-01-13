package archishmaan.com.scoutingapp.Listeners;

import android.view.View;

import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

public class UndoListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        matches.remove(matches.size()-1);
    }
}
