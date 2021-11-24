package com.bit.springBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bit.springBoard.dto.BoardDto;

public class BoardDao {
	
	DataSource ds;
	
	public BoardDao() {
		try {
			//server.xml에 선언한 DataSource를 JNDI 기법으로 리턴
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//write : insert into tblBoard(id, name, title, content, hit)values
	//(tblBoardSeq.nextval, ?, ?, ?, 0)
	public void write(BoardDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = ds.getConnection();
			sql = "insert into tblBoard(id, name, title, content, hit)values";
			sql+="(tblBoardSeq.nextval, ?, ?, ?, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.executeUpdate();//실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//list : select * from tblBoard order by id desc
	public ArrayList<BoardDto> list(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		try {
			con = ds.getConnection();
			sql = "select * from tblBoard order by id desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				dtos.add(new BoardDto(id, name, title, content, regdate, hit));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	//contentView : select * from tblBoard where id = ?
	public BoardDto contentView(int id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		BoardDto dto = null; 
		try {
			con = ds.getConnection();
			sql = "select * from tblBoard where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				dto = new BoardDto(id, name, title, content, regdate, hit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	//modify : update tblBoard set name = ?, title = ?, content = ? where id = ?
	public void modify(BoardDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = ds.getConnection();
			sql = "update tblBoard set name = ?, title = ?, content = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//delete : delete from tblBoard where id = ? 
	public void delete(int id){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = ds.getConnection();
			sql = "delete from tblBoard where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//upHit : update tblBoard set hit = hit + 1 where id = ?
	public void upHit(int id){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = ds.getConnection();
			sql = "update tblBoard set hit = hit + 1 where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}







