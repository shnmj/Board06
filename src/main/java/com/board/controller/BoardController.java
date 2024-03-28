package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardVo;
import com.board.mapper.BoardMapper;
import com.board.menus.domain.MenuVo2;
import com.board.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private MenuMapper menuMapper;
	 
	@Autowired
	private BoardMapper boardMapper;
	
	
	// /Board/List?menu_id=MENU01
	@RequestMapping("/List")
	public ModelAndView list(MenuVo2 menuVo) {
	// public ModelAndView list(@Param String menu_id) {  -- Legacy ver.
		log.info("============ menuVo : { }", menuVo);
		
		// 메뉴 목록
		List<MenuVo2> menuList   = menuMapper.getMenuList();
		
		
		// 게시물 목록
		List<BoardVo> boardList = boardMapper.getBoardList(menuVo);
		System.out.println(boardList);
		
		String    menu_id = menuVo.getMenu_id();
		
		ModelAndView mv         = new ModelAndView();
		mv.addObject("menu_id",   menu_id);
		mv.addObject("menuList",  menuList);
		mv.addObject("boardList", boardList);
		mv.setViewName("board/list");
		return mv;
	}
	
	
	// /Board/WriteForm?menu_id=MENU01
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(MenuVo2 menuVo) {
		
		// 메뉴 목록 조회
		List<MenuVo2> mList = menuMapper.getMenuList();
		System.out.println("<MenuList> : " + mList);
		
		// ?menu_id=MENU01 넘어온 menu_id 처리
		
		String       menu_id  = menuVo.getMenu_id(); 
		
		ModelAndView mv       = new ModelAndView();
		mv.addObject("menuList", mList);
		mv.addObject("menu_id", menu_id);
		mv.setViewName("board/write");
		
		return mv;
	}
	
	
	// /Board/Write
	// menu_id=MENU01, title=sss, writer=sss, content=sss
	@RequestMapping("/Write")
	public ModelAndView write(BoardVo boardVo) {
		
		// 넘어온 값 저장 Board 저장 db
		boardMapper.insertBoard(boardVo);
		
		
		String menu_id  = boardVo.getMenu_id(); 
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
		
		
	}
	
	//  /Board/View?bno=1
	@RequestMapping("/View")
	public ModelAndView view(BoardVo boardVo) {
		
		// 메뉴목록 조회
		List<MenuVo2>  menuList = menuMapper.getMenuList();
		
		// 조회수 증가 (현재 bno의 hit = hit+1)
		boardMapper.incHit(boardVo);
		
		// bno로 조회한 게시글 정보
		BoardVo       vo       = boardMapper.getBoard(boardVo);
		
		// vo.content 안의 \n을 '<br>' 로 변경
		String  content = vo.getContent();
		if(content != null) {
			content         = content.replace("\n", "<br>");
			vo.setContent(content);
		}
		
		ModelAndView  mv       =  new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("vo", vo  );
		mv.setViewName("board/view");
		return mv;
		
	}
	
	
	
}
