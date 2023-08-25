package com.ezen.springmvc;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.web.PageParams;

import lombok.extern.slf4j.Slf4j;

/**
 * 게시판 테스트 클래스
 * @author 윤동진
 *
 */
@SpringBootTest
@Slf4j
public class ArticleMapperTest {
	@Autowired
	private ArticleMapper articleMapper;

	/** 게시글 등록 테스트*/
	@Test
	@Disabled
	@Transactional
	void createTest() {
		ArticleDTO article = ArticleDTO.builder()
									   .articleId(79)
									   .boardId(10)
									   .writer("monday")
									   .subject("팔월십팔일")
									   .content("방그리 신규글 내용입니다.")
									   .passwd("1234")
									   .groupNo(2)
									   .levelNo(0)
									   .orderNo(0)
									   .build();
		log.info(article.toString());
		articleMapper.create(article);
	}
/*
	/** 게시글 수정 테스트*/
	@Test
	@Disabled
	@Transactional
	void updateArticleTest() {
		ArticleDTO article = ArticleDTO.builder()
									   .articleId(9)
									   .subject("9번 게시글수정")
									   .build();
		articleMapper.updateArticle(article);
	}

	/** 게시글 삭제 전 orderNo 조정 테스트*/
	@Test
	@Disabled
	void beforeRemoveUpdateOrderNoTest() {
		articleMapper.beforeRemoveUpdateOrderNo(10, 1, 70);
	}
	
	/** 게시글 삭제 테스트*/
	@Test
//	@Disabled
	@Transactional
	void removeArticleTest() {
		ArticleDTO article = ArticleDTO.builder()
									      .passwd("1111")
									      .articleId(67)
									      .groupNo(67)
									      .levelNo(2)
									      .build();
		if(article.getLevelNo() > 0) {
			articleMapper.beforeRemoveUpdateOrderNo(10, 1, 72);
		}
		articleMapper.removeArticle(article);
	}

	/** 답글 작성 테스트 */
	@Test
	@Disabled
	void replyTest() {
		ArticleDTO article = ArticleDTO.builder()
//									   .articleId(69)  //시퀀스
									   .boardId(10)
									   .writer("thursday")
									   .subject("7월18일 답글")
									   .content("7월18일 답글 ")
									   .passwd("1234")
									   .groupNo(1)
									   .levelNo(0)
									   .orderNo(1)
									   .build();
		articleMapper.reply(article);
	}
	
	/** 대댓글 작성 전 orderNo 조정*/
	@Test
	@Disabled
	void updateOrderNoTest() {
		articleMapper.updateOrderNo(10, 1, 70);
	}
	
	/** 대댓글 작성*/
	@Test
	@Disabled
	void replyComentTest() {
		articleMapper.updateOrderNo(10, 1, 74);
		ArticleDTO article = ArticleDTO.builder()
									   .boardId(10)
									   .writer("thursday")
									   .subject("목요일 대댓글 제목 테스트")
									   .content("목요일 대댓글 내용 테스트")
									   .passwd("1234")
									   .groupNo(1)
									   .levelNo(2)
									   .articleId(74)
									   .build();
		articleMapper.replyComent(article);
	}
	
	/** 게시글 상세보기 테스트*/
	@Test
	@Disabled
	void openArticleTest() {
		ArticleDTO opened = null;
		opened = articleMapper.openArticle(2);
		}
	
	
	@Test
	@Disabled
	public void getCountAllTest() {
     PageParams pageParams = PageParams.builder()
						               .elementSize(10)
						               .pageSize(10)
						               .requestPage(1)
						               .rowCount(0)
						               .boardId(10)
						               .input(null)
						               .type(null)
						               .build();
       int count = articleMapper.getCountAll(pageParams);
       log.info("게시글 수 : {}", count);
  }

	@Test
	@Disabled
	public void findByAllTest() {
	   PageParams pageParams = PageParams.builder()
								         .elementSize(10)
								         .pageSize(10)
								         .requestPage(1)
								         .rowCount(0)
								         .boardId(20)
								         .input("yy")
								         .type("subject_content_writer")
								         .build();
	   List<ArticleDTO> articleDTO = articleMapper.findByAll(pageParams);
	   articleDTO.forEach( article -> {
	      log.info("검색된 게시글 : {}", article);
	   });
	}
	@Test
	@Disabled
	public void setHitcountTest() {
		articleMapper.setHitcount(2);
	}
}
