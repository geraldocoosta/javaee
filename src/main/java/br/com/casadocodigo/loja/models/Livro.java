package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank
	private String titulo;

	@Lob
	@NotNull
	@NotBlank
	@Length(min = 10)
	private String descricao;

	@NotNull
	@DecimalMin("20")
	private BigDecimal preco;

	@NotNull
	@Min(80)
	private Integer numeroDePaginas;

	@Temporal(TemporalType.DATE)
	private Calendar dataPublicacao = Calendar.getInstance();

	@ManyToMany
	@Size(min = 1, max = 255)
	@NotNull
	private List<Autor> autores = new ArrayList<>();
	
	
	private String capaPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@Override
	public String toString() {
		return "Livro [id=" + getId() + ", titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco
				+ ", numeroDePaginas=" + numeroDePaginas + ", dataPublicacao=" + dataPublicacao + ", autores=" + autores
				+ "]";
	}

	public void setCapaPath(String capaPath) {
		this.capaPath = capaPath;
	}

	public String getCapaPath() {
		return capaPath;
	}
}
