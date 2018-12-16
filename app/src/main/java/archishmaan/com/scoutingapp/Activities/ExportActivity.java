package archishmaan.com.scoutingapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;

import archishmaan.com.scoutingapp.Models.ScoutingModel;
import archishmaan.com.scoutingapp.R;

/**
 * Created by Archishmaan Peyyety on 11/24/18.
 * Project: ScoutingApp
 */
public class ExportActivity extends Fragment implements View.OnClickListener {
    Button exportButton;
    int i = 0;
    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.export_activity, container, false);
        exportButton = view.findViewById(R.id.export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File fileName = new File(i + ".csv");
                FileOutputStream outputStream;

                try {
                    outputStream = getContext().openFileOutput(fileName.toString(), Context.MODE_PRIVATE);
                    for(ScoutingModel model : ScoutingActivity.matches) {
                        outputStream.write((model.getTeamNumber()+",").getBytes());
                        outputStream.write((model.getMatchNumber()+",").getBytes());
                        outputStream.write((model.isAutoDrop()+",").getBytes());
                        outputStream.write((model.isAutoPark()+",").getBytes());
                        outputStream.write((model.isMarker()+",").getBytes());
                        outputStream.write((model.isSample()+",").getBytes());
                        outputStream.write((model.getDepot()+",").getBytes());
                        outputStream.write((model.getLander()+",").getBytes());
                        outputStream.write((model.isEndHang()+",").getBytes());
                        outputStream.write((model.isEndPartial()+",").getBytes());
                        outputStream.write((model.isFullPark()+",").getBytes());
                    }
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
