package capstone.everyhealth.controller.dto.Challenge;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeRoutineCopyToParticipantRequest {
    @ApiModelProperty(
            example = "["
                    + "{"
                    + "\"challengeRoutineId\": 1,"
                    + "\"challengeRoutineProgressDate\": \"2022-12-01\""
                    + "},"
                    + "{"
                    + "\"challengeRoutineId\": 2,"
                    + "\"challengeRoutineProgressDate\": \"2022-12-02\""
                    + "},"
                    + "{"
                    + "\"challengeRoutineId\": 3,"
                    + "\"challengeRoutineProgressDate\": \"2022-12-03\""
                    + "},"
                    + "{"
                    + "\"challengeRoutineId\": 4,"
                    + "\"challengeRoutineProgressDate\": \"2022-12-04\""
                    + "}"
                    + "]"
    )
    private List<ChallengeRoutineCopyToParticipantData> challengeRoutineCopyToParticipantDataList = new ArrayList<>();
}
