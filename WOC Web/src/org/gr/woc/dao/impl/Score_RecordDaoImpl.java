package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.gr.woc.dao.IScore_RecordDao;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Score_Record;

public class Score_RecordDaoImpl implements IScore_RecordDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	
	public Score_RecordDaoImpl(Connection conn) {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils = new DBUtils();
		this.conn = conn;
	}

	@Override
	public int insert(Score_Record record) {
		// TODO Auto-generated method stub
		int postid=record.getPostId();
		int userid=record.getUserId();
		String strSQL = "select * from score_record where postid=? and userid=?";
		ResultSet resultSet=  this.dbUtils.execQuery(this.conn, strSQL, new Object[]{postid,userid});	
		try {
			if(resultSet.next())
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strSQL = "insert into score_record values(null,?,?)";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid,userid});	
	}

}
