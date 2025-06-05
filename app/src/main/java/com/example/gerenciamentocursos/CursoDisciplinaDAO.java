package com.example.gerenciamentocursos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class CursoDisciplinaDAO {
    private SQLiteDatabase db;

    public CursoDisciplinaDAO(Context context) {
        BancoHelper helper = new BancoHelper(context);
        db = helper.getWritableDatabase();
    }

    public long relacionarDisciplinaAoCurso(int cursoId, int disciplinaId) {
        ContentValues valores = new ContentValues();
        valores.put("curso_id", cursoId);
        valores.put("disciplina_id", disciplinaId);
        return db.insert("CursoDisciplina", null, valores);
    }

    public boolean verificarRelacionamento(int cursoId, int disciplinaId) {
        Cursor cursor = db.rawQuery(
                "SELECT * FROM CursoDisciplina WHERE curso_id = ? AND disciplina_id = ?",
                new String[]{String.valueOf(cursoId), String.valueOf(disciplinaId)}
        );

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }
}
