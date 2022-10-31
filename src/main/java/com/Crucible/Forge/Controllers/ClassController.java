package com.Crucible.Forge.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassController {
    ObjectMapper mapper = new ObjectMapper();


}
