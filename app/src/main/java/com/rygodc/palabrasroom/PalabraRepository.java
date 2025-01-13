package com.rygodc.palabrasroom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraRepository {
    private final PalabraDAO mPalabraDAO;
    private final LiveData<List<Palabra>> mPalabras;

    PalabraRepository(Application application) {
        PalabraDB db = PalabraDB.getDatabase(application);
        mPalabraDAO = db.palabraDAO();
        mPalabras = mPalabraDAO.getPalabrasOrdenadas();
    }

    LiveData<List<Palabra>> getAllPalabras() {
        return mPalabras;
    }

    void insert(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.insert(palabra);
        });
    }
    void delete(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.delete(palabra);
        });
    }

    void deleteAll() {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.deleteAll();
        });
    }

}
