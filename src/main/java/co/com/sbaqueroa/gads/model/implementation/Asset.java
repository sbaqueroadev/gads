/**
 * 
 */
package co.com.sbaqueroa.gads.model.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author sergio
 * Implementation of Asset Model.
 *
 */
@Entity
@Table(name="asset")
public class Asset {

	/**
	 * @author sergio
	 * Asset status options.
	 */
	public static enum AssetStatus{
		ACTIVE(1), CANCELED(2), IN_REPAIR(3), AVAILABLE(4), ASSIGNED(5);
		
		private int id;

		/**
		 * @param id the id to be set.
		 */
		private AssetStatus(int id){
			this.id = id;
		}
		
		/**
		 * @return the id.
		 */
		public int getId(){
			return this.id;
		}
		
		/**
		 * @return all Asset status options.
		 */
		public static List<AssetStatus> getAll(){
			ArrayList<AssetStatus> result = new ArrayList<AssetStatus>();
			for(AssetStatus a : AssetStatus.values())
				result.add(a);
			return result;
			
		}
		
	};
	/**
	 * Serial field constant
	 */
	public static final String SERIAL_FIELD = "serial";
	/**
	 * Buy date field constant
	 */
	public static final String BUY_DATE_FIELD = "buy_date";
	/**
	 * type field constant
	 */
	public static final String TYPE_FIELD = "type";
	/**
	 * Id field constant
	 */
	public static final String ID_FIELD = "id";
	/**
	 * Asset id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,updatable=false)
	private int id;
	/**
	 * Asset name
	 */
	@Column(name="name",columnDefinition="VARCHAR(45)",nullable=false)
	private String name;
	/**
	 * Asset type.
	 */
	@Column(name=TYPE_FIELD,columnDefinition="VARCHAR(45)",nullable=false)
	private String type;
	/**
	 * Asset serial.
	 */
	@Column(name=SERIAL_FIELD,columnDefinition="VARCHAR(45)",nullable=false)
	private String serial;
	/**
	 * Asset inventory number.
	 */
	@Column(name="inventory_number",columnDefinition="VARCHAR(45)",nullable=false)
	private String inventoryNumber;
	/**
	 * Asset status
	 */
	@Column(name="status",columnDefinition="VARCHAR(15)",nullable=false)
	private String status;
	/**
	 * Asset color
	 */
	@Column(name="color",columnDefinition="VARCHAR(20)",nullable=false)
	private String color;
	/**
	 * Asset weight.
	 */
	@Column(name="weight",columnDefinition="DECIMAL(11,2)")
	private float weight;
	/**
	 * Asset height.
	 */
	@Column(name="height",columnDefinition="DECIMAL(11,2)")
	private float height;
	/**
	 * Asset width.
	 */
	@Column(name="width",columnDefinition="DECIMAL(11,2)")
	private float width;
	/**
	 * Asset height.
	 */
	@Column(name="length",columnDefinition="DECIMAL(11,2)")
	private float length;
	/**
	 * Asset buy price.
	 */
	@Column(name="buy_price",columnDefinition="DECIMAL(11,2)",nullable=false)
	private float buyPrice;
	/**
	 * Asset buy date.
	 */
	@Column(name=BUY_DATE_FIELD,columnDefinition="DATE",nullable=false)
	private Date buyDate;
	/**
	 * Asset withdrawal date.
	 */
	@Column(name="withdrawal_date",columnDefinition="DATE")
	private Date withdrawalDate;
	
	/**
	 * Asset assignment reference.
	 */
	@OneToOne(mappedBy = "asset", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = true)
	@JsonManagedReference
	private AssignedAsset assignedAsset;
	
	/**
	 * Super class constructor
	 */
	public Asset() {
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}
	/**
	 * @param serial the serial to set
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
	/**
	 * @return the inventoryNumber
	 */
	public String getInventoryNumber() {
		return inventoryNumber;
	}
	/**
	 * @param inventoryNumber the inventoryNumber to set
	 */
	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}
	/**
	 * @return the buyPrice
	 */
	public float getBuyPrice() {
		return buyPrice;
	}
	/**
	 * @param buyPrice the buyPrice to set
	 */
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	/**
	 * @return the buyDate
	 */
	public Date getBuyDate() {
		return buyDate;
	}
	/**
	 * @param buyDate the buyDate to set
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	/**
	 * @return the withdrawalDate
	 */
	public Date getWithdrawalDate() {
		return withdrawalDate;
	}
	/**
	 * @param withdrawalDate the withdrawalDate to set
	 */
	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	/**
	 * @return the assignedAsset
	 */
	public AssignedAsset getAssignedAsset() {
		return assignedAsset;
	}
	/**
	 * @param assignedAsset the assignedAsset to set
	 */
	public void setAssignedAsset(AssignedAsset assignedAsset) {
		this.assignedAsset = assignedAsset;
	}
	
	
}
