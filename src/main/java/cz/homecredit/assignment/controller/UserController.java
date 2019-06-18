package cz.homecredit.assignment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import cz.homecredit.assignment.config.SwaggerConfig;
import cz.homecredit.assignment.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cz.homecredit.assignment.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
@Api(value = "/user", tags = {SwaggerConfig.TAG_USERS})
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Returns user's personal details and list of to-do's")
    public User getUserDetails(
            @ApiParam("Identification of the user") @PathVariable Long userId) {
        return userService.getUsersTodos(userId);
    }
    
}
