package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxCommentTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxCommentTagMapper {

    List<BxCommentTag> getCommentTagList(@Param("ids") String ids) throws SelectException;

}
