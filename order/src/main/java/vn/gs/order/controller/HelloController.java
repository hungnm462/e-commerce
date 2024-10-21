package vn.gs.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.gs.order.config.env.GreenSnakeProperties;
import vn.gs.order.dto.request.ExampleRequest;
import vn.gs.order.dto.response.ExampleResponse;
import vn.gs.order.entity.ExampleEntity;
import vn.gs.order.dto.event.MailDto;
import vn.gs.order.event.producer.MailProducerService;
import vn.gs.order.repository.ExampleRepository;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloController {

  private final GreenSnakeProperties greenSnakeProperties;
  private final ExampleRepository exampleRepository;
  private final MailProducerService mailProducerService;

  @GetMapping("say")
  public String say() {
    return "Hello from order service";
  }

  @GetMapping("url-white")
  public List<String> getUrlWhiteList() {
    return greenSnakeProperties.getUrl().getWhiteList();
  }

  @GetMapping("/open-feign")
  public ExampleResponse search(ExampleRequest request) {
    ExampleResponse response = new ExampleResponse();
    response.setId(request.getId());
    response.setCode(request.getCode());
    response.setName(request.getName());
    response.setDate(request.getDate());
    response.setRefId(request.getRefId());
    return response;
  }

  @GetMapping("{id}")
  public ExampleEntity getDataById(@PathVariable Long id) {
    return exampleRepository.getData(id);
  }

  @PostMapping("{title}/{message}")
  public String test(@PathVariable String title, @PathVariable String message) {
    mailProducerService.send(MailDto.builder().title(title).content(message).build());
    return "SUCCESS";
  }
}
