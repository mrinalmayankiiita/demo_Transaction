package com.example.mongoDbTransactions.configuration.launcher;

import com.example.mongoDbTransactions.configuration.TempApiConfig;
import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan(
		basePackages = {"com.example"},
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.example\\..*launcher\\..*")
		})
@EnableAutoConfiguration(
		exclude = {
				MongoAutoConfiguration.class,
				MongoDataAutoConfiguration.class,
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				WebMvcAutoConfiguration.class,
				DispatcherServletAutoConfiguration.class
		})
@EnableTransactionManagement
@Configuration
public class MongodbTransactionsApplication {
	String mongoAtlasUrl = "mongodb+srv://mayank:mayank1234@clustermongotransaction.qgexs.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MongodbTransactionsApplication.class);
		springApplication.setAdditionalProfiles("local");
		springApplication.run(args);
	}
	@Bean
	public MongoClient mongoClient(){
		return MongoClients.create(mongoAtlasUrl);
	}

	@Bean
	public MongoTemplate mongoTemplate(){
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(),"testDb");
		mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS); // hear the exceptions while writing in mongodb
		// mongoTemplate.setWriteResultChecking();
		return mongoTemplate;
	}
	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoClientDbFactory(mongoClient(), "testDb");
	}

	@Bean
	MongoTransactionManager mongoTransactionManager(MongoDbFactory mongoDbFactory) {
		TransactionOptions transactionOptions = TransactionOptions.builder()
				.readConcern(ReadConcern.MAJORITY)
				.writeConcern(WriteConcern.MAJORITY)
				.build();
		MongoTransactionManager transactionManager = new MongoTransactionManager(mongoDbFactory, transactionOptions);
		return transactionManager;
	}

	@Bean
	public DispatcherServletRegistrationBean configApi() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		AnnotationConfigWebApplicationContext applicationContext =
				new AnnotationConfigWebApplicationContext();
		applicationContext.register(TempApiConfig.class);
		dispatcherServlet.setApplicationContext(applicationContext);
		DispatcherServletRegistrationBean servletRegistrationBean =
				new DispatcherServletRegistrationBean(dispatcherServlet, "/u/*");
		servletRegistrationBean.setName("DemoApi");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

}
