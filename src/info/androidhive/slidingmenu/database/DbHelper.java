package info.androidhive.slidingmenu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.AlteredCharSequence;

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
        db.execSQL(DataBaseManager.CREATE_TABLE_DATOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_RESPUESTA);
        db.execSQL(DataBaseManager.CREATE_TABLE_TIPO_PREGUNTA);
        db.execSQL(DataBaseManager.CREATE_TABLE_PREGUNTA);
        db.execSQL(DataBaseManager.CREATE_TABLE_COMENTARIO);
        db.execSQL(DataBaseManager.CREATE_TABLE_ALTERNATIVA);
        db.execSQL(DataBaseManager.CREATE_TABLE_MEDALLA);
        db.execSQL(DataBaseManager.CREATE_TABLE_NIVEL);
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO_ESTADO);    
        //insert if not exists
        //buscar una manera de solo inertar una vez llenar db una vez
        db.insert(DataBaseManager.TABLE_TIPO_PREGUNTA,null,generarContentValuesTipoPregunta("Pregunta de Fundamentos"));//Alternativas
        db.insert(DataBaseManager.TABLE_TIPO_PREGUNTA,null,generarContentValuesTipoPregunta("Pregunta de Valores de Salida"));//Alternativas
        db.insert(DataBaseManager.TABLE_TIPO_PREGUNTA,null,generarContentValuesTipoPregunta("Preguntas de Algoritmos"));//Envío de texto
        
        db.insert(DataBaseManager.TABLE_RESPUESTA,null,generarContentValuesRespuesta(1,"Conjunto de pasos ordenados"));//Envío de texto
        db.insert(DataBaseManager.TABLE_RESPUESTA,null,generarContentValuesRespuesta(2,"Es un un diagrama que sirve para mapear los pasos a seguir en un algoritmo"));//Envío de texto
        //db.insert(DataBaseManager.TABLE_RESPUESTA,null,generarContentValuesRespuesta("Preguntas de Algoritmos"));//Envío de texto
        //db.insert(DataBaseManager.TABLE_RESPUESTA,null,generarContentValuesRespuesta("Preguntas de Algoritmos"));//Envío de texto

        db.insert(DataBaseManager.TABLE_ALTERNATIVA,null,generarContentValuesAlternativa(1,1,"Es una actividad compleja"));//Envío de texto
        
        
        db.insert(DataBaseManager.TABLE_PREGUNTA,null,generarContentValuesPregunta(1,1,"¿Qué es un algoritmo?"));//Alternativas
        db.insert(DataBaseManager.TABLE_PREGUNTA,null,generarContentValuesPregunta(1,2,"¿Qué es un diagrama de flujo?"));//Alternativas
       
    }
    
    public ContentValues generarContentValuesAlternativa(Integer idAlternativa, Integer idPregunta, String enunciado){

        ContentValues valores = new ContentValues();
        valores.put(DataBaseManager.ALTERNATIVA_ID,idAlternativa);
        valores.put(DataBaseManager.ALTERNATIVA_ID_PREGUNTA,enunciado);
        valores.put(DataBaseManager.ALTERNATIVA_ENUNCIADO,enunciado);
        //valores.put(DataBaseManager.ALTERNATIVA_,enunciado);
        
        
        return valores;
    }
    
    public ContentValues generarContentValuesRespuesta(Integer idRespuesta, String enunciado){

        ContentValues valores = new ContentValues();
        valores.put(DataBaseManager.RESPUESTA_ID,idRespuesta);
        valores.put(DataBaseManager.RESPUESTA_ENUNCIADO,enunciado);
        return valores;
    }
    
    public ContentValues generarContentValuesPregunta(Integer idTipoPregunta, Integer idRespuesta, String enunciado){

        ContentValues valores = new ContentValues();
        valores.put(DataBaseManager.PREGUNTA_ID_TIPO_PREGUNTA,idTipoPregunta);
        valores.put(DataBaseManager.PREGUNTA_ID_TIPO_PREGUNTA,idRespuesta);
        valores.put(DataBaseManager.PREGUNTA_ID_TIPO_PREGUNTA,enunciado);
        return valores;
    }
    
    public ContentValues generarContentValuesTipoPregunta(String descripcion){

        ContentValues valores = new ContentValues();
        valores.put(DataBaseManager.TIPO_PREGUNTA_DESCRIPCION,descripcion);
        return valores;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table if not exists Usuario");
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO);
        db.execSQL("create table if not exists Datos");
        db.execSQL(DataBaseManager.CREATE_TABLE_DATOS);
        db.execSQL("create table if not exists Respuesta");
        db.execSQL(DataBaseManager.CREATE_TABLE_RESPUESTA);
        db.execSQL("create table if not exists TipoPregunta");
        db.execSQL(DataBaseManager.CREATE_TABLE_TIPO_PREGUNTA);
        db.execSQL("create table if not exists Pregunta");
        db.execSQL(DataBaseManager.CREATE_TABLE_PREGUNTA);
        db.execSQL("create table if not exists Comentario");
        db.execSQL(DataBaseManager.CREATE_TABLE_COMENTARIO);
        db.execSQL("create table if not exists Alternativa");
        db.execSQL(DataBaseManager.CREATE_TABLE_ALTERNATIVA);
        db.execSQL("create table if not exists Medalla");
        db.execSQL(DataBaseManager.CREATE_TABLE_MEDALLA);
        db.execSQL("create table if not exists Nivel");
        db.execSQL(DataBaseManager.CREATE_TABLE_NIVEL);
        db.execSQL("create table if not exists UsuarioEstado");
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO_ESTADO);
    }
}
