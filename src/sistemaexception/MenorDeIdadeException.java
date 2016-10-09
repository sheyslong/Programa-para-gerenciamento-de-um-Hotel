package sistemaexception;

@SuppressWarnings("serial")
public class MenorDeIdadeException extends EntradaException{
	
	public MenorDeIdadeException(){
		super("A idade do(a) hospede deve ser maior que 18 anos.");
	}
	
	public MenorDeIdadeException(String mensagem){
		super(mensagem);
	}
}
