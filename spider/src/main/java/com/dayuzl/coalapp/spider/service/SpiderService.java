package com.dayuzl.coalapp.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Service
public class SpiderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveBook(String bookName, String bookUrl) {

        String sql = "insert into spider_book(book_name, book_url) select ?, ? FROM DUAL WHERE NOT EXISTS ";
        sql += "(SELECT * FROM spider_book WHERE book_url <> ?)";
        jdbcTemplate.update(sql, bookName, bookUrl, bookUrl);
    }

    public void saveChapter(String chapterName, String chapterUrl, String bookUrl) {

        String sql = "insert into spider_chapter(chapter_name, chapter_url, book_url) select ?, ?, ? FROM DUAL WHERE NOT EXISTS ";
        sql += "(SELECT * FROM spider_chapter where chapter_url <> ?)";
        jdbcTemplate.update(sql, chapterName, chapterUrl, bookUrl, chapterUrl);
    }

    public void saveChapterText(String chapterText, String chapterUrl) {
        String emoji = "\uD83D\uDE09";
        chapterText = chapterText.replace(emoji, "");

        String sql = "insert into spider_chapter_text(chapter_text, chapter_url) select ?, ? FROM DUAL WHERE NOT EXISTS ";
        sql += "(SELECT * FROM spider_chapter_text where chapter_url <> ?)";
        jdbcTemplate.update(sql, chapterText, chapterUrl, chapterUrl);
    }
}
