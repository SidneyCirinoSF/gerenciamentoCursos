package com.example.gerenciamentocursos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class BancoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "universidade.db";
    private static final int DATABASE_VERSION = 1;

    public BancoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE Curso (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "codigo TEXT NOT NULL, " +
                "tempo_formacao INTEGER NOT NULL)");

        // Tabela Disciplina
        db.execSQL("CREATE TABLE Disciplina (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "codigo TEXT NOT NULL)");

        // Tabela de relação
        db.execSQL("CREATE TABLE CursoDisciplina (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "curso_id INTEGER NOT NULL, " +
                "disciplina_id INTEGER NOT NULL, " +
                "FOREIGN KEY (curso_id) REFERENCES Curso(id), " +
                "FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CursoDisciplina");
        db.execSQL("DROP TABLE IF EXISTS Disciplina");
        db.execSQL("DROP TABLE IF EXISTS Curso");
        onCreate(db);

    }
}
