package com.pks.sns.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pks.sns.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLike(int userId,int postId) {
		return likeDAO.getLike(userId, postId);	
	}
	
	
	//post id 를 전달받고, 좋아요 개수를 리턴하는 메소드
	public int likeCount(int postId) {
		return likeDAO.selectLikeCount(postId);
	}
	
	
	// postId 와 userId를 전달받고 좋아여 여부 리턴하는 메소드
	public boolean isLike(int postId, int userId) {
		int count = likeDAO.selectLikeCountByUserId(postId, userId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	// postId 와 userId를 전달받고 ㅅ해당 좋아요 행을 삭제한다.
	
	public int deleteLike(int postId, int userId) {
		return likeDAO.deleteLike(postId, userId);
	}
	
	public int deleteLikeByPostId(int postId) {
		return likeDAO.deleteLikeByPostId(postId);
	}

}
