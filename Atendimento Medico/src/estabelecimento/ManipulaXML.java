package estabelecimento;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


public class ManipulaXML {
	
	final static String ARQUIVOPESSOA = "pessoa";
	final static String ARQUIVOMEDICO = "medico";
	final static String ATENDIDOS = "atendimento";
	final static String LOCALHOST = "xml/";
	
	public static boolean gravarXMLPessoa(List<Pessoa> Pessoas){
		// Elemento root do estabelecimento
		Element raiz = new Element("Pessoa");

		//Defie raiz como root
		Document documento = new Document(raiz);

		Element titulo = new Element("titulo");
		titulo.setText("Pessoas na fila");

		Element data = new Element("data");
		data.setText(Datas.DataHoraForStringPadraoH(new Date()));
		
		raiz.addContent(titulo);
		raiz.addContent(data);
		
		for (int x = 0; x < Pessoas.size(); x++){
			Element pessoa = new Element("pessoa");
			
			pessoa.setAttribute("codigo", String.valueOf(Pessoas.get(x).getCodigo()));
			
			Element nome = new Element("nome");
			nome.setText(Pessoas.get(x).getNome());
			
			Element prioridade = new Element("prioridade");
			prioridade.setText(String.valueOf(Pessoas.get(x).getPrioridade()));

			Element idade = new Element("idade");
			idade.setText(String.valueOf(Pessoas.get(x).getIdade()));

			Element endereco = new Element("endereco");
			endereco.setText(Pessoas.get(x).getEndereco());
			
			
			Element dataChegada = new Element("data_chegada");
			dataChegada.setText(Datas.DataHoraForStringPadraoH(Pessoas.get(x).getDataChegada()));
						
			pessoa.addContent(nome);
			pessoa.addContent(prioridade);
			pessoa.addContent(idade);
			pessoa.addContent(endereco);
			pessoa.addContent(dataChegada);
			raiz.addContent(pessoa);
			
		}

		//Classe responsável por criar / gerar o xml
		XMLOutputter xout = new XMLOutputter();	
		try {
			//criando o arquivo de saida das pessoas que estão na fila
			BufferedWriter arquivoPessoas = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOCALHOST +  ARQUIVOPESSOA + ".xml"),"UTF-8"));
			//imprimindo o xml no arquivo das pessoas
			xout.output(documento, arquivoPessoas);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean gravarXMLMedico(List<Medico> Medicos){
		// Elemento root do estabelecimento
		Element raiz = new Element("Medico");

		//Defie raiz como root
		Document documento = new Document(raiz);

		Element titulo = new Element("titulo");
		titulo.setText("Medicos disponíveis");

		Element data = new Element("data");
		data.setText(Datas.DataHoraForStringPadraoH(new Date()));
		
		raiz.addContent(titulo);
		raiz.addContent(data);
		
		for (int x = 0; x < Medicos.size(); x++){
			Element medico = new Element("medico");
			
			medico.setAttribute("codigo", String.valueOf(Medicos.get(x).getCodigo()));
			
			Element nome = new Element("nome");
			nome.setText(Medicos.get(x).getNome());
			
			Element idade = new Element("idade");
			idade.setText(String.valueOf(Medicos.get(x).getIdade()));

			
			Element dataUltimoAtendimento = new Element("data_ultimo_atendimento");
			dataUltimoAtendimento.setText(Datas.DataHoraForStringPadraoH(Medicos.get(x).getDataUltimoAtendimento()));
						
			medico.addContent(nome);
			medico.addContent(idade);
			medico.addContent(dataUltimoAtendimento);
			raiz.addContent(medico);
			
		}

		//Classe responsável por criar / gerar o xml
		XMLOutputter xout = new XMLOutputter();	
		try {
			//criando o arquivo de saida das pessoas que estão na fila
			BufferedWriter arquivoMedicos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOCALHOST +  ARQUIVOMEDICO + ".xml"),"UTF-8"));
			//imprimindo o xml no arquivo das pessoas
			xout.output(documento, arquivoMedicos);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean gravarXMLAtendimento(List<Atendimento> Atendimentos){
		// Elemento root do estabelecimento
		Element raiz = new Element("Atendimento");

		//Defie raiz como root
		Document documento = new Document(raiz);

		Element titulo = new Element("titulo");
		titulo.setText("Histórico de Atendimentos");

		Element data = new Element("data");
		data.setText(Datas.DataHoraForStringPadraoH(new Date()));
		
		raiz.addContent(titulo);
		raiz.addContent(data);
		
		for (int x = 0; x < Atendimentos.size(); x++){
			Element atendimento = new Element("atendimento");
			
			atendimento.setAttribute("codigo", String.valueOf(Atendimentos.get(x).getCodigo()));
			
			Element codigoMedico = new Element("codigo_medico");
			codigoMedico.setText(String.valueOf(Atendimentos.get(x).getCodigoMedico()));
			
			Element codigoPessoa = new Element("codigo_pessoa");
			codigoPessoa.setText(String.valueOf(Atendimentos.get(x).getCodigoPessoa()));

			Element dataAtendimento = new Element("data_atendimento");
			dataAtendimento.setText(Datas.DataHoraForStringPadraoH(Atendimentos.get(x).getDataAtendimento()));
			
			Element dataChegada = new Element("data_chegada");
			dataChegada.setText(Datas.DataHoraForStringPadraoH(Atendimentos.get(x).getDataChegada()));
						
			atendimento.addContent(codigoMedico);
			atendimento.addContent(codigoPessoa);
			atendimento.addContent(dataAtendimento);
			atendimento.addContent(dataChegada);
			raiz.addContent(atendimento);
			
		}

		//Classe responsável por criar / gerar o xml
		XMLOutputter xout = new XMLOutputter();	
		try {
			//criando o arquivo de saida das pessoas que estão na fila
			BufferedWriter arquivoAtendimentos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOCALHOST +  ATENDIDOS + ".xml"),"UTF-8"));
			//imprimindo o xml no arquivo das pessoas
			xout.output(documento, arquivoAtendimentos);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static List<Pessoa> lerXMLPessoas(){
		List<Pessoa> fila = new ArrayList<Pessoa>();
		Document documento = null;
		SAXBuilder construtor = new SAXBuilder();	
		try { 
			documento = construtor.build(LOCALHOST + ARQUIVOPESSOA + ".xml");
		} catch (Exception e) {
			e.printStackTrace();
		}            
		Element raiz = documento.getRootElement();
		@SuppressWarnings("rawtypes")
		List filho = raiz.getChildren("pessoa");
		
		for (@SuppressWarnings("rawtypes")
		Iterator iter = filho.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			Pessoa pes = new Pessoa();
			pes.setCodigo(Integer.parseInt(element.getAttributeValue("codigo")));
			pes.setPrioridade((Integer.parseInt(element.getChildText("prioridade"))));
			pes.setIdade(Integer.parseInt(element.getChildText("idade")));
			pes.setNome(element.getChildText("nome"));
			pes.setEndereco(element.getChildText("endereco"));
			try{
				pes.setDataChegada(Datas.StringParaDataHora(element.getChildText("data_chegada")));
			} catch(ParseException pE){
				pE.printStackTrace();
			}
			fila.add(pes);
		}
		return fila;
	}
	
	public static List<Medico> lerXMLMedicos(){
		List<Medico> medicos = new ArrayList<Medico>();
		Document documento = null;
		SAXBuilder construtor = new SAXBuilder();	
		try { 
			documento = construtor.build(LOCALHOST + ARQUIVOMEDICO + ".xml");
		} catch (Exception e) {
			e.printStackTrace();
		}            
		Element raiz = documento.getRootElement();
		@SuppressWarnings("rawtypes")
		List filho = raiz.getChildren("medico");
		
		for (@SuppressWarnings("rawtypes")
		Iterator iter = filho.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			Medico med = new Medico();
			med.setCodigo(Integer.parseInt(element.getAttributeValue("codigo")));
			med.setIdade(Integer.parseInt(element.getChildText("idade")));
			med.setNome(element.getChildText("nome"));
			try{
				med.setDataUltimoAtendimento(Datas.StringParaDataHora(element.getChildText("data_ultimo_atendimento")));
			} catch(ParseException pE){
				pE.printStackTrace();
			}
			medicos.add(med);
		}
		return medicos;
	}
	
	
	
	public static List<Atendimento> lerXMLAtendimentos(){
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		Document documento = null;
		SAXBuilder construtor = new SAXBuilder();	
		try { 
			documento = construtor.build(LOCALHOST + ATENDIDOS + ".xml");
		} catch (Exception e) {
			e.printStackTrace();
		}            
		Element raiz = documento.getRootElement();
		@SuppressWarnings("rawtypes")
		List filho = raiz.getChildren("atendimento");
		
		for (@SuppressWarnings("rawtypes")
		Iterator iter = filho.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			Atendimento at = new Atendimento();
			at.setCodigo(Integer.parseInt(element.getAttributeValue("codigo")));
			at.setCodigoMedico(Integer.parseInt(element.getChildText("codigo_medico")));
			at.setCodigoPessoa(Integer.parseInt(element.getChildText("codigo_pessoa")));
			try{
				at.setDataAtendimento(Datas.StringParaDataHora(element.getChildText("data_atendimento")));
			} catch(ParseException pE){
				pE.printStackTrace();
			}
			try{
				at.setDataChegada(Datas.StringParaDataHora(element.getChildText("data_chegada")));
			} catch(ParseException pE){
				pE.printStackTrace();
			}
			atendimentos.add(at);
		}
		return atendimentos;
	}
	
	
	
	
}
