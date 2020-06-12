package com.dut.kidoi.ui.envoyer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EnvoyerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EnvoyerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}