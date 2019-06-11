package com.example.task32;

import android.os.AsyncTask;

public class AsynTask extends AsyncTask<MainActivity,Void, Void>
{
   private final static String[] random_name={"Васильев Василий Васильевич","Гудков Анатолий Ильич","Путин Владимир Владимирович","Песнячевкская Полина Николаевна","Коватьев Иван Михайлович"};

    public static String[] getRandom_name() {
        return random_name;
    }

    @Override
    protected Void doInBackground(MainActivity... splasches) {

        MainActivity m=splasches[0];
        AppDatabase db= AppDatabase.getDatabace(m);
        m.CallMainAndDie();
        return null;
    }

    @Override
    protected void onPostExecute(Void v)
    {

    }
}
