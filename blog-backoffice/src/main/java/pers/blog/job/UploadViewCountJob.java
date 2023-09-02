package pers.blog.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.blog.domain.entity.Article;
import pers.blog.service.ArticleService;
import pers.blog.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/9/1
 * @description: 将Redis信息更新到MySQl中
 */
@Component
public class UploadViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;  // 实现了IService接口, IService接口中有很多批量操作的方法

    @Scheduled(cron = "0 0/20 * * * ?")
    public void updateViewCount() {
        // 获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        // 数据处理

        List<Article> articles = viewCountMap.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());// entrySet中每个entry对象都包含了key和value

        // 更新到mysql中
        articleService.updateBatchById(articles);

    }

}
