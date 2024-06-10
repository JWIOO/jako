package me.jiwoosong.springbootdeveloper.controller

import lombok.Getter
import lombok.Setter
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDate

@Controller // 컨트롤러라는 것을 명시적으로 표현
class ExampleController {
    @GetMapping("/thymeleaf/example")
    fun thymeleafExample(model: Model): String { // 뷰로 데이터를 넘겨 주는 모델 객체
        val examPerson = Person()
        examPerson.setId(1L)
        examPerson.setName("홍길동")
        examPerson.setAge(11)
        examPerson.setHobbies(listOf<String>("운동", "독서"))

        model.addAttribute("person", examPerson) // Person 객체 저장
        model.addAttribute("today", LocalDate.now())

        return "example" // example.html라는 뷰 조회
    }

    @Getter
    @Setter
    internal inner class Person {
        private val id: Long? = null
        private val name: String? = null
        private val age = 0
        private val hobbies: List<String>? = null
    }
}