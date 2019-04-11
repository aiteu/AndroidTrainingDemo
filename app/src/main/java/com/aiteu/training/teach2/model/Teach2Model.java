package com.aiteu.training.teach2.model;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseApplication;
import com.aiteu.training.base.Callback;
import com.aiteu.training.teach2.biz.Image;
import com.aiteu.training.teach2.biz.UserItem;
import com.aiteu.training.utils.Opts;

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

public class Teach2Model {
    private static Teach2Model sInstance = null;

    private List<UserItem> mStarList;

    public static Teach2Model create(){
        synchronized (Teach2Model.class) {
            if(sInstance == null) {
                sInstance = new Teach2Model();
            }
            return sInstance;
        }
    }

    private Teach2Model (){
        mStarList = new ArrayList<>(0);
    }

    public List getStarList(){
        return mStarList;
    }

    public void load(final Callback callback){
        mStarList.clear();
        Observable.create(new ObservableOnSubscribe<List<UserItem>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<UserItem>> emitter) throws Exception {
                parseXml();
                emitter.onNext(mStarList);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<UserItem>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<UserItem> teachItems) {
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
        XmlResourceParser xml = context.getResources().getXml(R.xml.stars);
        xml.next();
        int eventType = xml.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                String tagName = xml.getName();
                if (tagName.equals("star")) {
                    UserItem item = new UserItem();
                    item.name = xml.getAttributeValue(null, "name");
                    item.image = new Image();
                    item.image.url = xml.getAttributeValue(null, "image_url");
                    item.image.w = Opts.optInt(xml.getAttributeValue(null, "width"));
                    item.image.h = Opts.optInt(xml.getAttributeValue(null, "height"));
                    mStarList.add(item);
                }
            }
            xml.next();
            eventType = xml.getEventType();
        }
    }
}
