/**
 * 
 */
package com.assignment.wordcount.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.wordcount.service.WordcountService;

//import ch.qos.logback.classic.Logger;

/**
 * @author Shalini Dixit
 *
 */
@RestController
public class WordcountController {

	@Autowired
	private WordcountService wordcount_service;

	private static final Logger LOGGER = LoggerFactory.getLogger(WordcountController.class);

	/**
	 * @param inputstring to be sorted
	 * @param sort_order asc/ASC or desc/DESC
	 * @return sorted word map
	 */
	@GetMapping("/getWordCount/{inputstring}/{sort_order}")
	public ResponseEntity<Object> getWordCountService(@PathVariable String inputstring,
			@PathVariable String sort_order) {

		LOGGER.info("Inside wordcount Controller Method");

		ResponseEntity<Object> response = null;
		Map<String, Integer> sorted_wordmap = null;

		sorted_wordmap = wordcount_service.sortWords(inputstring.trim(), sort_order);
		if (sorted_wordmap == null) {
			response = new ResponseEntity<Object>("Empty String", HttpStatus.OK);
		} else {
			response = new ResponseEntity<Object>(sorted_wordmap, HttpStatus.OK);
		}

		LOGGER.info(response.toString());
		return response;
	}

}
