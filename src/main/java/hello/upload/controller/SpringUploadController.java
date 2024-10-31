package hello.upload.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/spring")
@Slf4j
public class SpringUploadController {

  @Value("${file.dir}")
  private String fileDir;

  @RequestMapping("/upload")
  public String newFile() {
    return "upload-form";
  }

  @RequestMapping("/upload")
  public String saveFile(@RequestParam String itemName,
      @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

    log.info("request={}", request);
    log.info("itemName={}", itemName);
    log.info("multipartFile={}", file);

    if (!file.isEmpty()) {
      String fullPath = fileDir + file.getOriginalFilename();
      log.info("파일 저장 fullPath={}", file);
      file.transferTo(new File(fullPath));
    }

    return "upload-form";
  }

}
