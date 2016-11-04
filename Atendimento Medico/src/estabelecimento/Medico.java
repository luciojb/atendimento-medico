package estabelecimento;

import java.util.Date;

public class Medico {
	private int codigo, idade;
	private String nome;
	private Date dataUltimoAtendimento;
	
	
	public Medico(){
		
	}
	
	public Medico(int codigo, int idade, String nome){
		setCodigo(codigo);
		setIdade(idade);
		setNome(nome);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		if (codigo>0)
			this.codigo = codigo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		if (idade>0)
			this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome.length()>0)
			this.nome = nome;
	}

	public Date getDataUltimoAtendimento() {
		return dataUltimoAtendimento;
	}

	public void setDataUltimoAtendimento(Date dataUltimoAtendimento) {
		this.dataUltimoAtendimento = dataUltimoAtendimento;
	}
	
	
	
	
}
