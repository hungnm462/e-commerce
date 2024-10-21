package vn.gs.notify.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.gs.notify.dto.controller.request.ExampleRequest;
import vn.gs.notify.dto.client.ExampleDto;
import vn.gs.notify.client.ExampleFeignClient;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloController {
    private final ExampleFeignClient exampleFeignClient;

    @GetMapping("say")
    public String say() {
        return "Hello from notifi service";
    }

    @GetMapping("/open-feign")
    public ExampleDto search(ExampleRequest request) {
        ExampleDto response = exampleFeignClient.search(
                request.getId(),
                request.getCode(),
                request.getName(),
                request.getDate(),
                request.getRefId()
        );
        return response;
    }
}
