package estabelecimento;

import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;
import java.util.List;
//import java.util.ArrayList;

public class Atendimento {
	private int codigo, codigoMedico, codigoPessoa;
	private Date dataAtendimento, dataChegada;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		if (codigo>0)
			this.codigo = codigo;
	}
	public int getCodigoMedico() {
		return codigoMedico;
	}
	public void setCodigoMedico(int codigoMedico) {
		if (codigoMedico>0)
			this.codigoMedico = codigoMedico;
	}
	public int getCodigoPessoa() {
		return codigoPessoa;
	}
	public void setCodigoPessoa(int codigoPessoa) {
		if (codigoPessoa>0)
			this.codigoPessoa = codigoPessoa;
	}
	public Date getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public Date getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	public void defineCodigoAtendimento(List<Atendimento> atendidos, Atendimento ate){
		int maiorCodigo=1;
		for (Atendimento at : atendidos){
			if (maiorCodigo<at.getCodigo())
				maiorCodigo=at.getCodigo()+1;
		}
		ate.setCodigo(maiorCodigo);
	}
	
	public static String validaEntrada(String[] opcoes, String mensagem ){
		String input = "";
		while(input.length()<1 || input.equals("Sair")){
			input = (String) JOptionPane.showInputDialog(null, mensagem+ "\n\n Se quiser sair, selecione \"Sair\"",
			        "Define Prioridade", JOptionPane.QUESTION_MESSAGE, null, // ícone padrão
			        opcoes, // Array de escolhas possíveis
			        opcoes[0]); // Escolha padrão
		}
		
		return input;
	}
	
	public void definePrioridade(Pessoa pessoa){
		pessoa.setDataChegada(new Date());
		String[] caracteristica = {"1- Gestante/lactante", "2- Necessidade Especial",  "3- Com criança no colo", 
				"4- Atendimento convencional", "5- Gravemente ferido", "Sair"};
		String[] planos = {"1- Privado cobertura total", "2- Privado cobertura para este estabelecimento", "3- Privado normal", "4- SUS", "Sair"};
		String[] sintomas = {"1- Perna ou braço quebrado, derrame, infarto, apendicite", "2- Sofreu acidente leve, ou picada de animal venenoso", 
				"3- Teve convulsão", "4- Nenhum se aplica", "Sair"};
		String input = (String) JOptionPane.showInputDialog(null, "Entre com sua idade:");
		
		int idade = Integer.parseInt(input);
		int pontuacao = 0;
		while(idade<1 || idade>150) {
			input = (String) JOptionPane.showInputDialog(null, "Idade inválida. Entre com sua idade:");
			idade = Integer.parseInt(input);
			
		}
		
		pessoa.setIdade(idade);
		input = "";
		while (input.length()<1){
			input =  (String) JOptionPane.showInputDialog(null, "Entre com seu nome:");
		}
		pessoa.setNome(input);
		input = "";
		while (input.length()<1){
			input =  (String) JOptionPane.showInputDialog(null, "Entre com seu endereço:");
		}
		pessoa.setEndereco(input);
		
		String mensagem = "";
		
		mensagem = "Selecione o tipo de atendimento: ";
		input = validaEntrada(caracteristica, mensagem);
		
		if (input==caracteristica[4])
			pontuacao =12;
		else if (input==caracteristica[0] || (idade<10 || idade>60))
			pontuacao=9;
		else if (input==caracteristica[1] || (idade<14 || idade>55))
			pontuacao=3;
		else if (input==caracteristica[2] || (idade<18 || idade>50))
			pontuacao = 2;
		else 
			pontuacao = 1;
		
		
		mensagem = "Selecione o plano de saúde: ";
		input = validaEntrada(planos, mensagem);
		
		
		if (pontuacao!=12 && pontuacao!=9){
			if (input == planos[0])
				pontuacao+=4;
			else if (input==planos[1])
				pontuacao+=3;
			else if (input==planos[2])
				pontuacao+=2;
			else
				pontuacao+=1;
		}
		
		

		mensagem = "Selecione um destes sintomas: ";
		input = validaEntrada(sintomas, mensagem);
		
		if(pontuacao!=12 && pontuacao!=9){
			if (input==sintomas[0])
				pontuacao+=4;
			else if (input==sintomas[1])
				pontuacao+=3;
			else if (input==sintomas[2])
				pontuacao+=2;
			else 
				pontuacao+=1;
		}
		
		if (pontuacao>9)
			pessoa.setPrioridade(4);
		else if (pontuacao>6)
			pessoa.setPrioridade(3);
		else if (pontuacao>3)
			pessoa.setPrioridade(2);
		else
			pessoa.setPrioridade(1);
	}
	
	public void adicionaPessoaFila(List<Pessoa> lista, Pessoa pessoa, List<Atendimento> atendidos){
		int maiorCodigo=1;
		for (Pessoa pes : lista){
			if (maiorCodigo<pes.getCodigo())
				maiorCodigo = pes.getCodigo()+1;
		}
		for (Atendimento ate : atendidos){
			if (maiorCodigo<ate.getCodigoPessoa())
				maiorCodigo = ate.getCodigoPessoa()+1;
		}
		pessoa.setCodigo(maiorCodigo);
		
		if (pessoa.getPrioridade()>0 && pessoa.getNome().length()>0 && pessoa.getIdade()>0 && pessoa.getEndereco().length()>0){
		
			if (lista.size()==0){
				lista.add(0, pessoa);
				
			}else {
				
				for(Pessoa p : lista){
					if(pessoa.getPrioridade()>p.getPrioridade()){
						lista.add(lista.indexOf(p), pessoa);
						break;
					} else {
						lista.add(pessoa);
						break;
					}
				}
				/*
				for (int i=0; i<lista.size(); i++){
					if (pessoa.getPrioridade()<lista.get(i).getPrioridade()){
						lista.add(i, pessoa);
						break;
					} else if (pessoa.getPrioridade()==lista.get(lista.size()-1).getPrioridade()){
						
					}
				}
				*/
			}
		}
		
	}
	
	public void atenderPessoa(List<Pessoa> fila, Medico medico, Atendimento at, List<Atendimento> atendido){
		
		if (fila.isEmpty()){
			JOptionPane.showMessageDialog(null, "Não há pessoas na fila.");
		}else {
			Pessoa pessoa = (Pessoa) fila.get(0);
			at.setDataAtendimento(new Date());
			at.setDataChegada(pessoa.getDataChegada());
			at.setCodigoPessoa(pessoa.getCodigo());
			at.setCodigoMedico(medico.getCodigo());
			atendido.add(at);
			fila.remove(pessoa);
			medico.setDataUltimoAtendimento(new Date());
		}
		
	}
	
	public void cadastraMedico(List<Medico> medicos, Medico medico){
		String input = (String) JOptionPane.showInputDialog(null, "Entre com a idade:");
		
		int idade = Integer.parseInt(input);
		while(idade<20 || idade>80) {
			input = (String) JOptionPane.showInputDialog(null, "Idade inválida, deve ser entre 20 e 80. Entre com sua idade:");
			idade = Integer.parseInt(input);
			
		}
		medico.setIdade(idade);
		
		input = (String) JOptionPane.showInputDialog(null, "Entre com o nome:");
		while (input.length()<1){
			input = (String) JOptionPane.showInputDialog(null, "Nome inválido. Entre com o nome:");
		}
		medico.setNome(input);
		
		int maiorCodigo=1;
		for (Medico m : medicos){
			if (maiorCodigo<m.getCodigo())
				maiorCodigo=m.getCodigo()+1;
		}
		medico.setCodigo(maiorCodigo);
		if (medico.getNome().length()>0 && medico.getIdade()>0)
			medicos.add(medico);
	}
	
	public void removeMedico(List<Medico> medicos, Medico medico){
		medicos.remove(medico);
	}
	
	//se precisar
	public void inicializaRHmedico(List<Medico> medicos){
		if (medicos.isEmpty()){
			Medico medico = new Medico();
			medico.setCodigo(1);
			medico.setIdade(25);
			medico.setNome("Jonas de Alcântara");
			medicos.add(medico);
			
			medico = new Medico();
			medico.setCodigo(medicos.get(medicos.size()-1).getCodigo()+1);
			medico.setIdade(34);
			medico.setNome("Ivan Rosknov");
			medicos.add(medico);
		}
	}
	
	public void mostraFila(List<Pessoa> fila){
		StringBuilder builder = new StringBuilder();
		builder.append("Codigo:   ");
		builder.append("Nome:   \n\n");
		for (Pessoa p : fila){
			builder.append(p.getCodigo());
			builder.append("          ");
			builder.append(p.getNome());
			builder.append("\n");
			
		}
		
		JOptionPane.showMessageDialog(null, builder.toString());
	}
	
	
}
