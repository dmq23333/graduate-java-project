package com.store.mapper;

import com.store.pojo.TbUserShipping;
import com.store.pojo.TbUserShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserShippingMapper {
    int countByExample(TbUserShippingExample example);

    int deleteByExample(TbUserShippingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbUserShipping record);

    int insertSelective(TbUserShipping record);

    List<TbUserShipping> selectByExample(TbUserShippingExample example);

    TbUserShipping selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbUserShipping record, @Param("example") TbUserShippingExample example);

    int updateByExample(@Param("record") TbUserShipping record, @Param("example") TbUserShippingExample example);

    int updateByPrimaryKeySelective(TbUserShipping record);

    int updateByPrimaryKey(TbUserShipping record);
}