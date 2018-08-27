package com.lzl.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @JsonIgnore 忽略属性的格式化
 * 序列化时转换成对应的key  age->new_age
 * @JsonProperty(value = "new_age")
 * create by lzl ON 2018/08/01
 */
public class Obj implements Serializable{


    private Long id;

    /**
     *
     */
    private Boolean flag;

    /**
     *
     */
    private String name;

    /**
     * 忽略time属性的序列化和反序列化
     */
    private LocalDateTime time;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private Double score;

    /**
     *
     */
    private Long salary;

    private String myAddress;

    /**
     * 删除的标志： 0： false,1:true
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


    public Obj() {
    }

    public Obj(Boolean flag, Integer age, String myAddress) {
        this.flag = flag;
        this.age = age;
        this.myAddress = myAddress;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
