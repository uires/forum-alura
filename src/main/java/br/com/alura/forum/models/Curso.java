package br.com.alura.forum.models;

public class Curso {
	private int id;
	private String titulo;
	private String categoria;

	public Curso() {
	}

	public Curso(int id, String titulo, String categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
	}

	public Curso(String titulo, String categoria) {
		this.titulo = titulo;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Curso other = (Curso) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", categoria=" + categoria + "]";
	}
}