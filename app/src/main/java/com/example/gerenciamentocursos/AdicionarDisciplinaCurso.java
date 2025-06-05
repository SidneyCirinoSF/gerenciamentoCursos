package com.example.gerenciamentocursos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdicionarDisciplinaCurso extends AppCompatActivity {

    private Spinner spinnerCursos, spinnerDisciplinas;
    private Button btnRelacionar;

    private CursoDAO cursoDAO;
    private DisciplinaDAO disciplinaDAO;
    private CursoDisciplinaDAO cursoDisciplinaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_disciplina_curso);

        spinnerCursos = findViewById(R.id.spinnerCursos);
        spinnerDisciplinas = findViewById(R.id.spinnerDisciplinas);
        btnRelacionar = findViewById(R.id.btnRelacionarDisciplinaCurso);

        cursoDAO = new CursoDAO(this);
        disciplinaDAO = new DisciplinaDAO(this);
        cursoDisciplinaDAO = new CursoDisciplinaDAO(this);

        carregarSpinners();

        btnRelacionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cursoSelecionado = spinnerCursos.getSelectedItem().toString();
                String disciplinaSelecionada = spinnerDisciplinas.getSelectedItem().toString();

                int cursoId = cursoDAO.obterIdPorNome(cursoSelecionado);
                int disciplinaId = disciplinaDAO.obterIdPorNome(disciplinaSelecionada);

                if (cursoId != -1 && disciplinaId != -1) {

                    boolean jaRelacionado = cursoDisciplinaDAO.verificarRelacionamento(cursoId, disciplinaId);

                    if (jaRelacionado) {
                        Toast.makeText(AdicionarDisciplinaCurso.this,
                                "Essa disciplina já está vinculada a este curso.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    long resultado = cursoDisciplinaDAO.relacionarDisciplinaAoCurso(cursoId, disciplinaId);
                    if (resultado != -1) {
                        Toast.makeText(AdicionarDisciplinaCurso.this,
                                "Disciplina relacionada ao curso com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdicionarDisciplinaCurso.this,
                                "Erro ao relacionar disciplina ao curso.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdicionarDisciplinaCurso.this,
                            "Erro ao identificar curso ou disciplina", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void carregarSpinners() {
        ArrayList<String> cursos = cursoDAO.listarNomesCursos();
        ArrayList<String> disciplinas = disciplinaDAO.listarNomesDisciplinas();

        if (cursos.isEmpty()) cursos.add("Nenhum curso cadastrado");
        if (disciplinas.isEmpty()) disciplinas.add("Nenhuma disciplina cadastrada");

        ArrayAdapter<String> adapterCursos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cursos);
        adapterCursos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCursos.setAdapter(adapterCursos);

        ArrayAdapter<String> adapterDisciplinas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, disciplinas);
        adapterDisciplinas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDisciplinas.setAdapter(adapterDisciplinas);
    }
}
