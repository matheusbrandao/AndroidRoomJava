package br.com.alura.agenda.asyncTask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradoListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO telefoneDAO,
                                          Aluno aluno,
                                          TelefonesDoAlunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    /*
        Criação do Listener.

        Quando existente, na chamada da task, o método obriga a criação do mesmo.
        Isso é utilizado quando necessita a execução de alguma tarefa
        na activity.
     */
    public interface TelefonesDoAlunoEncontradoListener{
        void quandoEncontrados(List<Telefone> telefones);
    }
}
