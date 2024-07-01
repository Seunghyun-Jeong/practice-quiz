package com.example.quiz;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizApplication {
	/** 구동 메서드 */
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args).getBean(QuizApplication.class).execute();
	}

	/** 주입(인젝션) */
	@Autowired
	QuizRepository repository;

	/** 실행 메서드 */
	private	void execute() {
		// 등록 처리
		setup();
	}

	/** === 퀴즈 2건을 등록합니다 === */
	private void setup() {
		// 엔티티 생성
		Quiz quiz1 = new Quiz(null, "Spring은 프레임워크입니까?", true, "홍길동");
		// 등록 실행
		quiz1 = repository.save(quiz1);
		// 등록 확인
		System.out.println("등록한 퀴즈는 " + quiz1 + "입니다.");

		// 엔티티 생성
		Quiz quiz2 = new Quiz(null, "스프링 MVC는 배치 처리를 제공합니까?", false, "홍길동");
		// 등록 싱행
		quiz2 = repository.save(quiz2);
		// 등록 확인
		System.out.println("등록한 퀴즈는 " + quiz2 + "입니다.");
	}
}
