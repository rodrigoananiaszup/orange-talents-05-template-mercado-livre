package br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica;

import javax.validation.constraints.NotBlank;

public class CaracteristicaDto {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicaDto other = (CaracteristicaDto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Caracteristica toModel() {

		return new Caracteristica(this.nome, this.descricao);

	}
}
