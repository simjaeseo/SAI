package com.ssafy.sai.db.repository;

import com.ssafy.sai.db.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {
}
