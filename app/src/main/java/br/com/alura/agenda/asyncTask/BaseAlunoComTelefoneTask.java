package br.com.alura.agenda.asyncTask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones)
            telefone.setAlunoId(alunoId);
    }

    /*
        Criação do Listener.

        Quando existente, na chamada da task, o método obriga a criação do mesmo.
        Isso é utilizado quando necessita a execução de alguma tarefa
        na activity.
     */
    public interface FinalizadaListener {
        void quandoFinalizada();
    }
}
