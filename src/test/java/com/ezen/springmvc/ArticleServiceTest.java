package com.ezen.springmvc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

/**
 * 서비스 구현체 테스트 클래스
 * @author 윤동진
 */
@SpringBootTest
@Slf4j
public class ArticleServiceTest {
	@Autowired
	private ArticleService articleService;
	
	@Test
	@Disabled
	@Transactional
	@DisplayName("게시글 생성 테스트")
	public void registTest() {
		ArticleDTO article = ArticleDTO.builder()
//				   .articleId(68)
				   .boardId(10)
				   .writer("bangry")
				   .subject("67번에 대한 답글")
				   .content("테스트 게시글 내용입니다. 아이디는 방그리")
				   .passwd("1111")
//				   .groupNo(67)
				   .levelNo(1)
				   .orderNo(12)
				   .build();
		articleService.regist(article);
	}
	
	@Test
	@Disabled
	@Transactional
	@DisplayName("게시글 상세보기 테스트")
	public void showArticleTest() {
		ArticleDTO opened = null;
		opened = articleService.showArticle(78);
		log.info(opened.toString());
	}
	
	
	@Test
	@Disabled
	@Transactional
	@DisplayName("게시글 수정 테스트")
	public void updateArticleTest() {
		ArticleDTO article = ArticleDTO.builder()
									   .articleId(9)
									   .subject("9번  게시글 서비스 테스트로 수정내용")
									   .build();
		articleService.updateArticle(article);
	}
	
	@Test
//	@Disabled
//	@Transactional
	@DisplayName("게시글 삭제 테스트")
	public void deleteArticleTest() {
		ArticleDTO article = ArticleDTO.builder()
			      .passwd("111")
			      .articleId(198)
			      .groupNo(157)
			      .levelNo(3)
			      .build();
	   articleService.deleteArticle(article);
	}
	
	@Test
	@Disabled
	@Transactional
	@DisplayName("답글, 대댓글 생성 테스트")
	public void replyTest() {
		ArticleDTO article = ArticleDTO.builder()
				   .boardId(10)
				   .writer("thursday")
				   .subject("8월22일 대댓글 제목 테스트")
				   .content("8월22일 대댓글 내용 테스트")
				   .passwd("1234")
				   .groupNo(1)
				   .levelNo(1)
				   .articleId(145)
				   .build();
		articleService.reply(article);
	}
	
	
	
	
}
