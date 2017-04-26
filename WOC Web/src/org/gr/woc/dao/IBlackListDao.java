package org.gr.woc.dao;

import java.sql.ResultSet;
import java.util.List;

import org.gr.woc.po.BlackList;

public interface IBlackListDao {
 public abstract int update(final BlackList blackList);
 public abstract int delete(final BlackList blackList);
 public abstract int insert(final BlackList blackList);
 public abstract List<BlackList> findAll();
}
