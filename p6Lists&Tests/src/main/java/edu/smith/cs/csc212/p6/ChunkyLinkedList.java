package edu.smith.cs.csc212.p6;


import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

/**
 * This is a data structure that has an array inside each node of a Linked List.
 * Therefore, we only make new nodes when they are full. Some remove operations
 * may be easier if you allow "chunks" to be partially filled.
 * 
 * @author jfoley
 * @param <T> - the type of item stored in the list.
 */
public class ChunkyLinkedList<T> implements P6List<T> {
	private int chunkSize;
	private SinglyLinkedList<FixedSizeList<T>> chunks;
	
	public ChunkyLinkedList(int chunkSize) {
		
		this.chunkSize = chunkSize;
		chunks=new SinglyLinkedList<FixedSizeList<T>>();
		this.chunks.addBack(new FixedSizeList<>(chunkSize));
	}

	// O(n)
	@Override
	public T removeFront() {
		
		T value=this.getIndex(0);
		this.removeIndex(0);
		return value;
	}

	
	//O(n)
	@Override
	public T removeBack() {
		// if there's no element in the first chunk yet..
		this.checkEmpty();
		
		T value=this.getIndex(this.size()-1);// O(n)
		this.removeIndex(this.size()-1);// O(n)
		return value;
		
	}

	// O(n)	
	@Override
	public T removeIndex(int index) {
		
		this.checkEmpty();
		/**
		 * a counter for indexing elements in the list
		 */
		int count = 0;
		/**
		 * count for chunk index
		 */
//		int chunkCount=0;
		for (FixedSizeList<T> chunk : this.chunks) {
			// check the size of the current chunk
			int end = count + chunk.size();
			
			// Check whether the index should be in this chunk:	
			if (count <= index && index < end) {
				T value=chunk.getIndex(index-count);
				chunk.removeIndex(index-count);
				// if we removed the last element in the chunk, also remove the chunk
//				if(chunk.isEmpty()) {
//					this.chunks.removeIndex(chunkCount);
//				}	
				return value;

			}
			
			// update bounds of next chunk.
			count = end;

//			chunkCount++;
		}
		
		throw new BadIndexError();
		
	}

	// O(1)
	@Override
	public void addFront(T item) {
		
		this.addIndex(item, 0);
		
	}

	// O(n)
	@Override
	public void addBack(T item) {

		this.addIndex(item,this.size());
			
	}

	//O(n)
	@Override
	public void addIndex(T item, int index) {
		
		// if the list is empty, create a chunk and put the item in it
		if(this.hasChunk()==false) {//O(1)=>SinglyLinkedList just checks if it has a ndoe
			
			FixedSizeList<T> firstNode=new FixedSizeList<T>(this.chunkSize);
			firstNode.addFront(item);
			this.chunks.addFront(firstNode);
			
			return;
			
		}
		
		
		/**
		 * a counter for indexing elements in the list
		 */
		int ElementCount = 0;
		/**
		 * a counter for chunk, will be used when we try to insert new chunks
		 */
		int chunkCount=0;
		
		for (FixedSizeList<T> chunk : this.chunks) {//O(n)

			/**
			 * At what index does this chunk ends
			 */
			// ends is always 1 bigger than the index of the latest element
			int end = ElementCount + chunk.size();

			// if the target index is in the current chunk:
			if (ElementCount <= index && index <=end) {
			
				// if the chunk is not full
				if(chunk.size()!=this.chunkSize) {
					chunk.addIndex(item, index-ElementCount);
					return;
				}
				// if the chunk is full
				// create a new chunk, insert it after the current chunk
				else {
					// create a new chunk 
					FixedSizeList<T> newChunk = new FixedSizeList<T>(this.chunkSize);
					
					// if we want to add the new element before the last position of the current chunk,
					// we have to move the last element of the current chunk to the new chunk,
					// move all elements from the target position rightward for one position
					// then put the new element to the target position.
					if(index!=end) {
						
						// put the last element of the previous chunk into this new chunk
						newChunk.addFront(chunk.getIndex(chunk.size()-1));
						// insert after the current chunk
						chunks.addIndex(newChunk, chunkCount+1);
						
						// move every item after the target index one position rightward.
						for(int i=chunk.size()-1;i>index-ElementCount;i--) {   
							chunk.removeIndex(i);
							chunk.addIndex(chunk.getIndex(i-1), i);
						}
						// remove the elements at the target position and add the new element
						chunk.removeIndex(index-ElementCount);
						chunk.addIndex(item, index-ElementCount);
						
						return;
					}
					// else if we want to add the new element at the end of the current array
					// then we just put it into the new chunk
					else{
						// put the last element of the previous chunk into this new chunk
						newChunk.addFront(item);
						// insert after the current chunk
						chunks.addIndex(newChunk, chunkCount+1);
						return;
					}
				}
			}


			
			// update bounds of next chunk.
			ElementCount = end;
			// update the chunk index
			chunkCount++;
		
		}
		throw new BadIndexError();
		
	}
	
	
	
	// O(1) 
	@Override
	public T getFront() {
		// returns the first element
		return this.chunks.getFront().getFront();
	}

	
	// O(n) -> O(n) for SinglyLiknedList.getBack() and O(1) for FixedLengthList.getBack()
	@Override
	public T getBack() {
		
		//returns the last element
		return this.chunks.getBack().getBack();
	}


	// O(n)
	@Override
	public T getIndex(int index) {
		this.checkEmpty();
		
		int start = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			// calculate bounds of this chunk.
			int end = start + chunk.size();
			
			// Check whether the index should be in this chunk:
			if (start <= index && index < end) {
				return chunk.getIndex(index - start);
			}
			
			// update bounds of next chunk.
			start = end;
		}

		throw new BadIndexError();
	}

	@Override
	// O(n)
	public int size() {
		int total = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			total += chunk.size();
		}
		return total;
	}

	@Override
	// O(1)
	public boolean isEmpty() {
		return this.chunks.isEmpty();
	}
	
	public void visualize() {
		
		int chunkCount=0;
		
		for (FixedSizeList<T> chunk:this.chunks){
			System.out.println("Chunk: "+ chunkCount);
			for(int i=0; i<chunk.size();i++) {
				System.out.println(i+"th, value: "+chunk.getIndex(i));
			}
			chunkCount++;

		}
		System.out.println("=================END===============");
	}

	
	/**
	 * Checks if there's no element in the list
	 * @return 
	 */
	// O(n)
	private boolean hasElements() {
		
		return this.size()!=0;

	}
	
	
	//O(n)
	/**
	 * Checks if there's no chunk in the list
	 */
	private boolean hasChunk() {
		
		return this.chunks.size()!=0;
		
	}
	
	
	/**
	 * checks emptiness and throws exception
	 */
	public void checkEmpty() {
		if (this.hasChunk()==false||this.hasElements()==false) {
			throw new EmptyListError();
		}
	}

}
