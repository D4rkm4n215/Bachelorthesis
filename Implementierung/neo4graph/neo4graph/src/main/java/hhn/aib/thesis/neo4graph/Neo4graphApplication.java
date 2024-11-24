package hhn.aib.thesis.neo4graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.neo4j.core.convert.Neo4jConversions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class Neo4graphApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4graphApplication.class, args);
	}

}

