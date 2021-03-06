package edu.smith.cs.csc212.p6;

import org.junit.Test;

import edu.smith.cs.csc212.p6.errors.EmptyListError;

import org.junit.Assert;

public class ChunkyLinkedListTest {
	@Test
	public void testEmpty() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		Assert.assertEquals(0, data.size());
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveFrontCrash() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		data.removeFront();
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveBackCrash() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		data.removeBack();
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveIndexCrash() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		data.removeIndex(3);
	}

	@Test
	public void testGetFrontGetBack() {
		
		P6List<String> data = new ChunkyLinkedList<String>(3);
		data.addFront("1");
		Assert.assertEquals("1", data.getFront());
		Assert.assertEquals("1", data.getBack());
		data.addBack("2");
		data.addFront("0");
		data.addBack("3");

		Assert.assertEquals("2", data.getIndex(2));
		Assert.assertEquals("0", data.getFront());
		Assert.assertEquals("3", data.getBack());
	}
	
	@Test
	public void testAddToFront() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		data.addFront("1");
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("1", data.getIndex(0));
		data.addFront("0");
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("0", data.getIndex(0));
		Assert.assertEquals("1", data.getIndex(1));
		data.addFront("-1");
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("-1", data.getIndex(0));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(2));
		data.addFront("-2");
		Assert.assertEquals("-2", data.getIndex(0));
		Assert.assertEquals("-1", data.getIndex(1));
		Assert.assertEquals("0", data.getIndex(2));
		Assert.assertEquals("1", data.getIndex(3));
	}
	
	@Test
	public void testAddToBack() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		data.addBack("1");

		Assert.assertEquals(1, data.size());
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("0");

		Assert.assertEquals(2, data.size());
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("-1");

		Assert.assertEquals(3, data.size());
		Assert.assertEquals("-1", data.getIndex(2));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("-2");

		Assert.assertEquals("-2", data.getIndex(3));
		Assert.assertEquals("-1", data.getIndex(2));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		
	}
	
	/**
	 * Helper method to make a full list.
	 * @return
	 */
	public P6List<String> makeAList() {
		P6List<String> data = new ChunkyLinkedList<String>(3);
		//((ChunkyLinkedList<String>) data).visualize();
		data.addBack("a");
		//((ChunkyLinkedList<String>) data).visualize();
		data.addBack("b");
		//((ChunkyLinkedList<String>) data).visualize();
		data.addBack("c");
		//((ChunkyLinkedList<String>) data).visualize();
		data.addBack("d");
		//((ChunkyLinkedList<String>) data).visualize();

		return data;
	}
	

	@Test
	public void testRemoveFront() {
		P6List<String> data = makeAList();
		//((ChunkyLinkedList<String>) data).visualize();
		Assert.assertEquals(4, data.size());
		Assert.assertEquals("a", data.removeFront());
		//((ChunkyLinkedList<String>) data).visualize();
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("b", data.removeFront());
		//((ChunkyLinkedList<String>) data).visualize();
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("c", data.removeFront());
		//((ChunkyLinkedList<String>) data).visualize();

		Assert.assertEquals(1, data.size());
		Assert.assertEquals("d", data.removeFront());
		
		//((ChunkyLinkedList<String>) data).visualize();
		Assert.assertEquals(0, data.size());

	}
	@Test
	public void testRemoveBack() {
		P6List<String> data = makeAList();
		Assert.assertEquals(4, data.size());
		Assert.assertEquals("d", data.removeBack());
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("c", data.removeBack());
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("b", data.removeBack());
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("a", data.removeBack());
		Assert.assertEquals(0, data.size());
	}
	
	@Test
	public void testRemoveIndex() {
		P6List<String> data = makeAList();
		Assert.assertEquals(4, data.size());
		Assert.assertEquals("c", data.removeIndex(2));
		//((ChunkyLinkedList<String>) data).visualize();

		Assert.assertEquals(3, data.size());
		Assert.assertEquals("d", data.removeIndex(2));
		//((ChunkyLinkedList<String>) data).visualize();

		Assert.assertEquals(2, data.size());
		Assert.assertEquals("b", data.removeIndex(1));
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("a", data.removeIndex(0));
		Assert.assertEquals(0, data.size());
	}
}
