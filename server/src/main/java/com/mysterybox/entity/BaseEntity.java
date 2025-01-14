package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    @Column(name = "create_time")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private Date createTime;
    
    @Column(name = "update_time")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private Date updateTime;
} 