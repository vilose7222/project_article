package com.ezen.springmvc.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ArticleDTO {

	int articleId;
	int boardId;
	String writer;
	String subject;
	String content;
	String regdate;
	int hitcount;
	String passwd;
	int groupNo;
	int levelNo;
	int orderNo;
	
}
