package com.jinseong.soft.springboot.domain.posts;

import com.jinseong.soft.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity     //테이블과 링크될 클래스임을 나타냄 카멜케이스 -> 언더스코어 네이밍
public class Posts extends BaseTimeEntity {
    @Id     //PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //스프링 부트 2.0 에서는 IDENTITY 옵션이 있어야지 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false)                 //굳이 선언 안해도 됨. 기본값을 바꾸고 싶을때 선언.
    private String title;

    @Column(columnDefinition ="TEXT", nullable = false)
    private String content;

    private String author;

    @Builder                                                //Builder 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
