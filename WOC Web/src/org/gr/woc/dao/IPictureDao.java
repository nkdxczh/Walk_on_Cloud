package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Picture;


public interface IPictureDao {
		public abstract int insert(final Picture picture);
		public abstract int deleteById(final int pictureId);
		public abstract int update(final Picture picture);
		public abstract List<Picture> selectAll();
		public abstract List<Picture> selectByComId(final int comId); 
		public abstract Picture selectById(final int pictureId);
}
