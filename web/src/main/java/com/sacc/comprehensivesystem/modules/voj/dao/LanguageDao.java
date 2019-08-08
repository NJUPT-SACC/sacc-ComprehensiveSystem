package com.sacc.comprehensivesystem.modules.voj.dao;

import com.sacc.comprehensivesystem.modules.voj.entity.Language;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LanguageDao {
    /**
     * 通过编程语言的唯一标识符获取编程语言对象.
     * @param languageId - 编程语言的唯一标识符
     * @return 预期的编程语言对象或空引用
     */
    Language getLanguageUsingId(@Param("languageId") int languageId);

    /**
     * 通过编程语言的别名获取编程语言对象.
     * @param languageSlug - 编程语言的别名
     * @return 预期的编程语言对象或空引用
     */
    Language getLanguageUsingSlug(@Param("languageSlug") String languageSlug);

    /**
     * 获取支持的编程语言.
     * @return 编程语言列表(List<Language>对象)
     */
    List<Language> getAllLanguages();

    /**
     * 添加编程语言对象.
     * @param language - 待添加的编程语言对象
     */
    int createLanguage(Language language);

    /**
     * 更新编程语言对象.
     * @param language - 待更新的编程语言对象
     */
    int updateLanguage(Language language);

    /**
     * 删除编程语言对象.
     * @param languageId - 编程语言的唯一标识符
     */
    int deleteLanguage(@Param("languageId") int languageId);
}
