package com.example.gerenciamentocursos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
public class CursoDAO {

    private SQLiteDatabase db;

    public CursoDAO(Context context) {
        BancoHelper helper = new BancoHelper(context);
        db = helper.getWritableDatabase();
    }

    public long inserirCurso(String nome, String codigo, int tempoFormacao) {
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("codigo", codigo);
        valores.put("tempo_formacao", tempoFormacao);
        return db.insert("Curso", null, valores);
    }

    public ArrayList<String> listarNomesCursos() {
        ArrayList<String> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT nome FROM Curso", null);
        while (cursor.moveToNext()) {
            lista.add(cursor.getString(0));
        }
        cursor.close();
        return lista;
    }

    public int obterIdPorNome(String nome) {
        Cursor cursor = db.rawQuery("SELECT id FROM Curso WHERE nome = ?", new String[]{nome});
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        return -1;
    }
}
