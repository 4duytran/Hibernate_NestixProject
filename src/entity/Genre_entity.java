package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "genre")
public class Genre_entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genre_id;
	
	@Column(name="genre_Nom")
	private String genre_name;
	
	@ManyToMany(mappedBy = "genres", cascade = CascadeType.PERSIST)
    private Set<Media_entity> medias = new HashSet<>();
	
	
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	public Set<Media_entity> getMedias() {
		return medias;
	}
	public void setMedias(Set<Media_entity> medias) {
		this.medias = medias;
	}
}
