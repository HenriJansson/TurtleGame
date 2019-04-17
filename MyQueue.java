import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


// Detta är en egenskriven kö klass sedan tidigare, ville använda denna så jag kunde vara flexibel med metoderna. 

public class MyQueue<E> {

	private int cap;
	private Node<E> first = null;
	private Node<E> last = null;
	private int size = 0;

	public MyQueue(int i) {
		if (i < 1) {
			throw new IllegalArgumentException();
		}
		cap = i;
	}


	public void add(E element) {
		Node<E> lastAdded = new Node<E>(element);
		 if (size >= cap) {
		 throw new IllegalStateException();
		 }
		if (element == null) {
			throw new NullPointerException();
		}
		if (first == null) {
			first = lastAdded;
			last = lastAdded;
			size++;
		} else {
			last.next = lastAdded;
			last = lastAdded;
			size++;
			
		}
	}


	public void addAll(Collection<? extends E> c) {
		Iterator<? extends E> iter = c.iterator();
		if ((c.size() + size) > cap) {
			throw new IndexOutOfBoundsException();
		} else {
			while (iter.hasNext()) {
				add(iter.next());
				iter.remove();
			}
		}
	}

	public E remove() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Node<E> removable = first;
		if (first.next != null) {
			first = first.next;
		} else {
			first = null;
			last = null;
		}
		size--;
		return removable.data;
	}


	public E peek() {
		if (first == null) {
			return null;
		}
		return first.data;

	}


	public void clear() {
		first = null;
		last = null;
		size = 0;
	}


	public int size() {
		return size;
	}


	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}


	public boolean isFull() {
		if (size == cap) {
			return true;
		}
		return false;
	}


	public int totalCapacity() {
		return cap;
	}


	public int currentCapacity() {
		return cap - size;
	}


	public int discriminate(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			return 0;
		}
		Iterator<E> iter = iterator();
		int moved = 0;
		MyQueue<E> moveQueue = new MyQueue<E>(size);
		Iterator<E> moveIter = moveQueue.iterator();

		while (iter.hasNext()) {
			E current = iter.next();
			if (current.equals(e)) {
		
				iter.remove();
				size--;
				moveQueue.add(e);
				moved++;

			}
		}

		while (moveIter.hasNext()) {
			E moveData = moveIter.next();
			add(moveData);
		}
		return moved;
	}


	public Iterator<E> iterator() {
		return new MyQueueIterator();

	}

	private class MyQueueIterator implements Iterator<E> {

		Node<E> currNode = null;
		Node<E> lastNode = null;
		boolean removed = false;


		public boolean hasNext() {
			if (currNode == null && first != null) {
				return true;
			} else if (currNode != null && currNode.next != null) {
				return true;
			}
			return false;
		}


		public E next() {
			if (currNode == null && first != null) {
				currNode = first;
				return currNode.data;
			} else if (currNode != null && currNode.next != null && !removed) {
				lastNode = currNode;
				currNode = currNode.next;
				return currNode.data;
			} else if (currNode != null && currNode.next != null) {
				removed = false;
			currNode = currNode.next;
			return currNode.data;
		}
			throw new NoSuchElementException();
		}


		public void remove() {
			
			if (lastNode != null) {
				if(currNode.next == null) {
				last = lastNode;
				}
				lastNode.next = currNode.next;

				}
			if (lastNode == null) {
				first = currNode.next;
				
			}
			removed = true;
		}

	}

	public String toString() {

		if (size == 0) {
			return "[]";
		}
		boolean firstPrint = true;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			if (firstPrint == false) {
				sb.append(", ");
			}
			sb.append(iter.next().toString());
			firstPrint = false;
		}
		sb.append("]");
		return sb.toString();
	}

	private static class Node<E> {
		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
		}
	}

}
