package com.example.gerenciamentocursos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
public class DisciplinaDAO {
    private SQLiteDatabase db;

    public DisciplinaDAO(Context context) {
        BancoHelper helper = new BancoHelper(context);
        db = helper.getWritableDatabase();
    }

    public long inserirDisciplina(String nome, String codigo) {
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("codigo", codigo);
        return db.insert("Disciplina", null, valores);
    }

    public ArrayList<String> listarNomesDisciplinas() {
        ArrayList<String> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT nome FROM Disciplina", null);
        while (cursor.moveToNext()) {
            lista.add(cursor.getString(0));
        }
        cursor.close();
        return lista;
    }

    public boolean verificarCodigoDiciplina(String codigo) {
        Cursor cursor = db.rawQuery("SELECT * FROM Disciplina WHERE codigo = ?", new String[]{codigo});
        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    public int obterIdPorNome(String nome) {
        Cursor cursor = db.rawQuery("SELECT id FROM Disciplina WHERE nome = ?", new String[]{nome});
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        return -1;
    }
}
