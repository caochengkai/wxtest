package com.cck.wxtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cck.wxtest.entity.Topic;
import com.cck.wxtest.repository.TopicDao;

@RestController
@RequestMapping(value = "/wechat/topic")
public class TopicController {

	@Autowired
	private TopicDao topicDao;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list() {
		Topic entity = new Topic();
		topicDao.save(entity);
		List<Topic> topics = topicDao.findAll();
		ModelAndView mav = new ModelAndView("/topic/topic_list");
		mav.addObject("topics", topics);
		return mav;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public void add() {
		Topic topic = new Topic();
		topic.setTitle("one");
		topic.setContent("one");
		topicDao.save(topic);
	}

}
