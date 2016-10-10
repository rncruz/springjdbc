package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.ImportResource;
import java.util.List;

@SpringBootApplication
@ImportResource("classpath:datasources.xml")	//defaults to src/main/resources directory
public class Application implements CommandLineRunner {

	//local class objects
    @Autowired JdbcTemplate CLEAR_PROD;	
    private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);		
    }

    @Override    
	public void run(String... strings) throws Exception {
		System.out.println("Hello, world!");
		
		String query = "SELECT TOP 100 [DAY_OF_WEEK] FROM [CaseWiseCMI].[dbo].[rptEvolveLogins]";
		List<String> strings2 = (List<String>) CLEAR_PROD.queryForList(query, String.class);
		for (String item : strings2) { System.out.println(item); }			
	}
}
