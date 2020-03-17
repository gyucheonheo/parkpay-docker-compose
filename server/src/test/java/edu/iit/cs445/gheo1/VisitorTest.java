package edu.iit.cs445.gheo1;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.iit.cs445.gheo1.boundary.VisitorBoundary;
import edu.iit.cs445.gheo1.manager.VisitorManager;
import edu.iit.cs445.gheo1.visitor.Visitor;

public class VisitorTest {
	private VisitorBoundary vi = new VisitorManager();
  @Before
  public void setup(){
    vi.getAllVisitor().clear();
  }
  @Test
	public void create_visitor() {
		vi.createVisitor("vid", "tester", "tester@gmail.com");
		Visitor v = new Visitor("vid", "tester", "tester@gmail.com");
		assertEquals(v.getVid(), vi.getAllVisitor().get(0).getVid());
		assertEquals(v.getName(), vi.getAllVisitor().get(0).getName());
		assertEquals(v.getEmail(), vi.getAllVisitor().get(0).getEmail());
	}

  @Test
	public void visitor_manager_get_visitor_by_keyword() {
    String keyword = "tester";
		vi.createVisitor("vid", "tester", "tester@gmail.com");

		List<Visitor> result = vi.getVisitorByKeyword(keyword);
    assertEquals("vid", result.get(0).getVid());
    assertEquals("tester", result.get(0).getName());
    assertEquals("tester@gmail.com", result.get(0).getEmail());
	}

  @Test
  public void visitor_manager_get_visitor_by_vid() {
		vi.createVisitor("vidTest", "tester", "tester@gmail.com");
    Visitor result = vi.getVisitorByVid("vidTest");

    assertEquals("vidTest", result.getVid());
    assertEquals("tester", result.getName());
    assertEquals("tester@gmail.com", result.getEmail());
  }

  @Test
  public void visitor_manager_find_visitor_by_email() {
		vi.createVisitor("vid", "tester", "tester@gmail.com");
    Visitor result = vi.findVisitorByEmail("tester@gmail.com");

    assertEquals("vid", result.getVid());
    assertEquals("tester", result.getName());
    assertEquals("tester@gmail.com", result.getEmail());
  }

}
