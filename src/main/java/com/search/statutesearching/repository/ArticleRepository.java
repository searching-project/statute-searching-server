package com.search.statutesearching.repository;

import com.search.statutesearching.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    //like or 사용
    @Query(value = "select * FROM ARTICLE as a WHERE a.content like %:keyword% OR a.title like %:keyword%",nativeQuery = true )
    List<Article> search(@Param("keyword")String keyword);
    //like union 사용
    @Query(value = "select * FROM ARTICLE as a WHERE a.title like %:keyword% " +
            "union select * FROM ARTICLE as a WHERE a.content like %:keyword% ",nativeQuery = true )
    List<Article> search2(@Param("keyword")String keyword);

    //N-gram parser
    @Query(value = "select * from (select * FROM ARTICLE as a WHERE MATCH(a.title) AGAINST(:keyword IN BOOLEAN MODE)" +
            "union all " +
            "select * FROM ARTICLE as a WHERE MATCH(a.content) AGAINST(:keyword IN BOOLEAN MODE)) as a2 order by case when a2.title = :original then 1 when a2.content = :original then 2 else 3 end",nativeQuery = true )
    List<Article> search3(@Param("keyword")String keyword,@Param("original")String original);


}
