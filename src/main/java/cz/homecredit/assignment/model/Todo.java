package cz.homecredit.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Model of Todo ")
public class Todo {

    @ApiModelProperty(value = "Title of the TODO", required = true)
    private String title;

    @ApiModelProperty(value = "State of the TODO", required = true)
    private Boolean completed;
}
