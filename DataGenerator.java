import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {

	LinkedListMultiset<String> linkedListMultiset = new LinkedListMultiset<String>();
	SortedLinkedListMultiset<String> sortedLinkedListMultiset = new SortedLinkedListMultiset<String>();
	BstMultiset<String> bstMultiset = new BstMultiset<String>();
	HashMultiset<String> hashMultiset = new HashMultiset<String>();
	BalTreeMultiset<String> balTreeMultiset = new BalTreeMultiset<String>();
	ArrayList<String> testDataset = new ArrayList<String>();
	
	/**
	 * initial multiset size
	 */
	public static final int Initial_Size = 10000;
	/**
	 * running addition operation times during current test case.
	 */
	public static  int Addition_Times = 10000;
	/**
	 * running removel operation times during current test case.
	 */
	public static  int Removel_Times = 10000;
	/**
	 * running search operation times during current test case.
	 */
	public static  int Search_Times = 10000;
	
	public Multiset multiset = new LinkedListMultiset<String>();
//	public Multiset multiset = new SortedLinkedListMultiset<String>();
//	public Multiset multiset = new BstMultiset<String>();
//	public Multiset multiset = new HashMultiset<String>();
//	public Multiset multiset = new BalTreeMultiset<String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Data generator running...");
		new DataGenerator().generate();
	}
	
	public void generate(){
	
		init();
		operate();
	}
	
	private void init(){
		System.out.println("step 1 : Initialize multiset: randomly add " + Initial_Size + " nodes into multiset...");
		for(int i = 0 ; i < Initial_Size; i ++){
			multiset.add(getRandomItem());
		}
	}
	private void operate(){
		System.out.println("step 2 : Setup items & operations...");
		System.out.println("Addition_Times = " + Addition_Times);
		System.out.println("Removel_Times = " + Removel_Times);
		System.out.println("Search_Times = " + Search_Times);
		int total = Addition_Times + Removel_Times + Search_Times;
		ArrayList<String> items = new ArrayList<String>();
		ArrayList<Integer> operations = new ArrayList<Integer>();
		for(int i = 0 ; i < total ; i ++){
			items.add(getRandomItem());
			operations.add(getRandomOperation());
		}
		System.out.println("step 3 : generate data...");
		long time = System.currentTimeMillis();
		for(int i = 0 ; i < total ; i ++){
			String item = items.get(i);
			switch(operations.get(i)){
			case 0:
				multiset.add(item);
				break;
			case 1:
				multiset.removeAll(item);
				break;
			case 2:
				multiset.search(item);
				break;
			}
		}
		time = System.currentTimeMillis() - time;
		
		System.out.println("cost time : " + time + " ms.");
	}
	
	
	private String getRandomItem(){
		int randomNum = new Random().nextInt(1000000);
		return Integer.toString(randomNum);
	}
	
	private int getRandomOperation(){
		
		ArrayList<Integer> oops = new ArrayList<Integer>();
		if(Addition_Times > 0){
			oops.add(0);
		}
		if(Removel_Times > 0){
			oops.add(1);
		}
		if(Search_Times > 0){
			oops.add(2);
		}
		int randomIndex = new Random().nextInt(oops.size());
		int randomOperation = oops.get(randomIndex);
		if(randomOperation == 0){
			Addition_Times--;
		}else if(randomOperation == 1){
			Removel_Times--;
		}else if(randomOperation == 2){
			Search_Times--;
		}
	
		return randomOperation;
	}

}