package co.com.sbaqueroa.gads.webservices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.services.AssetImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AssetController {

	private static final Logger logger = LoggerFactory.getLogger(AssetController.class);

	@Autowired
	private AssetImpl assetImpl;
	
	/**
	 * Redirects to /order/form as the home page.
	 * 
	 * @param request HTTP request to handle on.
	 * @param httpServletResponse HTTP response to return.
	 * 
	 * @return View represented by a JSP file.
	 */
	@RequestMapping(value = "/asset", method = RequestMethod.GET)
	public @ResponseBody List<Asset> home(HttpServletRequest request, HttpServletResponse httpServletResponse,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="buyDate",required=false) String buyDate,
			@RequestParam(value="serial",required=false) String serial
			) {
		//logger.debug(""+assetImpl.getAll().size());
		//System.out.println(""+new AssetImpl().getAll().size());
		if(serial!=null)
			return assetImpl.getAllByFieldValue(Asset.SERIAL_FIELD, serial);
		if(type!=null)
			return assetImpl.getAllByFieldValue(Asset.TYPE_FIELD, type);
		if(buyDate!=null)
			return assetImpl.getAllByFieldValue(Asset.BUY_DATE_FIELD, buyDate);
		return assetImpl.getAll();
	}
	
	@RequestMapping(value = "/asset", method = RequestMethod.POST)
	public @ResponseBody String add(HttpServletRequest request, HttpServletResponse httpServletResponse,
			@RequestBody Asset asset) {
		JSONObject result = new JSONObject();
		if(assetImpl.add(asset)){
			return result.put("result", "OK").toString();
		}else
			return result.put("result", "fail").toString();
	}
	
	@RequestMapping(value = "/asset", method = RequestMethod.PUT)
	public @ResponseBody String update(HttpServletRequest request, HttpServletResponse httpServletResponse,
			@RequestBody Asset asset) {
		JSONObject result = new JSONObject();
		if(assetImpl.update(asset)){
			return result.put("result", "OK").toString();
		}else
			return result.put("result", "fail").toString();
	}

}
