package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="level")
public class Level_entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="level_Id")
	private Integer levelId;
	
	@Column(name="level_Nom")
	private String levelName;

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
}
