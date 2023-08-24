 package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.springmvc.domain.board.dto.BoardDTO;

/**
 * 게시판 비즈니스로직 및 트랜젝션 관리
 * @author 윤동진
 */
@Service
public interface BoardService {
	/**게시판 생성*/
	/**게시판 목록 반환*/
	public List<BoardDTO> load(int boardId);
}
