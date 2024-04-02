package com.sparta.wuzuzu.domain.post.entity;

import com.sparta.wuzuzu.domain.category.entity.Category;
import com.sparta.wuzuzu.domain.common.entity.Timestamped;
import com.sparta.wuzuzu.domain.post.dto.PostRequest;
import com.sparta.wuzuzu.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long views = 0L;

    @Column(columnDefinition = "TINYINT(1) default 0")
    private Boolean status = true;

    @Column(nullable = false)
    private String goods;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    public Post(User user, PostRequest requestDto, Category category) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.goods = requestDto.getGoods();
        this.price = requestDto.getPrice();
        this.stock = requestDto.getStock();
        this.category = category;
    }

    public void update(PostRequest requestDto, Category category) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.goods = requestDto.getGoods();
        this.price = requestDto.getPrice();
        this.stock = requestDto.getStock();
        this.category = category;
    }

    public void increaseViews(){
        views++;
    }

    public void goodsOrder(Long count){
        stock -= count;
    }

    public void delete() {
        status = false;
    }
}