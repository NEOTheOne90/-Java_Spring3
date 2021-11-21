package com.bit.springBoard.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private int id; 
	private String name;
	private String title;
	private String content;
    private Timestamp regdate;
    private int hit;
	
}





