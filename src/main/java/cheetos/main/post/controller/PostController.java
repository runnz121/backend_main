package cheetos.main.post.controller;

import cheetos.main.post.dto.WritePostDto.WritePost;
import cheetos.main.post.service.PostService;
import cheetos.main.post.service.PostWriteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cheetos.main.common.ResponseForm;
import cheetos.main.post.dto.GetPostDto;
import cheetos.main.post.service.PostWriteServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"게시물 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostWriteServiceImpl postWriteServiceImpl;

    private final PostWriteService postWriteService;

    private static final Long TEST_USER_ID = 123L;

    /**
     * 토큰값으로 유저정보 파악 후 반환
     * @return
     */
    @GetMapping("/main")
    @ApiOperation(value = "유저별 메인화면 지도 정보", response = GetPostDto.MainPostDataRes.class, responseContainer = "List")
    public ResponseForm getMainPage() {
        return new ResponseForm(postWriteServiceImpl.getMainPostInfo(TEST_USER_ID));
    }

    /**
     * 사진, 글 업로드 생성
     */
    @PostMapping("/write")
    @ApiOperation(value = "포스트 작성 컨트롤러")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseForm createPost(@ModelAttribute WritePost writePost) {
        postWriteService.writePost(writePost);
        return new ResponseForm();
    }




}
