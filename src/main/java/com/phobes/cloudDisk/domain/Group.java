package com.phobes.cloudDisk.domain;

import java.util.ArrayList;
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
public class Group {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private ArrayList<User> users;
} // class User
