package com.aiteu.training.teach4;

import android.content.Context;
import android.os.AsyncTask;

import com.aiteu.training.teach4.model.PhotoUtils;

import java.util.List;

public class LoadPhotoTask extends AsyncTask<Void, Void, List> {

    private Context context;

    public LoadPhotoTask(Context context) {
        this.context = context;
    }

    @Override
    protected List doInBackground(Void... voids) {
        return PhotoUtils.getAllPhotos(context);
    }
}
