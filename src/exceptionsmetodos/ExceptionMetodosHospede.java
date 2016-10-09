package exceptionsmetodos;
	
import java.util.Calendar;

import sistemaexception.AtualizaCadastroException;
import sistemaexception.CadastroHospedeException;
import sistemaexception.DataNascimentoNullException;
import sistemaexception.EmailHospedeException;
import sistemaexception.FormatoDataException;
import sistemaexception.MenorDeIdadeException;
import sistemaexception.NomeHospedeException;
import sistemaexception.NomeHospedeInvalidoException;
	
public class ExceptionMetodosHospede extends ExceptionMetodos{
	
	public void exceptionEntrada(String nome, String email, String dataNascimento) throws CadastroHospedeException {
		this.exceptionNomeHospedeInvalido(nome);
		this.exceptionEmailHospede(email);
		this.exceptionEmailFormat(email);
		this.exceptionMenorDeIdade(dataNascimento);
	
	}
	
	//Cadastro
	//Nome
	public void exceptionNomeHospede(String nome) throws CadastroHospedeException{
		if(nome == null || nome.trim().isEmpty()) {
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new  NomeHospedeException());
		}
	}
	
	public void exceptionNomeHospedeInvalido(String nome) throws CadastroHospedeException {
		this.exceptionNomeHospede(nome);
		String[] str = nome.split(" ");
		for(int i = 0; i < str.length; i ++){
			if(!(str[i].matches("^\\A[a-zA-Z]*\\z$"))){
				throw new CadastroHospedeException(new CadastroHospedeException() + " " + new NomeHospedeInvalidoException());
			}
		}
	}
	
	//Email
	public void exceptionEmailHospede(String email) throws CadastroHospedeException {
		if (email == null || email.trim().isEmpty()) {
			throw new CadastroHospedeException(new CadastroHospedeException() + " " +  new EmailHospedeException() + " nao pode ser vazio.");
		}
	}
	
	public void exceptionEmailFormat(String email) throws CadastroHospedeException{
		if(!(email.matches("^\\A[a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+.[a-zA-Z]+\\z$"))){
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new EmailHospedeException() + " esta invalido.");
		}
	}
	
	
	//DataNascimento
	private void exceptionDataNascimentoHospedeNull(String dataNascimento) throws CadastroHospedeException{
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new DataNascimentoNullException());
		}
	}
	
	private void exceptionDataNascimentoHospedeFormat(String dataNascimento) throws CadastroHospedeException{
		this.exceptionDataNascimentoHospedeNull(dataNascimento);
		if(!(dataNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$"))){
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new FormatoDataException());
		}
		String[] str = dataNascimento.split("/");
		if(Integer.parseInt(str[0]) <= 0 || Integer.parseInt(str[0])  > 31){
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new FormatoDataException());
		}
		
		if(Integer.parseInt(str[1]) <= 0 || Integer.parseInt(str[1]) > 12){
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new FormatoDataException());
		}
		if(Integer.parseInt(str[2]) <= 0 || Integer.parseInt(str[1]) > Calendar.getInstance().get(Calendar.YEAR)){
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new FormatoDataException());
		}
	}
	
	public void exceptionMenorDeIdade(String dataNascimento) throws CadastroHospedeException {
		this.exceptionDataNascimentoHospedeFormat(dataNascimento);
		String[] str = dataNascimento.split("/");
		if((Calendar.getInstance().get(Calendar.YEAR )- Integer.parseInt(str[2])) < 18){
			throw new CadastroHospedeException(new CadastroHospedeException() + " " + new MenorDeIdadeException());
		}
	}
	
	//Atualizacao dos Dados
	//Nome
	public void exceptionAtualizaNomeHospede(String nome) throws AtualizaCadastroException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new  NomeHospedeException());
		}
	}
	
	public void exceptionAtualizaNomeHospedeInvalido(String nome) throws AtualizaCadastroException {
		this.exceptionAtualizaNomeHospede(nome);
		String[] str = nome.split(" ");
		for(int i = 0; i < str.length; i ++){
			if(!(str[i].matches("^\\A[a-zA-Z]*\\z$"))){
				throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new NomeHospedeInvalidoException());
			}
		}
	}
	//Email
	public void exceptionAtualizaEmailHospede(String email) throws AtualizaCadastroException {
		if (email == null || email.trim().isEmpty()) {
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " +  new EmailHospedeException() + " nao pode ser vazio.");
		}
	}
	
	public void exceptionAtualizaEmailFormat(String email) throws AtualizaCadastroException {
		this.exceptionAtualizaEmailHospede(email);
		if(!(email.matches("^\\A[a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+.[a-zA-Z]+\\z$"))){
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " +  new EmailHospedeException() + " esta invalido.");
		}
	}

	//Data
	public void exceptionAtualizaDataNascimentoHospede(String dataNascimento) throws AtualizaCadastroException  {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new DataNascimentoNullException());
		}
	}
	
	public void exceptionAtualizaDataNascimentoHospedeFormat(String dataNascimento) throws AtualizaCadastroException  {
		this.exceptionAtualizaDataNascimentoHospede(dataNascimento);
		if(!(dataNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$"))){
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new FormatoDataException());
		}
		String[] str = dataNascimento.split("/");
		if(Integer.parseInt(str[0]) <= 0 || Integer.parseInt(str[0])  > 31){
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new FormatoDataException());
		}
		
		if(Integer.parseInt(str[1]) <= 0 || Integer.parseInt(str[1]) > 12){
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new FormatoDataException());
		}
		if(Integer.parseInt(str[2]) <= 0 || Integer.parseInt(str[1]) > Calendar.getInstance().get(Calendar.YEAR)){
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " + new FormatoDataException());
		}
		
		
	}
	
	public void exceptionAtualizaMenorDeIdade(String dataNascimento) throws AtualizaCadastroException  {
		this.exceptionAtualizaDataNascimentoHospedeFormat(dataNascimento);
		String[] str = dataNascimento.split("/");
		if((Calendar.getInstance().get(Calendar.YEAR )- Integer.parseInt(str[2])) < 18){
			throw new AtualizaCadastroException(new AtualizaCadastroException() + " " +	new MenorDeIdadeException());
		}
	}
	
}
	
	
	
