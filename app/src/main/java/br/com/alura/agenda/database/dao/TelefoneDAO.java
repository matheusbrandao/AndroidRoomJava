package br.com.alura.agenda.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    /*
        Exemplo de QUERY com JOIN:

        @Query("SELECT t.* FROM telefone t " +
                "JOIN aluno a " +
                "ON t.alunoId = a.id " +
                "WHERE t.alunoId = :alunoId " +
                "LIMIT 1")
        Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);
    */

    @Query("SELECT * FROM telefone  " +
            "WHERE alunoId = :alunoId " +
            "LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM telefone  " +
            "WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);

    /*
        Utilizado INSERT pois caso houver conflito, ou seja, se já existir o registro,
        o Room fará o UPDATE dos dados. Quando não existir, o INSERT será feito
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
