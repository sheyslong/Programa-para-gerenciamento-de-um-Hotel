package hospede;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import cartaofidelidade.CartaoFidelidade;
import cartaofidelidade.FactoryCartaoFidelidade;
import estadia.Estadia;
import exceptionsmetodos.ExceptionMetodosHospede;
import sistemaexception.AtualizaDataNascimentoHospedeFormatException;
import sistemaexception.AtualizaDataNascimentoNullException;
import sistemaexception.AtualizaEmailHospedeException;
import sistemaexception.AtualizaNomeHospedeException;
import sistemaexception.DataNascimentoNullException;
import sistemaexception.EmailHospedeException;
import sistemaexception.FormatoDataException;
import sistemaexception.MenorDeIdadeException;
import sistemaexception.NomeHospedeException;
import sistemaexception.NomeHospedeInvalidoException;
import sistemaexception.ValorInvalidoException;

public class Hospede {
	
	private SimpleDateFormat format;
	private Date dataNascimento;
	
	private String nome;
	private String email;
	private FactoryCartaoFidelidade factoryCartao;
	private CartaoFidelidade cartao;
	private HashSet<Estadia> estadias;
	private ExceptionMetodosHospede exception;
	
	public Hospede(String nome, String email, String dataNascimento) throws NomeHospedeInvalidoException, NomeHospedeException, EmailHospedeException, FormatoDataException, DataNascimentoNullException, MenorDeIdadeException, ParseException {
		this.exception = new ExceptionMetodosHospede();
		this.exception.exceptionEntrada(nome, email, dataNascimento);
		
		this.format = new SimpleDateFormat("dd/MM/yyyy");
		this.nome = nome;
		this.email = email;
		this.dataNascimento = new Date();
		this.dataNascimento = format.parse(dataNascimento);
		this.factoryCartao = new FactoryCartaoFidelidade();
		this.cartao = this.factoryCartao.criarCartaoFidelidade();
		this.estadias =  new HashSet<Estadia>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws AtualizaNomeHospedeException {
		this.exception.exceptionAtualizaNomeHospede(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws AtualizaEmailHospedeException {
		this.exception.exceptionAtualizaEmailHospede(email);
		this.email = email;
	}

	public double getDebito() {
		return this.cartao.getGastosHospedes();
	}


	public String getAnoNascimento() {
		return this.format.format(dataNascimento);
	}

	public void setDataNascimento(String data) throws AtualizaDataNascimentoHospedeFormatException, DataNascimentoNullException, ParseException, AtualizaDataNascimentoNullException {
		this.exception.exceptionAtualizaDataNascimentoHospedeFormat(data);
		this.exception.exceptionAtualizaDataNascimentoHospede(data);
		Date dataNascimento = new Date();
		dataNascimento = format.parse(data);
		this.dataNascimento = dataNascimento;
		
	}
	
	public CartaoFidelidade getCartao() {
		return cartao;
	}

	public HashSet<Estadia> getEstadias() {
		return estadias;
	}

	public void adicionaEstadia(Estadia estadia) throws ValorInvalidoException {
		this.getEstadias().add(estadia);
		this.cartao.controleGastos(estadia.getValorTotal());
	}


	public void removeEstadia(Estadia estadia, double pagamento) throws ValorInvalidoException {
		this.cartao.controleGastos(pagamento * (-1));
		this.getEstadias().remove(estadia);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Hospede){
			Hospede hospede = (Hospede)obj;
			if(hospede.getNome().equalsIgnoreCase(this.getNome()) && hospede.getEmail().equalsIgnoreCase(this.getEmail()) && hospede.getAnoNascimento() == this.getAnoNascimento()){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		String retorno = "Nome: " + this.getNome() + 
				"\nE-mail: " + this.getEmail() + 
				"\nAno Nascimento: " + this.getAnoNascimento() +
				"\nEstadias:";
		for(Estadia estadia: this.getEstadias()){
			retorno += "\n" + estadia.toString();
		}
		
		return retorno;		
	}
	
	
}