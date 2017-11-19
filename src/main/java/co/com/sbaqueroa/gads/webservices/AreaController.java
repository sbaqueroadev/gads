package co.com.sbaqueroa.gads.webservices;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroa.gads.model.implementation.Area;
import co.com.sbaqueroa.gads.services.AreaImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AreaController {

	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private AreaImpl areaImpl;
	
	/**
	 * Redirects to /order/form as the home page.
	 * 
	 * @param request HTTP request to handle on.
	 * @param httpServletResponse HTTP response to return.
	 * 
	 * @return View represented by a JSP file.
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> home() {
		List<Area> result = areaImpl.getAll();
		if(result.size()>0)
			return ResponseEntity.ok(result);
		else{
			return ResponseEntity.status(404).body(new JSONObject()
					.put("result", "fail")
					.put("msg", "No data").toString());
		}
	}
	
	@RequestMapping(value="/area/home",method = {RequestMethod.GET})
	public ModelAndView form(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("area/record");
		List<Area> data =  areaImpl.getAll();
		if(data.size()>0)
			mv.addObject("data",data);
		return mv;
	}

	public Area get(int id) {
		Area area = new Area();
		area.setId(id);
		return areaImpl.getById(area);
	}

}
