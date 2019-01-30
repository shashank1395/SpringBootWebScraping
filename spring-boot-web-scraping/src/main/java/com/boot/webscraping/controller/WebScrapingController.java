package com.boot.webscraping.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boot.webscraping.model.NewScrapperItem;
import com.boot.webscraping.service.ScrapperService;


@Controller
@SessionAttributes("name")
public class WebScrapingController {
	private final Logger logger = LoggerFactory.getLogger(WebScrapingController.class);
	
	@Autowired
    ScrapperService service;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		logger.debug("index()");
		model.addAttribute("attr1", new NewScrapperItem());
		model.addAttribute("attr2", new NewScrapperItem());
		model.addAttribute("attr3", new NewScrapperItem());
		return "index";
	}
	
	@RequestMapping(value = "/getAuthors", method = RequestMethod.GET)
	public String getAuthors(ModelMap model) {
		model.addAttribute("authorsList", service.getAuthors());
		return "all-authors";
	}
	
	@RequestMapping(value = "/searchAuthors", method = RequestMethod.POST)
	public String searchAuthors(@ModelAttribute("attr2") NewScrapperItem item, ModelMap model) {
		model.addAttribute("titleList", service.searchAuthors(item.getAuthor()));
		return "title";
	}
	
	@RequestMapping(value = "/searchTitle", method = RequestMethod.POST)
	public String searchTitle(@ModelAttribute("attr2") NewScrapperItem item, ModelMap model) {
		model.addAttribute("total", service.searchTitle(item.getTitle()));
		return "final";
	}
}
