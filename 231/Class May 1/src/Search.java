import java.util.ArrayList;
import java.util.Comparator;

public class Search {
	ArrayList<Object> list;
	
	public Search(ArrayList<Object> list) {
		this.list = list;
	}
	
	public Object linSearch(Object find) {
		for(Object e: this.list) {
			if(e == find) {
				return e;
			}
		}
		
		return null;
	}
	
	public void Sort() {
		PQHeap<Object> sort = new PQHeap<Object>(new Search.objectComp());
		for(Object e: sort) {
			sort.add(e);
		}
		ArrayList<Object> max = new ArrayList<Object>();
		for(int i = sort.size(); i >=0; i--) {
			max.add(i, sort.remove());
		}
		this.list = max;
	}
	
	
	public Object binSearch(Object find) {
		Sort();
		int index = this.list.size()/2;
		if(this.list.get(index) == find) {
			return this.list.get(index);
		}
		
		/*if() {
			ArrayList<Object> newL = new ArrayList<Object>();
			
		}*/
	}
	
 static class objectComp implements Comparator<Integer>{
	public int compare(Integer a, Integer b)  {
		if(a < b) {
			return -1;
		}else if(a > b) {
			return 1;
		}
		return 0;
	 }
 }
	

	
	
	
	public static void main(String[] args) {
		
	}


}
