package br.com.alura.agenda.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.converter.ConversorTipoTelefone;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

import static br.com.alura.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries() //Utilizado para executar ação na thread principal
                //.fallbackToDestructiveMigration() //Retorna ao estado inicial do banco
                //em vez do método acima, utiliza-se o de baixo
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
