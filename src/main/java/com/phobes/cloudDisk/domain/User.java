package com.phobes.cloudDisk.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 * An entity User composed by three fields (id, telephone, name).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 *
 * @author phobes
 */
@Entity
@Table(name = "users")
public class User {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @NotNull
  private String username;
  @NotNull
  private String password;
  @Enumerated( EnumType.STRING)
  private Role role;
  // The user's telephone
  @NotNull
  private String telephone;
  // The user's username
  @NotNull
  private int total_space;
  @NotNull
  private int used_space;
  @NotNull
  @Temporal(TemporalType.DATE)
  private Date regist_time;
  //@NotNull
 // private Date purchase_due;
  @NotNull
  private int state;
  @NotNull
  private int illeage_num;
  // ------------------------
  // PUBLIC METHODS
  // ------------------------


  public User() { }

  public User(long id) { 
    this.id = id;
  }
  
  public User(String telephone, String username, String password) {
    this.telephone = telephone;
    this.username = username;
    this.password = password;
  }

  // Getter and setter methods
  
  	public String getUsername() {
  		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getTotal_space() {
		return total_space;
	}
	
	public void setTotal_space(int total_space) {
		this.total_space = total_space;
	}
	
	public int getUsed_space() {
		return used_space;
	}
	
	public void setUsed_space(int used_space) {
		this.used_space = used_space;
	}
	
	public Date getRegist_time() {
		return regist_time;
	}
	
	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}
	
	
	public int getState() {
		return state;
	}
	//用户状态，0 表示可以上传下载，1表示禁止
	public void setState(int state) {
		this.state = state;
	}
	
	public int getIlleage_num() {
		return illeage_num;
	}
	
	public void setIlleage_num(int illeage_num) {
		this.illeage_num = illeage_num;
	}
  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

  public String getTelephone() {
    return telephone;
  }
  
  public void setTelephone(String value) {
    this.telephone = value;
  }
  
  public String getPassword() {
	return password;
  }
	
  public void setPassword(String password) {
	this.password = password;
  }

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

} // class User
