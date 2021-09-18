package uz.pdp.task_2_1_2.payload;

import lombok.Data;

@Data
public class WorkDto {
    private String name;
    private String text;
    private String question;
    private String solution;
    private boolean star;
    private Integer categoryId;
}
