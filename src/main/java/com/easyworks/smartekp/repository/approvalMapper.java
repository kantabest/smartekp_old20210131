package com.easyworks.smartekp.repository;

import java.util.List;

import com.easyworks.smartekp.model.approvalModel;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface approvalMapper {
    approvalModel selectApprovalById(Long id);
    List<approvalModel> selectAllApproval();
    void insertCity(approvalModel city);
    // final
}
