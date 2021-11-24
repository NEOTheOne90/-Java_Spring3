package com.bit.springBoard.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDao;
import com.bit.springBoard.dto.BoardDto;

public class BoardDeleteCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		int id = (Integer)map.get("id");
		BoardDao dao = new BoardDao();
		dao.delete(id);
	}
}








