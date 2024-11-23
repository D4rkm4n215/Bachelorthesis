package hhn.aib.thesis.neo4rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@EnableTransactionManagement
@EnableNeo4jRepositories
@SpringBootApplication
public class Neo4restApplication {
	public static void main(String[] args) {
		SpringApplication.run(Neo4restApplication.class, args);
	}

}