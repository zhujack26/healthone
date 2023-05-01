package com.secui.healthone.domain.stress.service;

import com.secui.healthone.domain.stress.dto.UploadUserStressInfoRequestDto;
import com.secui.healthone.domain.stress.entity.Stress;

import java.util.List;

public interface StressService {

    List<Stress> getStressList(String date, int page);

    void uploadUserStressInfo(UploadUserStressInfoRequestDto stressInfoDto);

}
