package com.heyhong.HeyHong.facility.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFacilityCommentReq {

    private Long facilityPk;
    private Long replyCommentPk; // 대댓글이 아닌 경우 null
    private String contents;
}
