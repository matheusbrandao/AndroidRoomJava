package br.com.alura.agenda.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import br.com.alura.agenda.model.TipoTelefone;

class AgendaMigrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //Estratégia para excluir a coluna sobrenome do banco

            // Criar nova tabela com as informações desejas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)");

            //Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email) " +
                    "SELECT id, nome, telefone, email FROM Aluno");

            //Remove a tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //Renomeia a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };

    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefoneFixo` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER, " +
                    "`telefoneCelular` TEXT)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email, momentoDeCadastro) " +
                    "SELECT id, nome, telefone, email, momentoDeCadastro FROM Aluno");

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)");
            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, momentoDeCadastro) " +
                    "SELECT id, nome, email, momentoDeCadastro FROM Aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL)");
            database.execSQL("INSERT INTO telefone (numero, alunoId) " +
                    "SELECT telefoneFixo, id FROM Aluno");
            database.execSQL("UPDATE telefone SET tipo = ?", new TipoTelefone[] {TipoTelefone.FIXO});

            database.execSQL("DROP TABLE Aluno");
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");

        }
    };

    static final Migration[] TODAS_MIGRATIONS = {
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4,
            MIGRATION_4_5,
            MIGRATION_5_6
    };
}
