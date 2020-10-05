package com.jobeanda.miproyecto.ui.acerca;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AcercaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AcercaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esto es un fragmento de Acerca de...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}