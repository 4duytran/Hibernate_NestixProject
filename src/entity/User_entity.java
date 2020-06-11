package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity(name="utilisateur") // Name of sql table
@PrimaryKeyJoinColumn(name = "utilisateur_Id") // Primary key SQL

public class User_entity  extends Person_entity{

	@Column(name="utilisateur_EMail") // Name of SQL column
	private String userEmail;
	
	@Column(name="utilisateur_Password") // Name of SQL column
	private String usePassword;
	
	@Column(name="utilisateur_DateCreation", insertable=false)  // Name of SQL column with param optional for insert statement
	@Temporal(TemporalType.TIMESTAMP) // Value Timestamp in SQL
	@CreationTimestamp // Auto insertion with time
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)  // Ralation manyToOne with param fetch Lazy 
	@JoinColumn(name="level_Id") // Name of the SQL colomn for jointure
	private Level_entity level;

	@Column(name="utilisateur_Bloque", insertable=false)
	private Boolean blocked;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUsePassword() {
		return usePassword;
	}

	public void setUsePassword(String usePassword) {
		this.usePassword = usePassword;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Level_entity getLevel() {
		return level;
	}

	public void setLevel(Level_entity level) {
		this.level = level;
	}
}
