package com.cck.wxtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/wechat/topic")
public class TopicController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list() {
		return null;
	}

}
