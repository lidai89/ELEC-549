import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class GraphTest {

	@Test
	public void testPathLength01() throws IOException, InterruptedException {
		Graph record = new Graph("input-bacon.txt");
		assertEquals(1,record.pathLength("Bacon, Kevin", "Bennett, Gregory"));
	}
	
	@Test
	public void testPathLength02() throws IOException, InterruptedException {
		Graph record = new Graph("input-bacon.txt");
		assertEquals(1,record.pathLength("Bacon, Kevin", "Baur, Lisa"));
	}
	
	@Test
	public void testPathLength03() throws IOException, InterruptedException {
		Graph record = new Graph("input-bacon.txt");
		assertEquals(2,record.pathLength("Cooper, Maggie", "Abraham, Falconer"));
	}
	
	@Test
	public void testPathLength04() throws IOException, InterruptedException {
		Graph record = new Graph("input-bacon.txt");
		assertEquals(2,record.pathLength("Taylor, Elizabeth", "Warner, Rick"));
	}
	
	@Test
	public void testPathLength05() throws IOException, InterruptedException {
		Graph record = new Graph("input-bacon.txt");
		assertEquals(2,record.pathLength("Sherwood, Robin", "Groden, Robert J."));
	}
	
	@Test
	public void testPathLength06() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(1,record.pathLength("Ahrens, Melinda", "Beeler, David"));
	}
	
	@Test
	public void testPathLength07() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(1,record.pathLength("Bacon, Kevin", "Bowles, Sarah"));
	}
	
	@Test
	public void testPathLength08() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(1,record.pathLength("Bacon, Kevin", "Beech, Mark"));
	}
	
	@Test
	public void testPathLength09() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(2,record.pathLength("Beech, Mark", "Bowles, Sarah"));
	}
	
	@Test
	public void testPathLength10() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(3,record.pathLength("Hiller, Arthur", "Leal, Sharon"));
	}
	
	@Test
	public void testPathLength11() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(3,record.pathLength("Hiller, Arthur", "Reichle, Luke"));
	}
	
	@Test
	public void testPathLength12() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(4,record.pathLength("Hiller, Arthur", "Moran, Marcia"));
	}
	
	@Test
	public void testPathLength13() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(4,record.pathLength("Ley, Sascha", "Pizzarelli, John"));
	}
	
	@Test
	public void testPathLength14() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(4,record.pathLength("Ley, Sascha", "Griffith, Robin"));
	}
	
	@Test
	public void testPathLength15() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(4,record.pathLength("Langevin, Sophie", "Rainer, Pauline"));
	}
	
	@Test
	public void testPathLength16() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(4,record.pathLength("Langevin, Sophie", "Usher, Paul"));
	}
	
	@Test
	public void testPathLength17() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(5,record.pathLength("Langevin, Sophie", "Trani, Jessica"));
	}
	
	@Test
	public void testPathLength18() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(5,record.pathLength("Chumanov, Andrei", "Nelson, Craig T."));
	}
	
	@Test
	public void testPathLength19() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(5,record.pathLength("Chumanov, Andrei", "Svane, Toi"));
	}
	
	@Test
	public void testPathLength20() throws IOException, InterruptedException {
		Graph record = new Graph("input-year2000.txt");
		assertEquals(6,record.pathLength("Chumanov, Andrei", "Lazarus, Natalia"));
	}
}
