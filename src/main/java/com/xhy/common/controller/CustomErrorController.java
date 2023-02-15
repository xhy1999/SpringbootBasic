package com.xhy.common.controller;

import com.xhy.common.util.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "异常处理接口")
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public Result getError() {
        return Result.fail(404, "no route found.");
    }

}
