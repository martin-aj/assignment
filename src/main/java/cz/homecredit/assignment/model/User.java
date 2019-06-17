package cz.homecredit.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
@ApiModel(description = "Provides user details")
public class User {

    @ApiModelProperty(value = "Identification of user", required = true)
    Long id;

    @ApiModelProperty(value = "Nickname of user", required = true)
    String username;

    @ApiModelProperty(value = "Full name of user", required = true)
    String name;

    @ApiModelProperty(value = "Email address of user", required = true)
    String email;

    @ApiModelProperty(value = "List of TODOs for given user", required = true)
    List<Todo> todos;
}
