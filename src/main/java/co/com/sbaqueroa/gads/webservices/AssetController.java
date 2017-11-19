package co.com.sbaqueroa.gads.webservices;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroa.gads.model.implementation.Area;
import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.model.implementation.Asset.AssetStatus;
import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;
import co.com.sbaqueroa.gads.model.implementation.Person;
import co.com.sbaqueroa.gads.services.AssetImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AssetController {

	private static final Logger logger = LoggerFactory.getLogger(AssetController.class);

	@Autowired
	private AssetImpl assetImpl;
	
	@Autowired
	private PersonController personController;
	
	@Autowired
	private AreaController areaController;

	/**
	 * Redirects to /order/form as the home page.
	 * 
	 * @param request HTTP request to handle on.
	 * @param httpServletResponse HTTP response to return.
	 * 
	 * @return View represented by a JSP file.
	 */
	@RequestMapping(value = "/asset", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> home(
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="buyDate",required=false) String buyDate,
			@RequestParam(value="serial",required=false) String serial
			) {
		//logger.debug(""+assetImpl.getAll().size());
		//System.out.println(""+new AssetImpl().getAll().size());
		List<Asset> result;
		if(serial!=null)
			result = assetImpl.getAllByFieldValue(Asset.SERIAL_FIELD, serial);
		if(type!=null)
			result = assetImpl.getAllByFieldValue(Asset.TYPE_FIELD, type);
		if(buyDate!=null)
			result = assetImpl.getAllByFieldValue(Asset.BUY_DATE_FIELD, buyDate);
		result = assetImpl.getAll();
		if(result.size()>0)
			return ResponseEntity.ok(result);
		
		else{
			return ResponseEntity.status(404).body(new JSONObject()
					.put("result", "fail")
					.put("msg", "No data").toString());
		}
		
	}

	@RequestMapping(value = "/asset", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> add(@RequestBody Asset asset) {
		JSONObject result = new JSONObject();
		if(assetImpl.add(asset)){
			return ResponseEntity.ok(result.put("result", "OK").toString());
		}else
			return ResponseEntity.status(400).body(result
					.put("result", "fail")
					.put("msg", "Error creating asset.").toString());
	}

	@RequestMapping(value = "/asset", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> update(@RequestBody Asset asset) {
		JSONObject result = new JSONObject();
		List<Asset> consult = assetImpl.getAllByFieldValue(Asset.ID_FIELD, ""+asset.getId());
		if(consult.size()>0){
			Asset entity = consult.get(0);
			entity.setBuyDate(asset.getBuyDate());
			entity.setBuyPrice(asset.getBuyPrice());
			entity.setColor(asset.getColor());
			entity.setHeight(asset.getHeight());
			entity.setInventoryNumber(asset.getInventoryNumber());
			entity.setLength(asset.getLength());
			entity.setName(asset.getName());
			entity.setStatus(asset.getStatus());
			entity.setType(asset.getType());
			entity.setWeight(asset.getWeight());
			entity.setWidth(asset.getWidth());
			entity.setWithdrawalDate(asset.getWithdrawalDate());
			if(asset.getAssignedAsset()!=null){
				if(asset.getAssignedAsset().getPerson()!=null)
					if(asset.getAssignedAsset().getPerson().getId()>0){
						Person person = personController.get(
								asset.getAssignedAsset().getPerson().getId());
						if(person!=null){
							entity.setAssignedAsset(asset.getAssignedAsset());
							entity.getAssignedAsset().setAsset(entity);
							entity.getAssignedAsset().setPerson(person);
							entity.getAssignedAsset().setArea(null);
						}
					}
				if(asset.getAssignedAsset().getArea()!=null)
					if(asset.getAssignedAsset().getArea().getId()>0){
						Area area = areaController.get(
								asset.getAssignedAsset().getArea().getId());
						if(area!=null){
							entity.setAssignedAsset(
									asset.getAssignedAsset());
							entity.getAssignedAsset().setAsset(entity);
							entity.getAssignedAsset().setArea(area);
							entity.getAssignedAsset().setPerson(null);
						}
					}
			}
			if(entity.getAssignedAsset()!=null)
				entity.setStatus(""+AssetStatus.ASSIGNED.getId());
			else
				entity.setStatus(""+AssetStatus.AVAILABLE.getId());
			if(assetImpl.update(entity)){
				return ResponseEntity.ok(result.put("result", "OK").toString());
			}else
				return ResponseEntity.status(400).body(result
						.put("result", "fail")
						.put("msg", "Error updating asset.").toString());
		}else
			return ResponseEntity.status(400).body(result
					.put("result", "fail")
					.put("msg", "Asset doesn't exist.").toString());
	}

	@RequestMapping(value="/asset/record",method = {RequestMethod.GET})
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("asset/record");
		List<Asset> data = assetImpl.getAll();
		if(data.size()>0)
			mv.addObject("data",data);
		return mv;
	}

	@RequestMapping(value="/asset/add",method = {RequestMethod.GET})
	public ModelAndView addForm(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("asset/add");
		mv.addObject("assetStatus", AssetStatus.getAll());
		return mv;
	}

	@RequestMapping(value="/asset/update",method = {RequestMethod.GET})
	public ModelAndView updateForm(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("asset/update");
		mv.addObject("assetStatus", AssetStatus.getAll());
		return mv;
	}



}
