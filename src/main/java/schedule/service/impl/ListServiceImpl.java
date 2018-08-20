package schedule.service.impl;

import org.springframework.stereotype.Service;
import schedule.service.ListService;

import java.util.ArrayList;
import java.util.List;

/**
 * create by lzl ON 2018/08/20
 */
@Service
public class ListServiceImpl implements ListService{

    @Override
    public List<String> list(Integer type) {
        List<String> list = new ArrayList<>();
        for (int i = 0;i < 10; i++){
            list.add(String.valueOf(i));
        }
        return list;
    }

    @Override
    public void saveList(List<String> stringList) {

    }
}
