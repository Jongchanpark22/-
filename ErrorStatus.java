package umcstudy.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umcstudy.apiPayload.code.BaseErrorCode;
import umcstudy.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "해당 사용자가 존재하지 않습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    LOCATION_NOT_FOUND(HttpStatus.BAD_REQUEST,"LOCATION4001","해당 지역이 존재하지 않습니다."),
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST,"STORE4001","해당 가게가 존재하지 않습니다."),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST,"MISSION4001","해당 미션가 존재하지 않습니다."),
    STOREMISSION_NOT_FOUND(HttpStatus.BAD_REQUEST,"USERMISSION4001","해당가게에 미션이 존재하지 않습니다."),
    NOTVISITEDMISSION_NOT_FOUND(HttpStatus.BAD_REQUEST,"USERMISSION4002","미션이 완료되었거나 진행중입니다"),
    PAGE_NOT_FOUND(HttpStatus.BAD_REQUEST,"PAGE4001","페이지는 '1' 이상 입력해야합니다.");




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }

}