package br.com.alura.agenda;

import android.app.Application;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //criaAlunosDeTeste();
    }

//    private void criaAlunosDeTeste() {
//        AgendaDatabase database = Room
//                .databaseBuilder(this, AgendaDatabase.class, "agenda.db")
//                .allowMainThreadQueries()
//                .build();
//
//        AlunoDAO dao = database.getRoomAlunoDAO();
//        dao.salva(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
//        dao.salva(new Aluno("Fran", "1122223333", "fran@gmail.com"));
//    }
}
