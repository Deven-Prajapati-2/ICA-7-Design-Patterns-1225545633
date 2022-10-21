public class OfferingIterator implements ListIterator {
	private OfferingList offeringList;
	private int index = 0;
	public OfferingIterator(OfferingList list) {
		offeringList = list;
	}
	public boolean hasNext() {
		return (index < offeringList.size());
	}
	public Object next() {
		if(hasNext()) {
			return offeringList.get(index++);
		} else {
			return null;
		}
	}
	public void remove(int index) {
		offeringList.remove(index);
	}
	public void moveToHead() {
		index = 0;
	}
}
