package com.deepwares.fishmarketplaceconsumer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * Created by deepaksundar on 8/4/15.
 */
public class BaseDialogFragment extends DialogFragment {

    protected final String TAG = getClass().getName();
    private boolean mSaveInstanceCalled;
    protected final Handler mHandler = new Handler(Looper.getMainLooper());
    protected boolean mIsLandscape;

    /**
     * This is useful in the case where we want to register these types of callbacks on the
     * fragment, but the dialog hasn't yet been created (and sometimes we can't call
     * executePendingTransactions to force its creation).
     */
    public interface ShowHideListener {
        void onDialogFragmentShown();

        void onDialogFragmentCancelled();
    }

    public static class SimpleShowHideListener implements ShowHideListener {
        @Override
        public void onDialogFragmentShown() {
        }

        @Override
        public void onDialogFragmentCancelled() {
        }
    }

    protected ShowHideListener mShowHideListener;

    /**
     * Simple generic ok/cancel type listener. Similar to above
     */
    public interface DialogPositiveNegativeCancelListener {
        void onPositiveClick(String text);

        void onNegativeClick(String text);

        void onCancel();
    }

    protected DialogPositiveNegativeCancelListener mPositiveNegativeCancelListener;

    public void setPositiveNegativeCancelListener(DialogPositiveNegativeCancelListener listener) {
        mPositiveNegativeCancelListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate " + this + " | savedInstanceState : " + savedInstanceState);
        super.onCreate(savedInstanceState);
        setDialogStyle();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog " + this + " | savedInstanceState  : " + savedInstanceState);
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }


    /**
     * Override this to disable a fullscreen dialog, or to set a different style
     */
    protected void setDialogStyle() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenFragment);
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause " + this);
        super.onPause();
        if (mShowHideListener != null) {
            mShowHideListener.onDialogFragmentCancelled();
        }
    }

    @Override
    public void onResume() {
        mSaveInstanceCalled = false;
        Log.i(TAG, "onResume " + this);
        super.onResume();
        if (mShowHideListener != null) {
            getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    View v = getView();
                    if (v != null) {
                        v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mShowHideListener.onDialogFragmentShown();
                    }
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView " + this + " | savedInstanceState : " + savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "onAttach " + this + " | Activity  : " + activity);
        super.onAttach(activity);
    }


}
