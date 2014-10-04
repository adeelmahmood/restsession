package restsession;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/session")
	public String session(HttpSession session, Principal principal){
		log.info(session.getId());
		while(session.getAttributeNames().hasMoreElements()){
			String name = session.getAttributeNames().nextElement();
			log.debug(name + " = " + session.getAttribute(name));
		}
		
		log.info(principal.toString());
		
		log.info("session is new " + session.isNew());
		
		return "done";
	}
}
