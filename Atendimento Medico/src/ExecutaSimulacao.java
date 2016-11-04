
import estabelecimento.*;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Date;


public class ExecutaSimulacao {

	
	public static void main(String[] args) {
		List<Pessoa> fila = ManipulaXML.lerXMLPessoas();
		List<Medico> medicos = ManipulaXML.lerXMLMedicos();
		List<Atendimento> atendidos = ManipulaXML.lerXMLAtendimentos();
		
		
		//Após ser definida a prioridade do paciente, ele automaticamente estará em uma fila de espera.
		
		String[] menuPrincipal = {"1- Definir prioridade do paciente e adicioná-lo à fila", "2- Mostrar a fila", "3- Atender paciente", 
				"4- Mostrar lista de pacientes atendidos", "5- Cadastrar médico", "6- Remover médico", "Sair"};
		
		
		String input = "";
		Pessoa p = new Pessoa();
		Medico m = new Medico();
		Atendimento at = new Atendimento();
		do {
			p = new Pessoa();
			m = new Medico();
			at = new Atendimento();
			at.defineCodigoAtendimento(atendidos, at);
			input = (String) JOptionPane.showInputDialog(null, "Bem vindo à simulação de atendimento médico!!\n\nSelecione \"Sair\" para parar simulação.",
			        "Atendimento Médico", JOptionPane.QUESTION_MESSAGE, null, 
			        menuPrincipal, // Array de escolhas possíveis
			        menuPrincipal[0]); // Escolha padrão
		
			if (input==menuPrincipal[0]){
				at.definePrioridade(p);
				at.adicionaPessoaFila(fila, p, atendidos);
			} else if (input==menuPrincipal[1]){
				if (fila.isEmpty()){
					JOptionPane.showMessageDialog(null, "Não há pessoas na fila.");
				}else {
					at.mostraFila(fila);
				}
			} else if (input==menuPrincipal[2]){
				String[] listaMedicos = new String[medicos.size()];
				for(int i=0; i<medicos.size(); i++){
					listaMedicos[i] = medicos.get(i).getNome();
				}
				String med = "";
				
				//Daria pra fazer com random, mas estou sem tempo. (Pega o tamanho da lista e tals, e vai em algum)
				while (med.length()<1 || med ==null){
					med = (String) JOptionPane.showInputDialog(null, "Por favor, selecione o médico que irá atender: ",
					        "Selecionar Médico", JOptionPane.QUESTION_MESSAGE, null, 
					        listaMedicos, // Array de escolhas possíveis
					        listaMedicos[0]); // Escolha padrão
				}
				
				for(Medico medic : medicos){
					if (med==medic.getNome()){
						at.atenderPessoa(fila, medic, at, atendidos);
						break;
					}
				}
				
				
			}else if (input==menuPrincipal[3]){
				JOptionPane.showMessageDialog(null, "\n\n        O resultado será mostrado no console\n\n        "
						+ "        Para continuar, digite 1 no console        ");
				for (Atendimento atend: atendidos){
					System.out.println("Codigo do atendimento:      "+atend.getCodigo());
					System.out.println("Código do paciente:         "+atend.getCodigoPessoa());
					System.out.println("Código do médico:           "+atend.getCodigoMedico());
					System.out.println("Data de chegada do paciente:"+Datas.DataHoraForStringPadraoH(atend.getDataChegada()));
					System.out.println("Data do atendimento:        "+Datas.DataHoraForStringPadraoH(atend.getDataAtendimento())+"\n\n");
				}
				Scanner ler = new Scanner(System.in);
				int entrada = 0;
				while (entrada!=1){
					System.out.print("\nPor favor, digite 1 para continuar:" );
					entrada = ler.nextInt();
				}
				ler.close();
			} else if (input == menuPrincipal[4]){
				at.cadastraMedico(medicos, m);
			} else if (input == menuPrincipal[5]){
				String[] listaMedicos = new String[medicos.size()];
				for(int i=0; i<medicos.size(); i++){
					listaMedicos[i] = medicos.get(i).getNome();
				}
				String med = "";
				
				//Mostra a lista de médicos e mostra as opções.
				//Daria pra fazer com random, mas estou sem tempo. (Pega o tamanho da lista e tals, e vai em algum)
				while (med.length()<1){
					med = (String) JOptionPane.showInputDialog(null, "Por favor, selecione o médico que deseja remover: ",
					        "Selecionar Médico", JOptionPane.QUESTION_MESSAGE, null, 
					        listaMedicos, // Array de escolhas possíveis
					        listaMedicos[0]); // Escolha padrão
				}
				
				
				for(Medico medic : medicos){
					if (med==medic.getNome()){
						at.removeMedico(medicos, medic);
						break;
					}
				}
			}
			
		} while (input!=menuPrincipal[6]);
		
		ManipulaXML.gravarXMLPessoa(fila);
		ManipulaXML.gravarXMLMedico(medicos);
		ManipulaXML.gravarXMLAtendimento(atendidos);
		
	}

}
