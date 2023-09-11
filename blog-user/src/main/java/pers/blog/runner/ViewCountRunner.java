package pers.blog.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pers.blog.domain.entity.Article;
import pers.blog.mapper.ArticleMapper;
import pers.blog.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/9/1
 * @description: 初始化时将浏览量存入Redis
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 将博客浏览量存入Redis
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // 查询博客id
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article1 -> article1.getId().toString(),
                        article -> article.getViewCount().intValue()));

        redisCache.setCacheMap("article:viewCount", viewCountMap);
    }
}
