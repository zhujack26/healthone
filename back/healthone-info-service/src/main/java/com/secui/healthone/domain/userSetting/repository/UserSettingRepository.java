package com.secui.healthone.domain.userSetting.repository;

import com.secui.healthone.domain.userSetting.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSetting, Integer> {
}
