package com.aiteu.training.main;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseApplication;
import com.aiteu.training.base.Callback;
import com.aiteu.training.main.biz.TeachItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainDataSources {

    private static MainDataSources sInstance = null;

    private List<TeachItem> mTeachItems;

    public static MainDataSources create(){
        synchronized (MainDataSources.class) {
            if(sInstance == null) {
                sInstance = new MainDataSources();
            }
            return sInstance;
        }
    }

    private MainDataSources (){
        mTeachItems = new ArrayList<>(0);
    }

    public void load(final Callback callback){
        mTeachItems.clear();
        Observable.create(new ObservableOnSubscribe<List<TeachItem>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<TeachItem>> emitter) throws Exception {
                parseXml();
                emitter.onNext(mTeachItems);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<TeachItem>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<TeachItem> teachItems) {
                if(callback != null) {
                    callback.onSuccess(teachItems);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if(callback != null) {
                    callback.onError();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void parseXml() throws IOException, XmlPullParserException {
        Context context = BaseApplication.getApp();
        XmlResourceParser xml = context.getResources().getXml(R.xml.teach_list);
        xml.next();
        int eventType = xml.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                String tagName = xml.getName();
                if (tagName.equals("item")) {
                    TeachItem item = new TeachItem();
                    item.title = xml.getAttributeValue(0);
                    item.className = xml.getAttributeValue(1);
                    mTeachItems.add(item);
                }
            }
            xml.next();
            eventType = xml.getEventType();
        }
    }
}
