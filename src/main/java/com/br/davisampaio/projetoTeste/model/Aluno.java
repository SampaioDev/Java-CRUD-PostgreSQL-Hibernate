package com.br.davisampaio.projetoTeste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	
@Entity
@Table(name = "Aluno")
public class Aluno {

    private long id;
    private String nome;
    private Integer idade;
    private String email;
    private Integer vp1;
    private Integer vp2;
    private Integer vf;
    private Integer media;
 
    public Aluno() {
  
    }
 
    public Aluno(String nome, Integer idade, String email) {
         this.nome = nome;
         this.email = email;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "nome", nullable = false)
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    @Column(name = "idade", nullable = false)
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
 
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name = "vp1", nullable = true)
    public Integer getVp1() {
		return vp1;
	}

	public void setVp1(Integer vp1) {
		this.vp1 = vp1;
	}

	@Column(name = "vp2", nullable = true)
	public Integer getVp2() {
		return vp2;
	}

	public void setVp2(Integer vp2) {
		this.vp2 = vp2;
	}

	@Column(name = "vf", nullable = true)
	public Integer getVf() {
		return vf;
	}

	public void setVf(Integer vf) {
		this.vf = vf;
	}

	@Column(name = "media", nullable = true)
	public Integer getMedia() {
		if(this.vp1 != null && this.vp2 != null && this.vf != null) {
			return (this.vp1 + (this.vp2 * 2) + (this.vf * 3))/5;			
		}
		else {
			return media;
		}
	}

	public void setMedia(Integer media) {
		this.media = media;
	}

    @Override
    public String toString() {
        return "Employee [id=" + id + ", nome=" + nome + ", idade=" + idade + ", email=" + email
       + "]";
    }
 
}