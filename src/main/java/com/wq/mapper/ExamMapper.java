package com.wq.mapper;

import com.wq.bean.Exam;
import com.wq.bean.ExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ExamMapper {
    int countByExample(ExamExample example);

    int deleteByExample(ExamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    int insertSelective(Exam record);

    List<Exam> selectByExampleWithRowbounds(ExamExample example, RowBounds rowBounds);

    List<Exam> selectByExample(ExamExample example);

    int updateByExampleSelective(@Param("record") Exam record, @Param("example") ExamExample example);

    int updateByExample(@Param("record") Exam record, @Param("example") ExamExample example);
}