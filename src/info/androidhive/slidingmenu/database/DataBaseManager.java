package info.androidhive.slidingmenu.database;

import info.androidhive.slidingmenu.model.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by oshin on 05/05/15.
 */
public class DataBaseManager {

    /*Table Usuario*/
    public static final String TABLE_USUARIO = "Usuario";
    public static final String USUARIO_ID = "idUsuario";
    public static final String USUARIO_USER = "username";
    public static final String USUARIO_PASS = "password";
    public static final String USUARIO_ACTIVO = "activo";

    /*Table Datos*/
    public static final String TABLE_DATOS = "Datos";
    public static final String DATOS_ID = "idDatos";
    public static final String DATOS_ID_USUARIO = "idUsuario";
    public static final String DATOS_NOMBRES = "nombres";
    public static final String DATOS_APELLIDOP = "apellidoPaterno";
    public static final String DATOS_APELLIDOM = "apellidoMaterno";
    public static final String DATOS_FECHANAC = "fechaNacimiento";


    /*Table Nivel*/
    public static final String TABLE_NIVEL = "Nivel";
    public static final String NIVEL_ID = "idNivel";
    public static final String NIVEL_NIVEL = "nivel";

    /*Table Respuesta*/
    public static final String TABLE_RESPUESTA = "Respuesta";
    public static final String RESPUESTA_ID = "idRespuesta";
    public static final String RESPUESTA_ENUNCIADO = "enunciado";


    /*Table TipoPregunta*/
    public static final String TABLE_TIPO_PREGUNTA = "TipoPregunta";
    public static final String TIPO_PREGUNTA_ID = "idTipoPregunta";
    public static final String TIPO_PREGUNTA_DESCRIPCION = "descripcion";


    /*Table Pregunta*/
    public static final String TABLE_PREGUNTA = "Pregunta";
    public static final String PREGUNTA_ID = "idPregunta";
    public static final String PREGUNTA_ID_TIPO_PREGUNTA = "idPregunta";
    public static final String PREGUNTA_ID_RESPUESTA="idRespuesta";
    public static final String PREGUNTA_ENUNCIADO= "descripcion";


    /*Table Alternativa*/
    public static final String TABLE_ALTERNATIVA = "Alternativa";
    public static final String ALTERNATIVA_ID = "idAlternativa";
    public static final String ALTERNATIVA_ID_PREGUNTA = "idPregunta";
    public static final String ALTERNATIVA_ENUNCIADO= "enunciado";

    /*Table Comentario*/
    public static final String TABLE_COMENTARIO = "Comentario";
    public static final String COMENTARIO_ID = "idComentario";
    public static final String COMENTARIO_ID_PREGUNTA = "idPregunta";
    public static final String COMENTARIO_CONTENIDO= "contenido";


    /*Table Medalla*/
    public static final String TABLE_MEDALLA = "Medalla";
    public static final String MEDALLA_ID = "idMedalla";
    public static final String MEDALLA_MEDALLA_IMAGEN = "medallaImagen";
    public static final String MEDALLA_NOMBRE= "nombre";


    /*Table UsuarioEstado*/
    public static final String TABLE_USUARIO_ESTADO = "UsuarioEstado";
    public static final String USUARIO_ESTADO_ID = "idUsuarioEstado";
    public static final String USUARIO_ESTADO_ID_USUARIO = "idUsuario";
    public static final String USUARIO_ESTADO_ID_PREGUNTA = "idPregunta";
    public static final String USUARIO_ESTADO_ID_NIVEL = "idNivel";
    public static final String USUARIO_ESTADO_ID_MEDALLA = "idMedalla";
    public static final String USUARIO_ESTADO_PUNTAJE= "puntaje";



    /*Creación de Tablas*/

    public static final String CREATE_TABLE_USUARIO = "" +
            "create table "+TABLE_USUARIO +"( "+
            USUARIO_ID+" integer primary key autoincrement, " +
            USUARIO_USER +" text not null, "+
            USUARIO_PASS + " text, "+
            USUARIO_ACTIVO + " boolean );";

    public static final String CREATE_TABLE_DATOS = "" +
            "create table "+TABLE_DATOS +"( "+
            DATOS_ID+" integer primary key autoincrement, " +
            DATOS_ID_USUARIO +" integer not null, "+
            DATOS_NOMBRES   + " text, "+
            DATOS_APELLIDOP + " text, "+
            DATOS_APELLIDOM + " text, "+
            DATOS_FECHANAC  + " date, " +
            " foreign key ("+DATOS_ID_USUARIO+") references "+TABLE_USUARIO+"("+USUARIO_ID+"));";

    public static final String CREATE_TABLE_NIVEL = "" +
            "create table "+TABLE_NIVEL +"( "+
            NIVEL_ID+" integer primary key autoincrement, " +
            NIVEL_NIVEL +" text not null);";

    public static final String CREATE_TABLE_TIPO_PREGUNTA = "" +
            "create table "+TABLE_TIPO_PREGUNTA +"( "+
            TIPO_PREGUNTA_ID+" integer primary key autoincrement, " +
            TIPO_PREGUNTA_DESCRIPCION +" text not null);";

    public static final String CREATE_TABLE_RESPUESTA = "" +
            "create table "+TABLE_RESPUESTA +"( "+
            RESPUESTA_ID+" integer primary key autoincrement, " +
            RESPUESTA_ENUNCIADO +" text not null);";

    public static final String CREATE_TABLE_MEDALLA = "" +
            "create table "+TABLE_MEDALLA +"( "+
            MEDALLA_ID+" integer primary key autoincrement, " +
            MEDALLA_MEDALLA_IMAGEN +" text," +
            MEDALLA_NOMBRE+"text );";

    public static final String CREATE_TABLE_PREGUNTA = "" +
            "create table "+TABLE_PREGUNTA +"( "+
            PREGUNTA_ID+" integer primary key autoincrement, " +
            PREGUNTA_ID_TIPO_PREGUNTA+" integer,"+
            PREGUNTA_ID_RESPUESTA+" integer,"+
            PREGUNTA_ENUNCIADO +" text not null"+
            " foreign key ("+PREGUNTA_ID_TIPO_PREGUNTA+") references "+TABLE_TIPO_PREGUNTA+"("+TIPO_PREGUNTA_ID+"),"+
            " foreign key ("+PREGUNTA_ID_RESPUESTA+") references "+TABLE_RESPUESTA+"("+RESPUESTA_ID+"));";


    public static final String CREATE_TABLE_ALTERNATIVA = "" +
            "create table "+TABLE_ALTERNATIVA +"( "+
            ALTERNATIVA_ID+" integer primary key autoincrement, " +
            ALTERNATIVA_ID_PREGUNTA+" integer not null, "+
            ALTERNATIVA_ENUNCIADO +" text not null,"+
            " foreign key ("+ALTERNATIVA_ID_PREGUNTA+") references "+TABLE_PREGUNTA+"("+PREGUNTA_ID+"));";


    public static final String CREATE_TABLE_COMENTARIO = "" +
            "create table "+TABLE_COMENTARIO +"( "+
            COMENTARIO_ID+" integer primary key autoincrement, " +
            COMENTARIO_ID_PREGUNTA+" integer not null, "+
            COMENTARIO_CONTENIDO +" text not null, "+
            " foreign key ("+COMENTARIO_ID_PREGUNTA+") references "+TABLE_PREGUNTA+"("+PREGUNTA_ID+"));";


    public static final String CREATE_TABLE_USUARIO_ESTADO = "" +
            "create table "+TABLE_USUARIO_ESTADO +"( "+
            USUARIO_ESTADO_ID+" integer primary key autoincrement, " +
            USUARIO_ESTADO_ID_USUARIO+" integer not null, "+
            USUARIO_ESTADO_ID_PREGUNTA+" integer not null, "+
            USUARIO_ESTADO_ID_NIVEL+" integer not null, "+
            USUARIO_ESTADO_ID_MEDALLA+" integer, "+
            USUARIO_ESTADO_PUNTAJE +" integer, "+
            " foreign key ("+USUARIO_ESTADO_ID_USUARIO+") references "+TABLE_USUARIO+"("+USUARIO_ID+"), "+
            " foreign key ("+USUARIO_ESTADO_ID_PREGUNTA+") references "+TABLE_PREGUNTA+"("+PREGUNTA_ID+"), " +
            " foreign key ("+USUARIO_ESTADO_ID_NIVEL+") references "+TABLE_NIVEL+"("+NIVEL_ID+"), " +
            " foreign key ("+USUARIO_ESTADO_ID_MEDALLA+") references "+TABLE_MEDALLA+" ("+MEDALLA_ID+"));";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context contexto){
        helper = new DbHelper(contexto);
        db = helper.getWritableDatabase();

    }

    public ContentValues generarContentValues(String usuario, String password){

        ContentValues valores = new ContentValues();
        valores.put(USUARIO_USER,usuario);
        valores.put(USUARIO_PASS,password);
        return valores;
    }

    public void addUsuario(String usuario, String password){

        /*ContentValues valores = new ContentValues();
        valores.put(USUARIO_USER,usuario);
        valores.put(USUARIO_PASS,password);*/
    	

        db.insert(TABLE_USUARIO,null,generarContentValues(usuario,password));

    }

    public Cursor getUsuarios(){

        /*ContentValues valores = new ContentValues();
        valores.put(USUARIO_USER,usuario);
        valores.put(USUARIO_PASS,password);*/
        String[] columnas = new String[]{USUARIO_ID, USUARIO_USER, USUARIO_PASS};

        return db.query(TABLE_USUARIO,columnas,null,null,null,null,null);


    }

    public Cursor findUsuario(String username, String password){

        /*ContentValues valores = new ContentValues();
        valores.put(USUARIO_USER,usuario);
        valores.put(USUARIO_PASS,password);*/
        String[] columnas = new String[]{USUARIO_ID, USUARIO_USER, USUARIO_PASS};

        return db.query(TABLE_USUARIO,columnas,USUARIO_USER+"=?",new String[]{username},null,null,null);


    }

    public Usuario getUsuario (String username, String password){
        try {
            String query = "Select idUsuario, username, password from Usuario where username ='" + username + "' and password = '" + password + "'";
            Usuario myUser = new Usuario();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                	myUser.setUserId(cursor.getInt(0));
                    myUser.setUsername(cursor.getString(1));
                    myUser.setPassword(cursor.getString(2));
                } while (cursor.moveToNext());
            }

            Log.d("getUsuario", "myuser");

            return myUser;
        }catch (Exception e){
            Log.d("getUsuario", "excepcion");

            e.printStackTrace();
        }
        return null;
    }


}
