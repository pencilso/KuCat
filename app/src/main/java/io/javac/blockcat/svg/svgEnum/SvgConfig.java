package io.javac.blockcat.svg.svgEnum;

/**
 * Created by Pencilso on 2018/6/20/020.
 *
 * @author Pencilso
 */
public enum SvgConfig {
    /**
     * path 默认配置
     */
    GLYPHS_DEFAULT(),
    /**
     * path 从长到短 先后绘制
     */
    GLYPHS_GROW_TO_SHORT(),
    /**
     * path 从短到长 先后绘制
     */
    GLYPHS_SHORT_TO_GROW(),
    /**
     * path 随机绘制
     */
    GLYPHS_RANDOM;
}
