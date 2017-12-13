package co.com.sbaqueroa.gads.webservices;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.webjars.RequireJS;

@Controller
public class BoardController {
	
	@RequestMapping(value = "/board/home", method = RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("panel");
		return mv;
	}
	
	@MessageMapping("/boardUpdate")
	@SendTo("/topic/updatingBoard")
	public String gettingData(String data) throws Exception{
		return data;
	}
	
}
