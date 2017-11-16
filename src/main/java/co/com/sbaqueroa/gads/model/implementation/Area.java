/**
 * 
 */
package co.com.sbaqueroa.gads.model.implementation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
public class Area {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,updatable=false)
	private int id;
	@Column(name="name",columnDefinition="VARCHAR(45)",nullable=false)
	private String name;
	@OneToOne(cascade=CascadeType.ALL,targetEntity=City.class)
	@JoinColumn(name="city_id",columnDefinition="INT(11)",nullable=false)
	private City city;
	/**
	 * 
	 */
	public Area() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
