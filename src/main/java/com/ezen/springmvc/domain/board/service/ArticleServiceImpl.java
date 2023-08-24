package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.web.PageParams;
import com.ezen.springmvc.domain.common.web.Pagination;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
	private Pagination page;
	private final ArticleMapper articleMapper;
/**=======================게시판 CRUD=================================*/	
	@Override
	@Transactional
	public void regist(ArticleDTO articleDTO) {
		articleMapper.create(articleDTO);
	}

	@Override
	@Transactional
	public ArticleDTO showArticle(int articleId) {
		 ArticleDTO article = articleMapper.openArticle(articleId);
		 return article;
		
	}

	@Override
	@Transactional
	public void updateArticle(ArticleDTO articleDTO) {
		articleMapper.updateArticle(articleDTO);
	}

	@Override
	@Transactional
	public boolean deleteArticle(ArticleDTO articleDTO) {
		int articleId = articleDTO.getArticleId();
		int boardId = articleDTO.getBoardId();
		int groupNo = articleDTO.getGroupNo();
	
		articleMapper.beforeRemoveUpdateOrderNo(boardId, groupNo, articleId);
		return articleMapper.removeArticle(articleDTO);
	}

	@Override
	@Transactional
	public void reply(ArticleDTO articleDTO) {
		int levelNo = articleDTO.getLevelNo();
		int articleId = articleDTO.getArticleId();
		int boardId = articleDTO.getBoardId();
		int groupNo = articleDTO.getGroupNo();
		if(levelNo == 0) {
			articleMapper.reply(articleDTO);
		}else if(levelNo >= 1) {
			articleMapper.updateOrderNo(boardId, groupNo, articleId);
			articleMapper.replyComent(articleDTO);
		}
		
	}
	
	@Override
	public List<ArticleDTO> findByBoardId(int boardId) {
		return articleMapper.findByBoardId(boardId);
	}
	
	@Override
	public int getArticleCount(PageParams pageParams) {
		return articleMapper.getCountAll(pageParams);
	}

	@Override
	public List<ArticleDTO> getList(PageParams pageParams) {
		return articleMapper.findByAll(pageParams);
	}

	
}
