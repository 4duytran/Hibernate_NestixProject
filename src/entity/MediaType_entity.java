package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// SQL table name
@Entity(name="type_media")
public class MediaType_entity {

	@Id  // Unique key
	@GeneratedValue (strategy = GenerationType.IDENTITY) // Auto increase
	@Column(name="typeMedia_Id") // SQL column name
	private Integer mediaType_id;
	
	@Column(name="typeMedia_Nom")
	private String mediaType_name;
	
	public Integer getMediaType_id() {
		return mediaType_id;
	}
	public void setMediaType_id(Integer mediaType_id) {
		this.mediaType_id = mediaType_id;
	}
	public String getMediaType_name() {
		return mediaType_name;
	}
	public void setMediaType_name(String mediaType_name) {
		this.mediaType_name = mediaType_name;
	}
}
