package com.cck.wxtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cck.wxtest.entity.Topic;
import com.cck.wxtest.repository.TopicDao;

@RestController
@RequestMapping(value = "/wechat/topic")
public class TopicController {

	@Autowired
	private TopicDao topicDao;

	private final int pageSize = 5;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list() {
		PageRequest pageRequest = new PageRequest(0, pageSize, Direction.DESC, "createDate");
		Page<Topic> page = topicDao.findAll(pageRequest);
		ModelAndView mav = new ModelAndView("/topic/topic_list");
		mav.addObject("topics", page.getContent());
		mav.addObject("totalCount", page.getTotalElements());
		return mav;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public List<Topic> list(@RequestParam(value = "currentPage") Integer currentPage) {
		PageRequest pageRequest = new PageRequest(currentPage, pageSize, Direction.DESC, "createDate");
		Page<Topic> page = topicDao.findAll(pageRequest);
		return page.getContent();
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public ModelAndView add(Topic topic) {
		topicDao.save(topic);
		ModelAndView mav = new ModelAndView("redirect:/wechat/topic/list");
		return mav;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView("/topic/topic_add");
		return mav;
	}

}
