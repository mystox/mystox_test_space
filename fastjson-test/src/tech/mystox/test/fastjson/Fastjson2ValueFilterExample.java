package tech.mystox.test.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.ValueFilter;
import lombok.Data;

/**
 * Created by mystox on 2024/11/4, 17:32.
 * company:
 * description:
 * update record:
 */
public class Fastjson2ValueFilterExample {
    public static void main(String[] args) {
        User user = new User("Alice", null, null);

        // 定义一个 ValueFilter，将 null 值修改为默认值
        ValueFilter filter = (object, name, value) -> {
            if (value == null) {
                return "N/A"; // 如果字段值为 null，改为 "N/A"
            }
            return value;
        };

        // 使用 ValueFilter 序列化
        String jsonString = JSON.toJSONString(user, filter, JSONWriter.Feature.WriteNulls);
        System.out.println(jsonString);
        // 输出：{"name":"Alice","age":"N/A","active":"N/A"}
    }

}
@Data
class User {
    public String name;
    private Integer age;
    private Boolean active;

    public User(String name, Integer age, Boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }

}
