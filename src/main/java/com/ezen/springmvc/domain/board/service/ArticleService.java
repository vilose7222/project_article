package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.PageParams;
/**
 * 게시판 관련 비즈니스 로직 및 트랜젝션 처리
 * @author 윤동진
  
 */
@Service
public interface ArticleService {
	/**게시글 작성*/
	public void regist (ArticleDTO articleDTO);
	/**게시판 상세보기*/
	public ArticleDTO showArticle(int articleId);
	/**게시글 수정*/
	public void updateArticle(ArticleDTO articleDTO);
	/**게시글 삭제*/
	public boolean deleteArticle(ArticleDTO articleDTO);
	/**답글 및 대댓글 작성*/
	public void reply(ArticleDTO articleDTO);
	
	public List<ArticleDTO> findByBoardId(int boardId);
	
	public int getArticleCount(PageParams pageParams);
	
	/** 페이징관련 메서드... */   
	public List<ArticleDTO> getList(PageParams pageParams);
	
	/** 조회수 관리*/
	
	
	
	
}
