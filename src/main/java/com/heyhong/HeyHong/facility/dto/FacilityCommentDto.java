package com.heyhong.HeyHong.facility.dto;


import com.heyhong.HeyHong.facility.entity.FacilityComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FacilityCommentDto {

    private Long facilityCommentPk;
    private String userNickName;
    private String registerDateTime;
    private String contents;

    private List<FacilityCommentDto> recommentList = new ArrayList<>();

    public FacilityCommentDto(String userNickName, FacilityComment fc){
        this.userNickName = userNickName;

        this.facilityCommentPk = fc.getId();

        String registerDateTime = fc.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm"));
        System.out.println("댓글 작성 시간 변환 : " + registerDateTime);

        this.registerDateTime = registerDateTime;
        this.contents = fc.getContents();
        this.recommentList = new ArrayList<>();
    }

    public FacilityCommentDto(){
        this.userNickName = null;
        this.facilityCommentPk = null;
        this.registerDateTime = null;
        this.contents = "삭제된 댓글입니다.";
    }

    public void addRecomment(FacilityCommentDto fcDto){
        recommentList.add(fcDto);
    }
}
