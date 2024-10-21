package vn.gs.notify.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.gs.notify.dto.controller.request.ExampleRequest;
import vn.gs.notify.dto.client.ExampleDto;

import java.time.Instant;
import java.util.UUID;

@FeignClient(name = "example-feign-client", url = "http://localhost:8802")
public interface ExampleFeignClient {
    @GetMapping("/hello/open-feign")
    ExampleDto search(ExampleRequest request);

    @GetMapping("/hello/open-feign")
    ExampleDto search(
            @RequestParam("id") Integer id,
            @RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("date") Instant date,
            @RequestParam("refId") UUID refId
    );
}
