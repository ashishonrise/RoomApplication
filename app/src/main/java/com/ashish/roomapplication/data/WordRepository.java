package com.ashish.roomapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ashish.roomapplication.model.Word;
import com.ashish.roomapplication.model.WordDao;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        WordRoomDatabase wordRoomDatabase = WordRoomDatabase.getDatabase(application);
        mWordDao = wordRoomDatabase.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

   public void insert(Word word){
        WordRoomDatabase.dataBaseExecutor.execute(()->{
            mWordDao.insert(word);
        });
    }
}
