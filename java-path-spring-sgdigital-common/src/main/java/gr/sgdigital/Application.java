package gr.sgdigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	ErrorMvcAutoConfiguration.class,
	SqlInitializationAutoConfiguration.class,
	JpaRepositoriesAutoConfiguration.class,
	DataSourceAutoConfiguration.class,
	DataSourceTransactionManagerAutoConfiguration.class,
	HibernateJpaAutoConfiguration.class
})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}



