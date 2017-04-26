package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.vo.CComments;

public interface ICCommentsDao {
public abstract List<CComments> selectByComId(final int comId);
}
