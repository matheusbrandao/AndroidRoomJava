package br.com.alura.agenda.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String telefone;
    private String email;
    private Calendar momentoDeCadastro = Calendar.getInstance();

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto() {
        return nome;
    }

    public String dataFormatada(){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(momentoDeCadastro.getTime());
    }
}