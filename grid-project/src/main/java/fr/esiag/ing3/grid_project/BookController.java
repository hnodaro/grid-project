package fr.esiag.ing3.grid_project;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.esiag.ing3.zookeeper.ZooKeeperConnection;

@Controller
public class BookController {
	ArrayList<Object> arrayB;
	ZooKeeperConnection connection;
	
	@GetMapping("/book")
    public String greetingForm(Model model) throws KeeperException, InterruptedException, IOException, ClassNotFoundException {
		arrayB=new ArrayList<Object>();
        model.addAttribute("book", new Book());
        connection=new ZooKeeperConnection();
    	connection.connect("192.168.4.221:2181,192.168.4.222:2182,192.168.4.223:2183"); 
       
        List<String> children=connection.getZoo().getChildren("/book", false);
        for (int i=0;i<children.size();i++){
        	System.out.println(children.get(i));
        	byte[] dataBook=connection.getZoo().getData("/book/"+children.get(i), null, null);
        	//Deserialization
        	Object data= (Book)Serializer.deserializeBytes(dataBook);
        	arrayB.add(data);
        }
        System.out.println(arrayB);
        model.addAttribute("books",arrayB);
        return "book";
    }

    @PostMapping("/book")
    public String greetingSubmit(@ModelAttribute Book book) throws KeeperException, InterruptedException, IOException {
    	System.out.println(book);
    	//Connection au cluster zookeeper
    	connection=new ZooKeeperConnection();
    	connection.connect("192.168.4.221:2181,192.168.4.222:2182,192.168.4.223:2183");
    	//creation du ZNode contenant le login du user
    	String znodeName="/book/znode_"+book.getTitle().trim().replace(" ", "_");
    	
    	//Serialization
    	byte[] bookData = Serializer.serializeObject( book);
    	//Si le node n'a pas été crée, on le crée
    	if (connection.getZoo().exists(znodeName, true) == null){
    		connection.getZoo().create(znodeName, bookData, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	}
    	
        return "book";
    }

}
