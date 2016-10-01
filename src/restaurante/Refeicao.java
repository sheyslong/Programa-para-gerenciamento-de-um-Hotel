package restaurante;

import java.util.ArrayList;

import sistemaexception.CadastroDeRefeicaoVazioException;
import sistemaexception.ComponenteRefeicaoVazioException;
import sistemaexception.NumeroDePratosRefeicaoException;
import sistemaexception.PratoInexistenteRefeicaoException;

public class Refeicao extends ItensDoCardapio{

	private ArrayList<String> componentes;
	
	public Refeicao(String nome, String descricao, ArrayList<String> componentes) throws Exception {
		super(nome, descricao);
		
		verificaNome(nome);
		verificaDescricao(descricao);
		this.componentes = componentes;
	}
	
	private void verificaNome(String nome) throws Exception{
		if (nome == null || nome.trim().isEmpty()){
			throw new CadastroDeRefeicaoVazioException("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		}
	}
	
	private void verificaDescricao(String descricao) throws Exception{
		if (descricao == null || descricao.trim().isEmpty()){
			throw new CadastroDeRefeicaoVazioException("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		}
	}

	public ArrayList<String> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<String> componentes) {
		this.componentes = componentes;
	}
	
	public String getDescricao() {
		return toString();
	}
	
	public double getPreco() {
		return 1;
	}

	@Override
	public String getPrecoString() {
		return null;
	}
	
	private String toStringComponentes(){
		String impressao = "";
		for (int i = 0; i < componentes.size(); i++) {
			impressao += ", ";
			impressao += "(" + (i + 1) + ") " + componentes.get(i);
		}
		impressao = impressao.substring(2);
		return impressao;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((componentes == null) ? 0 : componentes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Refeicao other = (Refeicao) obj;
		if (componentes == null) {
			if (other.componentes != null)
				return false;
		} else if (!componentes.equals(other.componentes))
			return false;
		return true;
	}
	
	@Override 
	public String toString(){
		return this.getDescricaoItem() + " Serao servidos: " + toStringComponentes() + ".";				
	}
}
