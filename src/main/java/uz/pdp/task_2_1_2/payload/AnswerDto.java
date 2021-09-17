package uz.pdp.task_2_1_2.payload;

import lombok.Data;

@Data
public class AnswerDto {
    private String text;
    private Integer workId;
    private Integer userId;
    private boolean isCorrect=true;
}
