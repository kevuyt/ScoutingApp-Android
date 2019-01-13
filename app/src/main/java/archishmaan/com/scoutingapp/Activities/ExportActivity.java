package archishmaan.com.scoutingapp.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import static archishmaan.com.scoutingapp.Activities.DataActivity.filePath;

public class ExportActivity extends Fragment {
    ScrollView scrollView;
    TextView directory;
    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scrollView = new ScrollView(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
        directory = new TextView(getContext());
        directory.setWidth(25);
        directory.setHeight(50);
        directory.setText(filePath.get(0));
        linearLayout.addView(directory);
        return  scrollView;
    }
}
