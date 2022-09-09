package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.users.dto.RecentComment;

import java.util.List;

public interface QFacilityCommentRepository {

    List<RecentComment> findRecentCommentByLimit(long limit);
}
