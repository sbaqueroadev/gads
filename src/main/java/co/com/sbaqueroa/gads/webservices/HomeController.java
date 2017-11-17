package co.com.sbaqueroa.gads.webservices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.services.AssetImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody Asset home(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		logger.debug(""+assetImpl.getAll().size());
		System.out.println(""+new AssetImpl().getAll().size());
		return new Asset();
	}

}
