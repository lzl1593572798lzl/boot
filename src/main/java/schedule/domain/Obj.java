package schedule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * create by lzl ON 2018/08/01
 */
public class Obj {

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
    @JsonIgnore
    private LocalDateTime time;

    /**
     * 序列化时转换成对应的key  age->new_age
     */
    @JsonProperty(value = "new_age")
    private Integer age;

    /**
     *
     */
    private Double score;

    /**
     *
     */
    private Long salary;

    /**
     *
     */
    private Date date;

    public Obj() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
