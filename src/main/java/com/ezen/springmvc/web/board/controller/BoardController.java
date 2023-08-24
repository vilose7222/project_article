package com.ezen.springmvc.web.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.service.ArticleService;
import com.ezen.springmvc.domain.board.service.BoardService;
import com.ezen.springmvc.domain.common.web.PageParams;
import com.ezen.springmvc.domain.common.web.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private static final int elementSize = 10;
	private static final int pageSize = 5;

	private final BoardService boardService;

	private final ArticleService articleService;

	@GetMapping("/list")
	public String list(@RequestParam(value="boardId", required = false, defaultValue = "10") int boardId,
			           @RequestParam(value= "page", required = false, defaultValue = "1") int page, Model model,
			           @RequestParam(value="type", required = false) String type, @RequestParam(value="input",required = false) String input) {
		int rowCount = 0;
		PageParams pageParams = PageParams.builder()
										  .boardId(boardId)
										  .input(input)
										  .type(type)
										  .build();
		rowCount = articleService.getArticleCount(pageParams);
		pageParams.setBoardId(boardId);
		pageParams.setElementSize(elementSize);
		pageParams.setPageSize(pageSize);
		pageParams.setRowCount(rowCount);
		pageParams.setRequestPage(page);
		List<BoardDTO> board = boardService.load(boardId);
		Pagination pagination = new Pagination(pageParams);
		List<ArticleDTO> articles = articleService.getList(pageParams);
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageParams", pageParams);
	    model.addAttribute("type", pageParams.getType());
	    model.addAttribute("input", pageParams.getInput());

		return "board/list";
	}
	
	@GetMapping("/create")
	public String create(Model model, ArticleDTO articleDTO) {
		model.addAttribute("articleDTO", articleDTO);
		return "board/register";
	}
	
	@PostMapping("/create")
	public String regist(@RequestParam(value = "page", required = false, defaultValue = "1") String page,
						 @RequestParam("boardId") int boardId,
			             @ModelAttribute("articleDTO") ArticleDTO articleDTO, 
			             RedirectAttributes redirectAttributes,
			             Model model) {
		articleDTO.setBoardId(boardId);
		articleService.regist(articleDTO);
		model.addAttribute("articleDTO",articleDTO);
		redirectAttributes.addFlashAttribute("boardId",  articleDTO.getBoardId());
		return "redirect:/board/list";
	}

	@GetMapping("/read/{articleId}")
	public String read(Model model, @PathVariable("articleId") int articleId ,ArticleDTO articleDTO) {
		articleDTO = articleService.showArticle(articleId);
		model.addAttribute("article",articleDTO);
		return"board/read";
	}
	
	@GetMapping("/reply/{articleId}")
	public String reply(@PathVariable("articleId")int articleId,Model model) {
		ArticleDTO articleDTO = articleService.showArticle(articleId);
		model.addAttribute("articleDTO",articleDTO);
		int boardId = articleDTO.getBoardId();
		int groupNo = articleDTO.getGroupNo();
		model.addAttribute("boardId", boardId);
		model.addAttribute("groupNo",groupNo);
		return "board/reply";
	}
	
	
	@PostMapping("/reply")
	public String replyComent(@ModelAttribute ArticleDTO articleDTO , Model model, RedirectAttributes redirectAttributes) {
		articleService.reply(articleDTO);
		model.addAttribute(articleDTO);
		return"redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String remove(@RequestParam(value="articleId" , required = false) int articleId, Model model) {
		ArticleDTO articleDTO = articleService.showArticle(articleId);
		articleService.deleteArticle(articleDTO);
		model.addAttribute("articleDTO",articleDTO);
		return "redirect:/board/list";
	}
	
	@GetMapping("/update/{articleId}")
	public String update(@PathVariable("articleId")int articleId, Model model) {
		ArticleDTO articleDTO = articleService.showArticle(articleId);
		model.addAttribute("article",articleDTO);
		return "board/updateread";
	}
	
	@PostMapping("/update")
	public String updateArticle(@ModelAttribute ArticleDTO articleDTO ,@RequestParam(value="articleId" , required = false) int articleId,  Model model) {
//		articleDTO = articleService.showArticle(articleId);
		articleService.updateArticle(articleDTO);
		model.addAttribute("article",articleDTO);
		return "redirect:/board/list";
	}

	
}
