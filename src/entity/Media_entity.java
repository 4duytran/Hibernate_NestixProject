package entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

@Entity(name="media")
public class Media_entity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer media_id;
	
	@Column(name = "media_Titre")
	private String media_title;
	
	@Column(name = "media_AnneeSortie")
	private Integer media_year;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="typeMedia_Id")
	private MediaType_entity mediaType;
	
	@Column(name="livre_ISBN")
	private Long isbn;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="saga_Id")
	private Saga_entity saga;

	@Column(name = "valid", insertable=false)
	private boolean media_valid ;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(
			name =  "media_genre",
			joinColumns  = {@JoinColumn(name="media_Id")},
			inverseJoinColumns = {@JoinColumn(name="genre_Id" )}
			)
	private Set<Genre_entity> genres = new HashSet<>();
	

	@OneToMany(fetch = FetchType.LAZY, cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(
			name ="role_artiste",
			joinColumns = {@JoinColumn(table="media",name="media_Id")},
			inverseJoinColumns = {@JoinColumn(name="metier_Id")}
			)
	@MapKeyJoinColumn(name = "artiste_Id")
	private Map<Artist_entity, Job_entity> artist_job = new HashMap<>();
	

	@ManyToMany(mappedBy = "medias", cascade = CascadeType.PERSIST)
    private Set<Artist_entity> artists = new HashSet<>();
    
    
	public Set<Artist_entity> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artist_entity> artists) {
		this.artists = artists;
	}

	public Map<Artist_entity, Job_entity> getArtist_job() {
		return artist_job;
	}

	public void setArtist_job(Map<Artist_entity, Job_entity> artist_job) {
		this.artist_job = artist_job;
	}

	
	public Set<Genre_entity> getGenre() {
		return genres;
	}
	
	public Integer getMedia_id() {
		return media_id;
	}
	public void setMedia_id(Integer media_id) {
		this.media_id = media_id;
	}
	public String getMedia_title() {
		return media_title;
	}
	public void setMedia_title(String media_title) {
		this.media_title = media_title;
	}
	
	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	
	public Integer getMedia_year() {
		return media_year;
	}
	public void setMedia_year(Integer media_year) {
		this.media_year = media_year;
	}
	public MediaType_entity getMedia_type() {
		return mediaType;
	}
	public void setMedia_type(MediaType_entity mediaType) {
		this.mediaType = mediaType;
	}
	public boolean getMedia_valid() {
		return media_valid;
	}
	public void setMedia_valid(boolean media_valid) {
		this.media_valid = media_valid;
	}
	
	
	public void addGenre(Genre_entity genre) {
        genres.add(genre);
        genre.getMedias().add(this);
    }
	
	public void removeGenre(Genre_entity genre) {
        this.genres.remove(genre);
        genre.getMedias().remove(this);
    }
	
	public Saga_entity getSaga() {
		return saga;
	}
	public void setSaga(Saga_entity saga) {
		this.saga = saga;
	}

//	@Override
//	  public String toString() {
//	    return "(Media title) " + getMedia_title() + " (year) " +getMedia_year() + " (type media) " + mediaType.getMediaType_name() + " (Valid) " + getMedia_valid();
//	  }
	
}
