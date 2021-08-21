package top.lyijian.community.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;

    @TableField(fill = FieldFill.UPDATE)
    private Integer view_count;
    @TableField(fill = FieldFill.UPDATE)
    private Integer comment_count;
    @TableField(fill = FieldFill.UPDATE)
    private Integer like_count;
}
