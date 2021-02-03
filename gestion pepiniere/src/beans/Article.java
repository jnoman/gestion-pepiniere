package beans;

import java.io.InputStream;

public class Article {
	int id;
	String nom;
	String description;
	InputStream image;
	int quantite;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public Article(int id, String nom, String description, InputStream image, int quantite) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.image = image;
		this.quantite = quantite;
	}
	
	public Article(String nom, String description, InputStream image, int quantite) {
		super();
		this.nom = nom;
		this.description = description;
		this.image = image;
		this.quantite = quantite;
	}
}
