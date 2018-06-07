package sysshare.lq.com.penjingwang.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;
import sysshare.lq.com.penjingwang.event.RxEvent;

public abstract class BaseFragment extends Fragment {


    private RxEvent mRxEvent;
    private PublishSubject mSubject;
    private ReceiveEvent mDisposableObserver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            getBundle(bundle);
        }

        mRxEvent = RxEvent.getInstance();
        mDisposableObserver = new ReceiveEvent();
        mSubject = mRxEvent.registerEvent(registerEvent());
        mSubject.subscribe(mDisposableObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
            initViews(view);
        }

        return view;
    }

    protected abstract void initViews(View view);

    protected abstract void getBundle(Bundle bundle);

    protected abstract int getLayoutId();

    private class ReceiveEvent extends DisposableObserver{

        @Override
        public void onNext(Object o) {
            receiveEvent(o);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    protected abstract String registerEvent();
    protected abstract void receiveEvent(Object o);


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxEvent.getInstance().unRegisterEvent(registerEvent(), mSubject, mDisposableObserver);
    }
}
