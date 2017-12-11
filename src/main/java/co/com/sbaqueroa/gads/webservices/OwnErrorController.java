package co.com.sbaqueroa.gads.webservices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OwnErrorController implements ErrorController{

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public ModelAndView error(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/error");
		mv.addObject("data",res.getStatus());
		return mv;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}
