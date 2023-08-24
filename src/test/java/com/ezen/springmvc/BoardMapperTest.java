package com.ezen.springmvc;


import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
public class BoardMapperTest {
   @Autowired
   private  BoardMapper boardMapper;
   
   @Test
//   @Disabled
      public void findAllTest() {
        log.info("==================== 전체 게시판 조회 ========================");
         List<BoardDTO> list = boardMapper.findAll(10);
         for (BoardDTO boardDTO : list) {
            log.info(boardDTO.toString());
         }
      }
   

   
   @Test
   @Disabled
   @DisplayName("게시판 생성")
   @Transactional
   public void createTest(){
      BoardDTO board = BoardDTO.builder()
    		  				   .category(1)
    		  				   .title("팔월십팔일게시판")
    		  				   .description("팔월십팔일생성게시판 내용")
    		  				   .build();
      boardMapper.create(board);
      log.info("게시판생성완료 {}", board);
      
   }
}






