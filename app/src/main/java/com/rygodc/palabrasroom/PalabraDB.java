package com.rygodc.palabrasroom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Palabra.class}, version = 1, exportSchema = false)
public abstract class PalabraDB extends RoomDatabase {
    public abstract PalabraDAO palabraDAO();
    private static volatile PalabraDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                PalabraDAO dao = INSTANCE.palabraDAO();
                dao.deleteAll();

                Palabra palabra = new Palabra("Hello");
                dao.insert(palabra);
                palabra = new Palabra("World");
                dao.insert(palabra);
            });
        }
    };

    static PalabraDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PalabraDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PalabraDB.class, "palabras_db").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

}
