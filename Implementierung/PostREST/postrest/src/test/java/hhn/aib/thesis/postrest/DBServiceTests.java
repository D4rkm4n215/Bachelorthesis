package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DBServiceTests {

	private static DBService db;

	public DBServiceTests() {
		try {
			db = new DBService();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetPerson() {
		long rand = (new Random().nextLong(100-1)+1);
		Person person = db.getPerson(rand);
        assertNotNull(person);
		assertEquals(person.id,rand);
	}

	@Test
	public void testGetIssue() {
		long rand = (new Random().nextLong(200-1)+1);
		Issue issue = db.getIssue(rand);
		assertNotNull(issue);
		assertEquals(issue.id,rand);
	}

	@Test
	public void testGetProject() {
		long rand = (new Random().nextLong(200-1)+1);
		Project project = db.getProject(rand);
		assertNotNull(project);
		assertEquals(project.id,rand);
	}

}
