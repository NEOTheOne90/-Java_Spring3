package com.bit.springBoard.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDao;
import com.bit.springBoard.dto.BoardDto;

public class BoardModifyCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		BoardDto dto = (BoardDto)map.get("board");
		BoardDao dao = new BoardDao();
		dao.modify(dto);
	}
}








