package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaCarouselFigure;

public interface TeaCarouselFigureMapper {
    int deleteByPrimaryKey(String figureId);

    int insert(TeaCarouselFigure record);

    int insertSelective(TeaCarouselFigure record);

    TeaCarouselFigure selectByPrimaryKey(String figureId);

    int updateByPrimaryKeySelective(TeaCarouselFigure record);

    int updateByPrimaryKey(TeaCarouselFigure record);
}