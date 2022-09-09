package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainRes {

    private String mapImageUrl;

    private List<LikedItem> likedItemList = new ArrayList<>();

    private List<RecentComment> recentCommentList = new ArrayList<>();
}
