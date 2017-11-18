/**
 * 
 */
package co.com.sbaqueroa.gads.model.implementation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author sergio
 *
 */
@Entity
@Table(name="assigned_asset")
public class AssignedAsset{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,updatable=false)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id",nullable=false)
	private Asset asset;
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="person_id", nullable=true)
    private Person person;
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="area_id", nullable=true)
    private Area area;
	
	/**
	 * 
	 */
	public AssignedAsset() {
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
	 * @return the asset
	 */
	public Asset getAsset() {
		return asset;
	}
	/**
	 * @param asset the asset to set
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}
	
	
}
