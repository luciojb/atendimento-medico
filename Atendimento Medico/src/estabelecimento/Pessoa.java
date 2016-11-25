package estabelecimento;

import java.util.Date;

public class Pessoa {
	private int codigo, prioridade, idade;
	private String nome, endereco;
	private Date  dataChegada;
	
	
	public Pessoa(){
		
	}
	public Pessoa(int codigo, int idade, int prioridade, String nome, String endereco){
		setCodigo(codigo);
		setPrioridade(prioridade);
		setNome(nome);
		setEndereco(endereco);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		if (codigo>0)
			this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome.length()>0)
			this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if (endereco.length()>0)
			this.endereco = endereco;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		if (prioridade>0)
			this.prioridade = prioridade;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		if (idade>0)
			this.idade = idade;
	}
	public Date getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	
	
	
	
}
