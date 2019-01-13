package archishmaan.com.scoutingapp.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;
import static archishmaan.com.scoutingapp.Activities.ScoutingActivity.matches;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
@SuppressWarnings("SpellCheckingInspection")
public class DataActivity extends Fragment implements View.OnClickListener {
    ScrollView scrollView;
    int score;
    int requestValue = 0;
    LinearLayout linearLayout;
    static List<ScoutingModel> updateMatch = new ArrayList<>();
    static List<String> filePath = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scrollView = new ScrollView(getContext());
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        for (ScoutingModel match : matches) {
            score = 0;
            Button button = new Button(getContext());
            if (match.isAutoDrop()) {score +=30;}
            if (match.isSample()) {score+=25;}
            if (match.isDoubleSample()) {score+=50;}
            if (match.isMarker()) {score+=15;}
            if (match.isAutoPark()) {score+=10;}
            if (match.getDepot()>0) {score+=match.getDepot()*2;}
            if (match.getLander()>0) {score+=match.getLander()*5;}
            if (match.isEndHang()) {score+=50;}
            if (match.isEndPartial()) {score+=15;}
            if (match.isFullPark()) {score+=25;}
            button.setText ("Match #: " + match.getMatchNumber() + ", Team #: " + match.getTeamNumber() + ", Score: " + score++);
            button.setId(match.getMatchNumber());
            button.setTextSize(15);
            button.setWidth(1000);
            button.setHeight(100);
            button.getBackground().setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.DARKEN);
            linearLayout.addView(button);
            final ScoutingModel updateMatchEntry = match;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateMatch.add(updateMatchEntry);
                    assert getFragmentManager() != null;
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new EditActivity())
                            .commit();
                }
            });
        }
        Button export = new Button(getContext());
        export.setText("Export");
        export.setTextSize(25);
        export.setWidth(1000);
        export.setHeight(125);
        export.getBackground().setColorFilter(Color.parseColor("#DAA620"), PorterDuff.Mode.DARKEN);
        linearLayout.addView(export);
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String filename = "Scouting Data " + matches.get(0).getTournament() + ".csv";

                    File directoryDocument = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    File file = new File(directoryDocument, filename);
                    file.setReadable(true);
                    file.setExecutable(true);
                    file.setWritable(true);
                    if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestValue);
                    }
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, requestValue + 1);
                    }
                        FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(("Tournament Name, Match Number, Team Number, Auto Drop, Sample, Double Sample, Marker, Auto Park, Depot, Lander, End Hang, End Partial Park, End Full Park" + System.lineSeparator()).getBytes());
                        for (ScoutingModel match : matches) {
                        outputStream.write((match.getTournament() +", ").getBytes());
                        outputStream.write((match.getMatchNumber()+", ").getBytes());
                        outputStream.write((match.getTeamNumber()+", ").getBytes());
                        outputStream.write((match.isAutoDrop()+", ").getBytes());
                        outputStream.write((match.isSample()+", ").getBytes());
                        outputStream.write((match.isDoubleSample()+", ").getBytes());
                        outputStream.write((match.isMarker()+", ").getBytes());
                        outputStream.write((match.isAutoPark()+", ").getBytes());
                        outputStream.write((match.getDepot()+", ").getBytes());
                        outputStream.write((match.getLander()+", ").getBytes());
                        outputStream.write((match.isEndHang()+", ").getBytes());
                        outputStream.write((match.isEndPartial()+", ").getBytes());
                        outputStream.write((match.isFullPark()+ System.lineSeparator()).getBytes());
                        }
                        String path = file.getAbsolutePath();
                        filePath.add(path);
                        outputStream.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                requestValue+=2;
            }
        });

        return scrollView;
    }

    @Override
    public void onClick(View view) {


    }

}
