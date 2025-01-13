package com.rygodc.palabrasroom;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraViewModel extends AndroidViewModel {
    private PalabraRepository mPalabraRepository;
    private LiveData<List<Palabra>> mPalabras;

    public PalabraViewModel(Application application) {
        super(application);
        mPalabraRepository = new PalabraRepository(application);
        mPalabras = mPalabraRepository.getAllPalabras();
    }

    LiveData<List<Palabra>> getPalabras() {
        return mPalabras;
    }

    public void insert(Palabra palabra) {
        mPalabraRepository.insert(palabra);
    }

    public void deleteAll() {
        mPalabraRepository.deleteAll();
    }

    public void delete(Palabra palabra) {
        mPalabraRepository.delete(palabra);
    }

}