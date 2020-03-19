package edu.iit.cs445.gheo1;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.visitor.Visitor;
import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import edu.iit.cs445.gheo1.boundary.VisitorBoundary;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.manager.VisitorManager;
import edu.iit.cs445.gheo1.manager.ParkManager;
import edu.iit.cs445.gheo1.comment.Comment;
import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.manager.CommentManager;
import edu.iit.cs445.gheo1.park.locationinfo.Geo;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.CarPayment;
import edu.iit.cs445.gheo1.park.payment.MotorCyclePayment;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.park.payment.RvPayment;

public class CommentTest{
  private static CommentBoundary ci = new CommentManager();
  private Park p;

  @Before
  public void setup(){
    ci.getComments().clear();
  }

  @Test
  public void create_comment(){
    String vid="vid";
    String pid="pid";

    Comment c = ci.createComment(vid, pid, "title", "text");

    assertEquals("vid", c.getVid());
    assertEquals("pid", c.getPid());
    assertEquals("title", c.getTitle());
    assertEquals("text", c.getText());

  }

  @Test
  public void update_comment(){
    String vid="vid";
    String pid="pid";

    Comment c = ci.createComment(vid, pid, "title", "text");

    ci.updateCommentByNid(c.getNid(), "vid", "update", "update");

    assertEquals("update", c.getTitle());
    assertEquals("update", c.getText());

  }

  	@Test
  	public void comment_manager_get_comment_by_pid_and_nid() {
      String vid="vid";
      String pid="pid2";

      Comment c = ci.createComment(vid, pid, "title", "text");
  		Comment result = ci.getCommentByPidandNid(pid, c.getNid());

  		assertEquals(vid, result.getVid());
  		assertEquals(pid, result.getPid());
  		assertEquals("title", result.getTitle());
  		assertEquals("text", result.getText());
  	}

  	@Test
  	public void comment_manager_get_comment_by_keyword() {
  		String keywordForSuccess = "KeyWord";

      String vid="vid";
      String pid="pid3";

      Comment c = ci.createComment(vid, pid, "keyword title", "keyword text");

  		JsonArray result = ci.getCommentByKeyword(keywordForSuccess);

      assertEquals(pid, result.get(0).getAsJsonObject().get("pid").getAsString() );
      }

    @Test
    public void comment_manager_get_comment_by_vid() {
      String vid="vidTest";
      String pid="pid3";

      Comment c = ci.createComment(vid, pid, "vid title", "vid text");
      List<Comment> result = ci.getCommentByVid(vid);

      assertEquals("vid title", result.get(0).getTitle());
      assertEquals("vid text", result.get(0).getText());
      assertEquals(vid, result.get(0).getVid());
      assertEquals(pid, result.get(0).getPid());
    }

}
