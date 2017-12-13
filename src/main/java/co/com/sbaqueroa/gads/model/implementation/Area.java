/**
 * 
 */
package co.com.sbaqueroa.gads.model.implementation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author sergio
 * Implementation of Area Model.
 *
 */
@Entity
@Table(name="area")
public class Area {
	/**
	 * Area id.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,updatable=false)
	private int id;
	/**
	 * Area name.
	 */
	@Column(name="name",columnDefinition="VARCHAR(45)",nullable=false)
	private String name;
	/**
	 * Area city.
	 */
	@OneToOne(cascade=CascadeType.ALL,targetEntity=City.class)
	@JoinColumn(name="city_id",columnDefinition="INT(11)",nullable=false)
	private City city;
	/**
	 * Area asigned asset references.
	 */
	@OneToMany(mappedBy="area",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<AssignedAsset> assignedAssets = new HashSet<AssignedAsset>();
	/**
	 * Super class constructor.
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
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	/**
	 * @return the assignedAssets
	 */
	public Set<AssignedAsset> getAssignedAssets() {
		return assignedAssets;
	}
	/**
	 * @param assignedAssets the assignedAssets to set
	 */
	public void setAssignedAssets(Set<AssignedAsset> assignedAssets) {
		this.assignedAssets = assignedAssets;
	}
	
	
}
