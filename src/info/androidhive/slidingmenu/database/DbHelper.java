package info.androidhive.slidingmenu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oshin on 05/05/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="db.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Usuario");
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO);
        db.execSQL("drop table if exists Datos");
        db.execSQL(DataBaseManager.CREATE_TABLE_DATOS);
        db.execSQL("drop table if exists Respuesta");
        db.execSQL(DataBaseManager.CREATE_TABLE_RESPUESTA);
        db.execSQL("drop table if exists TipoPregunta");
        db.execSQL(DataBaseManager.CREATE_TABLE_TIPO_PREGUNTA);
        db.execSQL("drop table if exists Pregunta");
        db.execSQL(DataBaseManager.CREATE_TABLE_PREGUNTA);
        db.execSQL("drop table if exists Comentario");
        db.execSQL(DataBaseManager.CREATE_TABLE_COMENTARIO);
        db.execSQL("drop table if exists Alternativa");
        db.execSQL(DataBaseManager.CREATE_TABLE_ALTERNATIVA);
        db.execSQL("drop table if exists Medalla");
        db.execSQL(DataBaseManager.CREATE_TABLE_MEDALLA);
        db.execSQL("drop table if exists Nivel");
        db.execSQL(DataBaseManager.CREATE_TABLE_NIVEL);
        db.execSQL("drop table if exists UsuarioEstado");
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO_ESTADO);
    }
}
