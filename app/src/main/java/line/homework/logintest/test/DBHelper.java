package line.homework.logintest.test;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * 클래스 이름 : DBHelper
 *
 * 버전 정보
 *
 * 날짜 : 2018.01.06
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE LOGININFO (_id INTEGER PRIMARY KEY AUTOINCREMENT, userId TEXT, userName TEXT, pictureUrl TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void insert(String userId, String userName, String pictureUrl) {

        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO LOGININFO VALUES(null, '" + userId + "', '" + userName +"', '" + pictureUrl  +"');");
        db.close();
    }

    public String[] checkLogin() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LOGININFO", null);
        String[] dbInfo = new String[3];
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        while(cursor.moveToNext()){
            dbInfo[0]=cursor.getString(1);//userId
            dbInfo[1]=cursor.getString(2);//userName
            dbInfo[2]=cursor.getString(3);//pictureUrl
        }

        return dbInfo;
    }
}

