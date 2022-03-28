package com.bigdata.wooahgong.user;

import com.bigdata.wooahgong.user.dtos.request.FindPwSendEmailReq;
import com.bigdata.wooahgong.user.dtos.request.ResetPwdReq;
import com.bigdata.wooahgong.user.dtos.request.SignUpReq;
import com.bigdata.wooahgong.user.dtos.response.GetMyFeedsRes;
import com.bigdata.wooahgong.user.dtos.response.GetMyInfoRes;
import com.bigdata.wooahgong.user.dtos.response.GetUserInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpReq signUpReq) {
        userService.signUp(signUpReq);
        return new ResponseEntity<>("회원 가입 완료.", HttpStatus.OK);
    }

    @GetMapping(value = "/signup", params = "userId")
    public ResponseEntity<String> dupCheckUserId(@RequestParam("userId") String userId) {
        userService.dupCheckUserId(userId);
        return new ResponseEntity<>("사용할 수 있는 아이디입니다.", HttpStatus.OK);
    }

    @GetMapping(value = "/signup", params = "nickname")
    public ResponseEntity<String> dupCheckNickname(@RequestParam("nickname") String nickname) {
        userService.dupCheckNickname(nickname);
        return new ResponseEntity<>("사용할 수 있는 닉네임입니다.", HttpStatus.OK);
    }

    // 아이디 찾기
    @GetMapping("/id")
    public ResponseEntity<String> findId(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.findId(email), HttpStatus.OK);
    }

    // 비밀번호 찾기1 인증메일 발송
    @PatchMapping("/pwd")
    public ResponseEntity<String> findPwSendEmail(@RequestBody FindPwSendEmailReq findPwSendEmailReq) {
        userService.findPwSendEmail(findPwSendEmailReq);
        return new ResponseEntity<>("메일을 발송했습니다.", HttpStatus.OK);
    }

    // 비밀번호 찾기2 인증코드 확인
    @GetMapping("/pwd")
    public ResponseEntity<String> findPwInsertCode(@RequestParam("userId") String userId, String authCode) {
        return userService.findPwInsertCode(userId, authCode);
    }

    // 비밀번호 찾기3 비밀번호 재설정
    @PatchMapping("/repwd")
    public ResponseEntity<String> resetPwd(@RequestBody ResetPwdReq resetPwdReq) {
        userService.resetPwd(resetPwdReq);
        return new ResponseEntity<>("비밀번호가 변경되었습니다.", HttpStatus.OK);
    }

    // 사용자 프로필 정보 조회
    @GetMapping("/{nickname}")
    public ResponseEntity<GetUserInfoRes> getUserInfo(@RequestHeader("Authorization") String token, @PathVariable String nickname) {
        return new ResponseEntity<GetUserInfoRes>(userService.getUserInfo(token, nickname), HttpStatus.OK);
    }

    // 프로필 수정 페이지로 이동
    @GetMapping("/{nickname}/update")
    public ResponseEntity<GetMyInfoRes> getMyInfo(@RequestHeader("Authorization") String token, @PathVariable String nickname) {
        return new ResponseEntity<GetMyInfoRes>(userService.getMyInfo(token, nickname), HttpStatus.OK);
    }

    // 내가 올린 피드 조회 - 무한 스크롤
    @GetMapping("/{nickname}/feeds")
    public ResponseEntity<List<GetMyFeedsRes>> getMyFeeds(@RequestHeader("Authorization") String token, @PathVariable String nickname, @PageableDefault(size = 9) Pageable pageable) {
        return new ResponseEntity<>(userService.getMyFeeds(token, nickname, pageable), HttpStatus.OK);
    }
}
