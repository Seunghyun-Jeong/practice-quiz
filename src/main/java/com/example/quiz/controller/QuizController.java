package com.example.quiz.controller;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** Quiz 컨트롤러 */
@Controller
@RequestMapping("/quiz")
public class QuizController {
    /** DI 대상 */
    @Autowired
    QuizService service;

    /** form-backing bean의 초기화 */
    @ModelAttribute
    public QuizForm setUpForm() {
        QuizForm form = new QuizForm();
        // 라디오 버튼의 초깃값 설정
        form.setAnswer(true);
        return form;
    }

    /** Quiz 목록 표시 */
    @GetMapping
    public String showList(QuizForm quizForm, Model model) {
        // 신규 등록 설정
        quizForm.setNewQuiz(true);

        // 퀴즈 목록 취득
        Iterable<Quiz> list = service.selectAll();

        // 표시용 모델에 저장
        model.addAttribute("list", list);
        model.addAttribute("title", "등록 폼");

        return "crud";
    }

    /** Quiz 데이터를 1건 등록 */
    @PostMapping("/insert")
    public String insert(@Validated QuizForm quizForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // Formdptj Entity로 넣기
        Quiz quiz = new Quiz();
        quiz.setQuestion(quizForm.getQuestion());
        quiz.setAnswer(quizForm.getAnswer());
        quiz.setAuthor(quizForm.getAuthor());

        // 입력 체크
        if (!bindingResult.hasErrors()) {
            service.insertQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "등록이 완료되었습니다.");
            return "redirect:/quiz";
        } else {
            // 에러가 발생한 경우에는 목록 표시로 변경
            return showList(quizForm, model);
        }
    }
}
