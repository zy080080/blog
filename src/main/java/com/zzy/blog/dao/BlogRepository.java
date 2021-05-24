package com.zzy.blog.dao;

import com.zzy.blog.po.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from t_blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update t_blog b set b.views = b.views+1 where b.id = ?1")
    void updateViews(Long id);

//    @Query(value = "SELECT date_format(b.update_time, '%Y') AS year FROM t_blog b GROUP BY year ORDER BY year DESC", nativeQuery = true)
    @Query("select function('date_format', b.createTime, '%Y') from t_blog b group by function('date_format', b.createTime, '%Y') order by function('date_format', b.createTime, '%Y') desc")
    List<String> findGroupYear();

//    @Query("select b from t_blog b where function('date_format',b.createTime,'%Y') =?1")
    @Query(value = "SELECT * FROM t_blog b WHERE date_format(b.create_time, '%Y') = ?1", nativeQuery = true)
    List<Blog> findByYear(String year);
}
