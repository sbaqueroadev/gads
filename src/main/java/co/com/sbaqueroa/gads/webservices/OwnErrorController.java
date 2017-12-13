package co.com.sbaqueroa.gads.webservices;

import javax.servlet.http.HttpServletRequest;
import static co.com.sbaqueroa.gads.security.SecurityConstants.SIGN_IN_URL;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OwnErrorController implements ErrorController{

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public ModelAndView error(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/error");
		mv.addObject("data",res.getStatus());
		/*if(res.getStatus()==403 && req.getMethod().toLowerCase().equals("get")){
			res.sendRedirect(SIGN_IN_URL);
		}*/
		return mv;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}
