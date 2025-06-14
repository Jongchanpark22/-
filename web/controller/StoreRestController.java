package umcstudy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umcstudy.apiPayload.ApiResponse;
import umcstudy.converter.MemberConverter;
import umcstudy.converter.StoreConverter;
import umcstudy.service.StoreService.StoreCommandService;
import umcstudy.study.StoreService.StoreQueryService;
import umcstudy.study.domain.Missions;
import umcstudy.study.domain.Store;
import umcstudy.study.domain.mapping.Reviews;
import umcstudy.validation.annotation.ExistPage;
import umcstudy.validation.annotation.ExistStore;
import umcstudy.web.dto.StoreRequestDTO;
import umcstudy.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.JoinResultDTO> register(@RequestBody @Valid StoreRequestDTO.JoinDto request) {
        StoreResponseDTO.JoinResultDTO StoresponseDto = storeCommandService.registerStore(request);

        return ApiResponse.onSuccess(StoresponseDto);
    }

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId, @ExistPage @RequestParam(name = "page") Integer page){
        Page<Missions> missionList = storeQueryService.getMissionList(storeId,page-1);
        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionList));
    }
}