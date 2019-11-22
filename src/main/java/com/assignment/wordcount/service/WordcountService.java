package com.assignment.wordcount.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

/**
 * @author Shalini Dixit
 *
 */
/**
 * @author kl
 *
 */
@Service
public class WordcountService {

	/**
	 * @param inputString
	 * @param sort_order
	 * @return Map of words with their occurrences This method find number of
	 *         occurrences of all words in the inputString & then sort the word
	 *         based on occurrences according to sort_order If sort_order is asc/ASC
	 *         & desc/DESC then sorting is done in ascending or descending
	 *         respectively but in case of garbage value natural ordering i.e
	 *         Ascending order is used
	 */
	public Map<String, Integer> sortWords(String inputString, String sort_order) {
		Map<String, Integer> WordMap = new HashMap<String, Integer>();
		String[] words = inputString.replace(".", "").replace(",", "").replace("!", "").replace("?", "").split("\\s+");
		if (inputString.length() == 0) {
			return null;
		}
		for (String word : words) {
			Integer count = WordMap.get(word.toLowerCase());
			count = (count == null) ? 1 : ++count;
			WordMap.put(word.toLowerCase(), count);
		}

		Map<String, Integer> sorted_WordMap = null;
		if (sort_order.equalsIgnoreCase("DESC")) {
			DescendingComparator desc_comparator = new DescendingComparator(WordMap);
			sorted_WordMap = new TreeMap<String, Integer>(desc_comparator);

		} else {

			AscendingComparator asc_comparator = new AscendingComparator(WordMap);
			sorted_WordMap = new TreeMap<String, Integer>(asc_comparator);

		}

		sorted_WordMap.putAll(WordMap);
		return (sorted_WordMap);

	}

}

/**
 * 
 * For ordering words in DESCENDING order according to occurences
 */
class DescendingComparator implements Comparator<String> {

	Map<String, Integer> base;

	public DescendingComparator(Map<String, Integer> base) {
		this.base = base;
	}

	public int compare(String a, String b) {
		if (base.get(a) > base.get(b)) {
			return -1;
		} else if (base.get(a) < base.get(b)) {
			return 1;
		} else {
			if(a.charAt(0) >= b.charAt(0)) {
				return -1;
			} else {
				return 1;
			}
			
		}
	}
}

/**
 * For ordering words according to occurences in ASCENDING order 
 *
 */
class AscendingComparator implements Comparator<String> {

	Map<String, Integer> base;

	public AscendingComparator(Map<String, Integer> base) {
		this.base = base;
	}

	public int compare(String a, String b) {
		if (base.get(a) > base.get(b)) {
			return 1;
		} else if (base.get(a) < base.get(b)) {
			return -1;
		} else {
			if(a.charAt(0) >= b.charAt(0)) {
				return 1;
			} else {
				return -1;
			}
			
		}
	}
}
