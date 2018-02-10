package fr.esiag.ing3.grid_project;

import java.io.IOException;
import java.security.Principal;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import fr.esiag.ing3.zookeeper.ZooKeeperConnection;

@Controller
public class DefaultController {

	ZooKeeperConnection connection;
	
    @GetMapping("/")
    public String home1() {
        return "login";
    }

    @GetMapping("/user")
    public String user(ModelMap model, Principal principal) throws IOException, InterruptedException, KeeperException {
    	String name = principal.getName(); //get logged in username
    	//Connection au cluster zookeeper
    	connection=new ZooKeeperConnection();
    	connection.connect("192.168.4.221:2181,192.168.4.222:2182,192.168.4.223:2183");
    	//creation du ZNode contenant le login du user
    	String znodeName="/znode_"+name;
    	//Si le node n'a pas été crée, on le crée
    	if (connection.getZoo().exists(znodeName, true) == null){
    		connection.getZoo().create(znodeName, name.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    	}
    	
        return "/user";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}