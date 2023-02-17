package com.pks.sns.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pks.sns.post.bo.PostBO;
import com.pks.sns.post.model.PostDetail;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline/view")
	public String timeline(Model model) {
		
//		List<Post> postList = postBO.getPostList(); 이건 에러나고 있다 이유는  BO가 변경되었기 떄문 변경된 BO 형태인 PostDetail 형태로 
		
		List<PostDetail> postList = postBO.getPostList();
		
		model.addAttribute("postList", postList);
		
		return "/post/timeline";
	}
	
	
}
