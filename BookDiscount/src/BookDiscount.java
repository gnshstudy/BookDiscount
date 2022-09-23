
import java.util.*;

class BookDiscount {
	private static final int BOOK_PRICE = 50;
	private static Map<Integer, Double> discountPercentage = new HashMap<Integer, Double>();
	private double totalPrice;
	private Map<String, Integer> booksCountMap = new HashMap<String, Integer>();
	private List<Integer> discountSubsetList;

	public static void main(String[] args) {
		discountPercentage.put(1, 0.0);
		discountPercentage.put(2, 0.05);
		discountPercentage.put(3, 0.1);
		discountPercentage.put(4, 0.2);
		discountPercentage.put(5, 0.25);
		/**
		 * test case 1- 3 books with 2 copies and other 2 different books-expected
		 * output 320
		 */
//		List<String> list = Arrays.asList("A", "A", "B", "B", "C", "C", "D", "E");
		/** test case 2- Same book 2 copies -expected output 100 */
//		List<String> list = Arrays.asList("A", "A");
//		/** test case 3- 2 different books -expected output 95 */
//		List<String> list = Arrays.asList("A", "B");
//		/** test case 4- 3 different books -expected output 135 */
//		List<String> list = Arrays.asList("A", "B","C");
//		/** test case 5- 4 different books -expected output 160 */
//		List<String> list = Arrays.asList("A", "B","C","D");

//		/** test case 6- 5 different books -expected output 187.5 */
//		List<String> list = Arrays.asList("A", "B","C","D","E");

		/**
		 * test case 7- 4 books, of which 3 are different titles -expected output 185
		 */
		List<String> list = Arrays.asList("A", "B", "C", "A");

		BookDiscount bookDiscount = new BookDiscount();
		double calculateBasketCost = bookDiscount.calculateCost(list);
		System.out.println("Cost=" + calculateBasketCost);
	}

	public double calculateCost(List<String> books) {
		/**
		 * This will calculate key value pair map which consists of book name as a key
		 * and count of the values
		 */
		booksCountMap = getBooksCountMap(books);
		discountSubsetList = getFinalListForPriceCalculation(booksCountMap);
		for (Integer quantity : discountSubsetList) {
			totalPrice += BOOK_PRICE * quantity - ((BOOK_PRICE * quantity)) * this.discountPercentage.get(quantity);
		}
		return totalPrice;

	}

	public Map<String, Integer> getBooksCountMap(List<String> books) {
		booksCountMap = new HashMap<>();
		for (String book : books) {
			if (booksCountMap.containsKey(book)) {
				booksCountMap.put(book, booksCountMap.get(book) + 1);
			} else {
				booksCountMap.put(book, 1);
			}
		}
		return booksCountMap;
	}

	public List<Integer> getDiscountSubset(Map<String, Integer> booksCountMap) {
		discountSubsetList = new ArrayList<>();

		/**
		 * This logic will count discount subset For example if list is
		 * ("A","A","B","B","C","C","D","E") then discountSubsetList will have 5,3
		 */
		while (booksCountMap.size() > 0) {
			discountSubsetList.add(booksCountMap.size());
			Set<String> toBeRemoved = new HashSet<>();
			/**
			 * This for loop will iterate through booksCountMap if key(book) is present with
			 * key 1 then it will add in the toBeRemoved set
			 */

			for (String key : booksCountMap.keySet()) {
				if (booksCountMap.get(key) == 1)
					toBeRemoved.add(key);
				else
					booksCountMap.put(key, booksCountMap.get(key) - 1);
			}
			/**
			 * AFter loop ends all the elements in toBeRemoved set will be removed from
			 * booksCountMap
			 */
			booksCountMap.keySet().removeAll(toBeRemoved);
		}
		return discountSubsetList;
	}

	public List<Integer> getFinalListForPriceCalculation(Map<String, Integer> booksCountMap) {
		discountSubsetList = getDiscountSubset(booksCountMap);
		/**
		 * Below while loop will check if there is value 5 & 3 if yes then it will add 4
		 * & 4 to have maximum discount
		 */
		while (discountSubsetList.contains(3) && discountSubsetList.contains(5)) {
			discountSubsetList.remove(new Integer(3));
			discountSubsetList.remove(new Integer(5));
			discountSubsetList.add(4);
			discountSubsetList.add(4);
		}
		return discountSubsetList;
	}

}