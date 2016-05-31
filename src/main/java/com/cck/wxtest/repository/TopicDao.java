package com.cck.wxtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cck.wxtest.entity.Topic;

public interface TopicDao extends JpaRepository<Topic, String> {

}
