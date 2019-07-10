package com.example.stephanv.hellomath.ApplicationClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.lang.reflect.Array;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="hellomath.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_PLAYER = "MsPlayer";
    private static final String COL_PLAYERUSRNAME = "playerUserName";
    private static final String COL_PLAYERGENDER = "playerGender";
    private static final String COL_PLAYERDOB = "playerDOB";
    private static final String COL_PLAYERCOIN = "playerCoin";

    private static final String TABLE_TEMA = "DetailTema";

    SQLiteDatabase mSqLiteDatabase;

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void GetAllPlayer(){
        mSqLiteDatabase = this.getReadableDatabase();

        String que = "Select * from " + TABLE_PLAYER;
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if (cursor.moveToFirst()){
            do {
                int playerID = cursor.getInt(0);
                String playerUsrName = cursor.getString(1);
                String playerGender = cursor.getString(2);
                int playerCoin = cursor.getInt(4);

                Log.e("playerID", String.valueOf(playerID));
                Log.e("playerUsername", playerUsrName);
                Log.e("playerGender", playerGender);
                Log.e("playerCoin", String.valueOf(playerCoin));

            }while(cursor.moveToNext());

        }

        cursor.close();
        mSqLiteDatabase.close();
    }

    public void InsertPlayer(String name, String gender, String DOB ,int coin){
        mSqLiteDatabase = this.getWritableDatabase();

        /* Insert Player MsPlayer */
        ContentValues values = new ContentValues();
        values.put(COL_PLAYERUSRNAME, name);
        values.put(COL_PLAYERGENDER, gender);
        values.put(COL_PLAYERDOB, DOB);
        values.put(COL_PLAYERCOIN, coin);
        mSqLiteDatabase.insert(TABLE_PLAYER, null, values);

        mSqLiteDatabase.close();

        int id = PlayerID(name);
        Log.e("NAME", name);
        Log.e("ID", String.valueOf(id));
        mSqLiteDatabase = this.getWritableDatabase();

        /* Insert Player DetailGame */
        ContentValues values31 = new ContentValues();
        values31.put("gameID", 1);
        values31.put("playerID", id);
        values31.put("Score", 0);
        mSqLiteDatabase.insert("DetailGame", null, values31);

        ContentValues values32 = new ContentValues();
        values32.put("gameID", 2);
        values32.put("playerID", id);
        values32.put("Score", 0);
        mSqLiteDatabase.insert("DetailGame", null, values32);

        ContentValues values33 = new ContentValues();
        values33.put("gameID", 3);
        values33.put("playerID", id);
        values33.put("Score", 0);
        mSqLiteDatabase.insert("DetailGame", null, values33);

        /* Insert Player DetailTema */

        ContentValues values41 = new ContentValues();
        values41.put("playerID", id);
        values41.put("temaID", 1);
        values41.put("status", "used");
        mSqLiteDatabase.insert("DetailTema", null, values41);

        ContentValues values42 = new ContentValues();
        values42.put("playerID", id);
        values42.put("temaID", 2);
        values42.put("status", "buy");
        mSqLiteDatabase.insert("DetailTema", null, values42);

        mSqLiteDatabase.close();

    }

    public int PlayerID(String name){
        int playerID = -1;
        mSqLiteDatabase = this.getReadableDatabase();

        String que = "SELECT playerID FROM " + TABLE_PLAYER + " " + "WHERE playerUserName ='" + name +"'";
        Cursor c = mSqLiteDatabase.rawQuery(que, null);

        if(c.moveToFirst()){
            playerID = c.getInt(0);
        }

        c.close();
        mSqLiteDatabase.close();

        return playerID;
    }

    public String PlayerName(int PlayerID){
        String name;
        mSqLiteDatabase = this.getReadableDatabase();

        String que = "SELECT playerUserName FROM MsPlayer WHERE playerID = '" + PlayerID + "'";
        Cursor c = mSqLiteDatabase.rawQuery(que, null);

        c.moveToFirst();
        name = c.getString(0);
        c.close();

        mSqLiteDatabase.close();

        return name;
    }

    public boolean CheckPlayer(String username){
        boolean flag;
        mSqLiteDatabase = this.getReadableDatabase();

        String queCek = "SELECT playerUserName FROM MsPlayer WHERE playerUserName ='" + username + "'";
        Cursor cursor = mSqLiteDatabase.rawQuery(queCek, null);

        if(cursor.moveToFirst()){
            flag = true;
        }else {
            flag = false;
        }

        cursor.close();

        mSqLiteDatabase.close();
        return flag;
    }

    public int GetCoin(String username){
        int coin = 0;
        mSqLiteDatabase = this.getReadableDatabase();

        String que = "SELECT * FROM " + TABLE_PLAYER + " " + "WHERE playerUserName = '" + username + "'";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if(cursor.moveToFirst()){
            coin = cursor.getInt(4);
        }

        if(cursor!=null){
            cursor.close();
        }

        mSqLiteDatabase.close();
        return coin;  // Not found the coin
    }

    public void InsertCoin(String Username, int ICoin){
        /*
            UPDATE MsPlayer SET playerCoin = '200' WHERE playerID = '7'
            return true     , coin changed
            return false    , coin not changed
         */

        int Coin = GetCoin(Username) + ICoin;
        Log.e("COIN", String.valueOf(GetCoin(Username)));
        int PlayerID = PlayerID(Username);

        mSqLiteDatabase = this.getWritableDatabase();

        String que = "UPDATE MsPlayer SET playerCoin = "+ Coin +" WHERE playerUserName = '" + Username + "' ";

        mSqLiteDatabase.execSQL(que);

        mSqLiteDatabase.close();

    }

    public int GetScore(int GameID, int PlayerID){
        /*
            return Score individually
            return Score by PlayerID, and GameID
         */
        int Score = -1;
        mSqLiteDatabase = this.getWritableDatabase();

        String que = "SELECT Score FROM DetailGame WHERE playerID = '" + PlayerID + "' AND gameID= '" + GameID + "'";
        Cursor c = mSqLiteDatabase.rawQuery(que, null);
        if(c.moveToFirst()){
            Score = c.getInt(0);
        }
        c.close();
        mSqLiteDatabase.close();

        return Score;
    }

    public void InsertScore(int GameID, int PlayerID, int Score){
        mSqLiteDatabase = this.getWritableDatabase();

        String que = "UPDATE DetailGame SET Score = " + Score + " WHERE gameID = " + GameID + " AND playerID = " + PlayerID + " ";

        mSqLiteDatabase.execSQL(que);

        mSqLiteDatabase.close();

    }

    public void CheckScore(int GameID, int PlayerID, int ScoreGame){
        /*
            return true if updated.
            return false if not updated.
         */
        boolean tanda = false;
        int scorenya, flag;
        mSqLiteDatabase = this.getWritableDatabase();

        if(ScoreGame >= GetScore(GameID, PlayerID)){
            scorenya = ScoreGame;
        }else{
            scorenya = GetScore(GameID, PlayerID);
        }

        String que = "UPDATE DetailGame SET Score ='" + scorenya + "' WHERE gameID = '" + GameID + "' AND playerID = '" + PlayerID + "'";
        Cursor kepo = mSqLiteDatabase.rawQuery(que, null);

        kepo.moveToFirst();
        kepo.close();


        mSqLiteDatabase.close();

        return;
    }

    public String[] GetHighScoreName(int GameID){
        /*
            SELECT playerID FROM DetailGame WHERE gameID ='1' ORDER BY Score DESC LIMIT 3
            return top 3 HighScores name
         */
        int idx=0;
        int id[] = new int[5];
        String names[] = new String[5];

        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT playerID FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 3 ";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if (cursor.moveToFirst()){
            do {
                id[idx] = cursor.getInt(0);

                Log.e("idx", String.valueOf(id[idx]));
                idx++;

            }while(cursor.moveToNext());

        }
        cursor.close();

        for(int a=0; a<idx; a++){
            names[a] = PlayerName(id[a]);
            Log.e("Name", names[a]);
        }

        mSqLiteDatabase.close();

        return names;
    }

    public String GetHighScoreName1(int GameID){
        /*
            SELECT playerID FROM DetailGame WHERE gameID ='2' ORDER BY Score DESC LIMIT 1
         */
        String name = "te";
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT playerID FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 1";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        name = PlayerName(ID);

        cursor.close();
        mSqLiteDatabase.close();

        return name;
    }

    public String GetHighScoreName2(int GameID){
        /*
            SELECT playerID FROM DetailGame WHERE gameID ='2' ORDER BY Score DESC LIMIT 1
         */
        String name = "";
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT playerID FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 2";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if(cursor.moveToLast()){
            int ID = cursor.getInt(0);
            name = PlayerName(ID);
        }
        cursor.close();
        mSqLiteDatabase.close();

        return name;
    }

    public String GetHighScoreName3(int GameID){
        /*
            SELECT playerID FROM DetailGame WHERE gameID ='2' ORDER BY Score DESC LIMIT 1
         */
        String name = "";
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT playerID FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 3";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if(cursor.moveToLast()){
            int ID = cursor.getInt(0);
            name = PlayerName(ID);
        }
        cursor.close();
        mSqLiteDatabase.close();

        return name;
    }

    public int[] GetHighScore(int GameID){
        /*
            return 3 int in array score
            SELECT Score FROM DetailGame ORDER BY Score DESC LIMIT 3

         */
        int idx=0;
        int score[] = new int[5];
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT Score FROM DetailGame ORDER BY Score DESC LIMIT 3 WHERE gameID='" + GameID + "'";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if(cursor.moveToFirst()){
            do{
                score[idx] = cursor.getInt(0);
                idx++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        mSqLiteDatabase.close();

        return score;
    }

    public int GetHighScore1(int GameID){
        int flag = -1;
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT Score FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 1";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);
        if(cursor.moveToLast()){
            flag = cursor.getInt(0);
        }
        cursor.close();
        mSqLiteDatabase.close();

        return flag;
    }

    public int GetHighScore2(int GameID){
        int flag = -1;
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT Score FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 2";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);
        if(cursor.moveToLast()){
            flag = cursor.getInt(0);
        }
        cursor.close();
        mSqLiteDatabase.close();

        return flag;
    }

    public int GetHighScore3(int GameID){
        int flag = -1;
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT Score FROM DetailGame WHERE gameID = '" + GameID + "' ORDER BY Score DESC LIMIT 3";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);
        if(cursor.moveToLast()){
            flag = cursor.getInt(0);
        }
        cursor.close();
        mSqLiteDatabase.close();

        return flag;
    }

    public boolean CheckTema2(int PlayerID){
        boolean flag = false;
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT * FROM DetailTema WHERE playerID = '" + PlayerID + "' AND temaID = '2'";
        Cursor c = mSqLiteDatabase.rawQuery(que, null);

        if(c.moveToFirst()){
            String status = c.getString(2);
            Log.e("Status", status);
            if(status.equals("use")){
                flag = true;
            }
        }

        c.close();
        mSqLiteDatabase.close();
        return flag;
    }

    public int GetTheme(int PlayerID){
        /*
            return -1 = ERROR
            return 1 = Theme 1 (Default)
            return 2 = Theme 2
         */
        int Theme = -1;
        mSqLiteDatabase = this.getReadableDatabase();

        String que = "SELECT * FROM DetailTema WHERE playerID ='" + PlayerID + "'";
        Cursor cursor = mSqLiteDatabase.rawQuery(que, null);

        if(cursor.moveToFirst()){
            do{
                int temaID = cursor.getInt(1);
                String status = cursor.getString(2);
                if(status.equals("used")){
                    Theme = temaID;
                    break;
                }
            }while(cursor.moveToNext());
        }

        cursor.close();
        mSqLiteDatabase.close();

        return Theme;
    }

    public void BuyTheme(int PlayerID){
        mSqLiteDatabase = this.getWritableDatabase();
        String que = "UPDATE DetailTema SET status = 'use' WHERE playerID='" + PlayerID + "' AND temaID = '2'";

        Cursor c = mSqLiteDatabase.rawQuery(que, null);
        c.moveToFirst();
        c.close();
        mSqLiteDatabase.close();
    }

    public void SetThemeStatus(int onClick, int PlayerID){
        /*
            status ->
                Belum di beli => buy
                Sudah di beli, belum di pakai => use
                Sudah di beli, sudah di pakai => used

            UPDATE DetailTema SET status = 'use' WHERE playerID = '1' AND temaID = '1'
         */

        if(onClick == 1) {
            mSqLiteDatabase = this.getWritableDatabase();
            String que = "UPDATE DetailTema SET status = 'used' WHERE playerID='" + PlayerID + "' AND temaID = '1'";

            Cursor c = mSqLiteDatabase.rawQuery(que, null);
            c.moveToFirst();
            c.close();
            mSqLiteDatabase.close();

        }

        if(onClick == 2){
            mSqLiteDatabase = this.getWritableDatabase();
            String que1 = "UPDATE DetailTema SET status = 'used' WHERE playerID='" + PlayerID + "' AND temaID = '2'";

            Cursor c = mSqLiteDatabase.rawQuery(que1, null);
            c.moveToFirst();
            c.close();
            mSqLiteDatabase.close();

        }

    }

    public void SetThemeStatusToUse(int onClick, int PlayerID){
        if(onClick == 1) {
            mSqLiteDatabase = this.getWritableDatabase();
            String que3 = "UPDATE DetailTema SET status = 'use' WHERE playerID='" + PlayerID + "' AND temaID = '1'";

            Cursor c = mSqLiteDatabase.rawQuery(que3, null);
            c.moveToFirst();
            c.close();
            mSqLiteDatabase.close();

        }

        if(onClick == 2){
            mSqLiteDatabase = this.getWritableDatabase();
            String que4 = "UPDATE DetailTema SET status = 'use' WHERE playerID='" + PlayerID + "' AND temaID = '2'";

            Cursor c = mSqLiteDatabase.rawQuery(que4, null);
            c.moveToFirst();
            c.close();
            mSqLiteDatabase.close();

        }
    }

    public String GetThemeStatus(int TemaID, int PlayerID){
        /*
            return string of status in database,
            - buy
            - use
            - used
         */
        String flag;
        mSqLiteDatabase = this.getReadableDatabase();
        String que = "SELECT status FROM DetailTema WHERE playerID='" + PlayerID + "' AND  temaID='" + TemaID + "'";

        Cursor c = mSqLiteDatabase.rawQuery(que, null);
        c.moveToFirst();
        flag = c.getString(0);
        c.close();
        mSqLiteDatabase.close();

        return flag;
    }




}
