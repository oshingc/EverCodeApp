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

	private static final String DB_NAME = "135.sqlite";
	private static final int DB_SCHEME_VERSION = 1;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_SCHEME_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO);
		db.execSQL(DataBaseManager.CREATE_TABLE_DATOS);
		// db.execSQL(DataBaseManager.CREATE_TABLE_RESPUESTA);
		db.execSQL(DataBaseManager.CREATE_TABLE_TIPO_PREGUNTA);
		db.execSQL(DataBaseManager.CREATE_TABLE_PREGUNTA);
		db.execSQL(DataBaseManager.CREATE_TABLE_COMENTARIO);
		db.execSQL(DataBaseManager.CREATE_TABLE_ALTERNATIVA);
		db.execSQL(DataBaseManager.CREATE_TABLE_MEDALLA);
		db.execSQL(DataBaseManager.CREATE_TABLE_NIVEL);
		db.execSQL(DataBaseManager.CREATE_TABLE_USUARIO_ESTADO);

		/* Tipos de Pregunta */
		db.insert(DataBaseManager.TABLE_TIPO_PREGUNTA, null,
				generarContentValuesTipoPregunta(1, "Fundamentos"));
		db.insert(
				DataBaseManager.TABLE_TIPO_PREGUNTA,
				null,
				generarContentValuesTipoPregunta(2,
						"Valores de Salida"));
		db.insert(DataBaseManager.TABLE_TIPO_PREGUNTA, null,
				generarContentValuesTipoPregunta(3, "Creación de Algoritmos"));

		llenadoPreguntas(db);

	}

	private void llenadoPreguntas(SQLiteDatabase db) {

		/* Pregunta 1 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(1, 1, 1,
						"1. ¿Qué es un algoritmo?"));// Alternativas
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						1,
						1,
						"A. Es un espacio en la memoria de la computadora que permite almacenar "
								+ "temporalmente un dato.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						2,
						1,
						"B. Es una serie de pasos sin importar el orden, describen el proceso "
								+ "que se debe seguir para dar solución a un problema Genérico.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						3,
						1,
						"C. Es una serie de pasos organizados que describe el "
								+ "proceso que se debe seguir para dar solución a un problema "
								+ "específico.", Boolean.TRUE));
		db.insert(DataBaseManager.TABLE_ALTERNATIVA, null,
				generarContentValuesAlternativa(4, 1,
						"D. Es un dato numérico o alfanumérico que no cambia durante la "
								+ "ejecución del programa.", Boolean.FALSE));

		/* Pregunta 2 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(2, 1, 1,
						"2. ¿Qué es un pseudocódigo?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(5, 2,
						"A. Es un lenguaje de programación a nivel máquina.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(6, 2,
						"B. Es un lenguaje de especificación de algoritmos.",
						Boolean.TRUE));// Envío de texto
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(7, 2,
						"C. Es una línea de código.", Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(8, 2,
						"D. Es una sentencia de código.", Boolean.FALSE));

		/* Pregunta 3 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(3, 1, 1,
						"3. ¿Qué es un diagrama de flujo?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(9, 3, "A. Es un algoritmo.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(10, 3,
						"B. Es la representación de un bucle.", Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						11,
						3,
						"C. Es la representación gráfica del algoritmo o proceso.",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(12, 3,
						"D. Es la gráfica de un algoritmo lineal.",
						Boolean.FALSE));

		/* Pregunta 4 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(4, 1, 1,
						"4. ¿Qué es un lenguaje de Programación?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(13, 4,
						"A. Es código maquina realizado por la computadora.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						14,
						4,
						"B. Es la comunicación entre ingenieros dada en un entorno específico.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						15,
						4,
						"C. Lenguaje formal que expresas procesos realizados por las computadoras.",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						16,
						4,
						"D. Es un lenguaje creado para realizar diagramas de flujo de programas.",
						Boolean.FALSE));

		/* Pregunta 5 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(5, 1, 1,
						"5. ¿Qué es una bifurcación?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						17,
						5,
						"A. Es una operación condicional que divide la continuidad "
								+ "del flujo del programa por dos caminos distintos",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(18, 5,
						"B. Es un proceso iterativo.", Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(19, 5,
						"C. Es una operación secuencial.", Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						20,
						5,
						"D. Es un bucle que ejecuta sentencias bajo una determinada condición.",
						Boolean.FALSE));

		/* Pregunta 6 */
		db.insert(DataBaseManager.TABLE_PREGUNTA, null,
				generarContentValuesPregunta(6, 1, 1, "6. ¿Qué es un bucle?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(21, 6,
						"A. Es un proceso iterativo, en el que se "
								+ "repite un conjunto de operaciones, y está "
								+ "gobernado por una operación condicional.",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(22, 6,
						"B. Es una condición falsa.", Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(23, 6,
						"C. Es un conjunto de operaciones en secuencia.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(24, 6,
						"D. Es un conjunto de bifurcaciones.", Boolean.FALSE));

		/* Pregunta 7 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(7, 1, 1,
						"7. ¿Qué es una estructura de datos?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(25, 7,
						"A. Es un programa que me permite hacer algoritmos.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(26, 7,
						"B. Es una colección de datos que permiten organizar"
								+ " y operar con fines determinados.",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						27,
						7,
						"C. Es una serie de números que me permite organizar para realizar distintas tareas.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(28, 7,
						"D. Ninguna de las Anteriores", Boolean.FALSE));

		/* Pregunta 8 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(8, 1, 1,
						"8. ¿Qué se entiende por codificación?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						29,
						8,
						"A. Es plantear un problema y resolver mediante un algoritmos.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						30,
						8,
						"B. Es plasmar nuestro algoritmo en un lenguaje de programación.",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						31,
						8,
						"C. Es plantear la solución de un problema mediante un algoritmo.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(32, 8, "D. A y B.",
						Boolean.FALSE));

		/* Pregunta 9 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(9, 1, 1,
						"9. ¿Qué son estructuras selectivas?"));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(33, 9,
						"A. Son enunciados que tienen dos o más alternativas.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						34,
						9,
						"B. Son proposiciones que nos permiten decidir de uno o dos resultados.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						35,
						9,
						"C. Son respuestas que nos permiten seleccionar de muchas respuestas.",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						36,
						9,
						"D. Son enunciados que nos permiten elegir una respuesta o varias.",
						Boolean.TRUE));
		
		
		/* Pregunta 10 */
		db.insert(
				DataBaseManager.TABLE_PREGUNTA,
				null,
				generarContentValuesPregunta(10, 1, 1,
						"10. La definición de un algoritmo debe describir tres partes: "));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(37, 10,
						"A. Entrada, proceso y salida.",
						Boolean.TRUE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						38,
						10,
						"B. Entrada, programación y salida.    ",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						39,
						10,
						"C. Información, proceso y salida.      ",
						Boolean.FALSE));
		db.insert(
				DataBaseManager.TABLE_ALTERNATIVA,
				null,
				generarContentValuesAlternativa(
						40,
						10,
						"D. Información, programación estructurada y salida.  ",
						Boolean.FALSE));

	}

	public ContentValues generarContentValuesAlternativa(Integer idAlternativa,
			Integer idPregunta, String enunciado, Boolean esRespuesta) {

		ContentValues valores = new ContentValues();
		valores.put(DataBaseManager.ALTERNATIVA_ID, idAlternativa);
		valores.put(DataBaseManager.ALTERNATIVA_ID_PREGUNTA, idPregunta);
		valores.put(DataBaseManager.ALTERNATIVA_ENUNCIADO, enunciado);
		valores.put(DataBaseManager.ALTERNATIVA_ESRESPUESTA, esRespuesta);
		// valores.put(DataBaseManager.ALTERNATIVA_,enunciado);

		return valores;
	}

	public ContentValues generarContentValuesRespuesta(Integer idRespuesta,
			String enunciado) {

		ContentValues valores = new ContentValues();
		valores.put(DataBaseManager.RESPUESTA_ID, idRespuesta);
		valores.put(DataBaseManager.RESPUESTA_ENUNCIADO, enunciado);
		return valores;
	}

	public ContentValues generarContentValuesPregunta(Integer idPregunta,
			Integer idTipoPregunta, Integer idNivel, String enunciado) {

		ContentValues valores = new ContentValues();
		valores.put(DataBaseManager.PREGUNTA_ID, idPregunta);
		valores.put(DataBaseManager.PREGUNTA_ID_TIPO_PREGUNTA, idTipoPregunta);
		valores.put(DataBaseManager.PREGUNTA_ID_NIVEL, idNivel);
		valores.put(DataBaseManager.PREGUNTA_ENUNCIADO, enunciado);
		return valores;
	}

	public ContentValues generarContentValuesTipoPregunta(
			Integer idTipoPregunta, String descripcion) {

		ContentValues valores = new ContentValues();

		valores.put(DataBaseManager.TIPO_PREGUNTA_ID, idTipoPregunta);
		valores.put(DataBaseManager.TIPO_PREGUNTA_DESCRIPCION, descripcion);
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
