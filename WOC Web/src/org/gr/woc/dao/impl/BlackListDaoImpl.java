package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IBlackListDao;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.BlackList;
public class BlackListDaoImpl implements IBlackListDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	
	public BlackListDaoImpl(Connection conn) {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils = new DBUtils();
		this.conn = conn;
	}

	@Override
	public int update(BlackList blackList) {
		// TODO Auto-generated method stub
		int userid=blackList.getUserId();
		int managerid=blackList.getManagerId();
		int bllevel=blackList.getBLLevel();
		int blid=blackList.getBLId();
		int lastime=blackList.getLastTime();
		String strSQL = "update BlackList set userid=?,Managerid=?,Bllevel=? lasttime=? where blid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{userid,managerid,bllevel,lastime,blid});
	}

	@Override
	public int delete(BlackList blackList) {
		// TODO Auto-generated method stub
		int blid=blackList.getBLId();
		String sql="delete from blacklist where blid=?";
		return dbUtils.execOthers(conn, sql, new Object[]{blid});
		//return 0;
	}

	@Override
	public int insert(BlackList blackList) {
		// TODO Auto-generated method stub
		int userid=blackList.getUserId();
		int managerid=blackList.getManagerId();
		int bllevel=blackList.getBLLevel();
		int lastime=blackList.getLastTime();
		String strSQL = "insert into blacklist values(null,?,?,?,now(),?)";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{userid,managerid,bllevel,lastime});
	}
	
	@Override
	public List<BlackList> findAll() {
		// TODO Auto-generated method stub
		String strSQL="SELECT  blid,b.userid,b.managerid,bllevel,bltime,lasttime,u.username,m.managername FROM "
				+ " `woc`.`blacklist` b ,user u,manager m where b.userid=u.userid and m.managerid=b.managerid";
		
		List<BlackList> list=new ArrayList<BlackList>();
		ResultSet resultSet= dbUtils.execQuery(conn, strSQL, new Object[]{});
		try {
			while(resultSet.next())
			{
				BlackList blackList=new BlackList();
				blackList.setBLId(resultSet.getInt(1));
				blackList.setUserId(resultSet.getInt(2));
				blackList.setBLLevel(resultSet.getInt(4));
				blackList.setManagerId(resultSet.getInt(3));
				blackList.setBLTime(resultSet.getDate(5));
				blackList.setLastTime(resultSet.getInt(6));;
				blackList.setUserName(resultSet.getString(7));
				blackList.setManagerName(resultSet.getString(8));
				list.add(blackList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
