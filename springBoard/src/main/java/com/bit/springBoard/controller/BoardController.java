package com.bit.springBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springBoard.command.BoardCommand;
import com.bit.springBoard.command.BoardContentCommand;
import com.bit.springBoard.command.BoardDeleteCommand;
import com.bit.springBoard.command.BoardListCommand;
import com.bit.springBoard.command.BoardModifyCommand;
import com.bit.springBoard.command.BoardWriteCommand;
import com.bit.springBoard.dto.BoardDto;

@Controller
public class BoardController {

	BoardCommand command;
	
	@RequestMapping(value = "writeView")
	public String writeView() {return "writeView";}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@ModelAttribute("board") BoardDto dto, Model model) {
		command = new BoardWriteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping(value = "list")
	public String list(Model model) {
		command = new BoardListCommand();
		command.execute(model);
		return "list";//views/list.jsp 호출
	}
	
	@RequestMapping(value = "contentView")
	public String contentView(@RequestParam("id")int id, Model model) {
		model.addAttribute("id", id);
		command = new BoardContentCommand();
		command.execute(model);
		return "contentView";
	}
	
	//contentView.jsp 분석 : write 참고
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute("board") BoardDto dto, Model model) {
		command = new BoardModifyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	//contentView.jsp 분석 : contentView 참고
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("id")int id, Model model) {
		model.addAttribute("id", id);
		command = new BoardDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
}











