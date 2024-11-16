package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DBServiceTests {

	@Autowired
	private DBService db;

	public DBServiceTests(DBService db) {
			this.db = db;
	}

	@Test
	public void testGetPerson() {
		long rand = (new Random().nextLong(100-1)+1);
		Person person = db.getPerson(rand);
        assertNotNull(person);
		assertEquals(person.getPid(),rand);
	}



}
