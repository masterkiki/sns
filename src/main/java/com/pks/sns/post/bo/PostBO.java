package com.pks.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pks.sns.common.FileManagerService;
import com.pks.sns.post.comment.bo.CommentBO;
import com.pks.sns.post.comment.model.CommentDetail;
import com.pks.sns.post.dao.PostDAO;
import com.pks.sns.post.like.bo.LikeBO;
import com.pks.sns.post.model.Post;
import com.pks.sns.post.model.PostDetail;
import com.pks.sns.user.bo.UserBO;
import com.pks.sns.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	// 오토와이어디 어노테이션은 각각 해줘야함 1개로 안됨
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	
	
	public int addPost(int userId, String content, MultipartFile file) {
		// 파일을 저장하고, 접근 경로를 만든다.
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId,content, imagePath);
	}
	
	public List<PostDetail> getPostList(int userId) { //메소드는 << 이렇게 받아와도된다 로그인한 userId
		
		List<Post> postList = postDAO.selectPost();
		
		// 생성된 PostDetail 객체를 리스트로 구성한다.
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			// postDetail 객체를 생성하고, post 객체의 정보를 저장한다.
			PostDetail postDetail = new PostDetail();
			User user = userBO.getUserById(post.getUserId());
			
			// 좋아요 개수 조회
			int likeCount = likeBO.likeCount(post.getId());
			
			// 좋아요 여부 조회
			boolean isLike = likeBO.isLike(post.getId(), userId);
			
			List<CommentDetail> commentList = commentBO.getCommentList(post.getId());
			
			
			postDetail.setId(post.getId());
			postDetail.setUserId(post.getUserId());
			postDetail.setContent(post.getContent());
			postDetail.setImagePath(post.getImagePath());
			postDetail.setLike(isLike);
			postDetail.setLikeCount(likeCount);
			// UserName 값을 저장한다.
			postDetail.setUserName(user.getName());
			postDetail.setCommentList(commentList);
		
			postDetailList.add(postDetail);
		}
		
		return postDetailList;
	
	}
	
	public int deletePost(int postId, int userId) {
		
		Post post = postDAO.selectPostByPostId(postId);
		
		// post 삭제
		// 유저아이디 포스트 아이디가 일치하는지 확인 먼저 하고, 아래 삭제 진행
		int count = postDAO.deletePost(postId, userId);
		
		if(count == 1) {
			FileManagerService.removeFile(post.getImagePath());
			
			// post와 관계된 댓글 삭제
			commentBO.deleteCommentByPostId(postId);
			
			// 좋아요 삭제
			likeBO.deleteLikeByPostId(postId);
			
		}
		
		
		return count;

	}
	
	

}
