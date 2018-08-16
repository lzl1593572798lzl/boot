package schedule.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import schedule.domain.Obj;
import schedule.service.ObjService;

/**
 * create by lzl ON 2018/08/12
 */
@Service
public class ObjServiceImpl implements ObjService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjServiceImpl.class);

    @Override
    public Obj getById(Long id) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public String setString(String string) {
        return string;
    }

    @Override
    public String getString(String string) {
        return string;
    }
}
