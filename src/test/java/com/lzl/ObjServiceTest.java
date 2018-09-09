package com.lzl;

import com.lzl.domain.Obj;
import com.lzl.mapper.ObjMapper;
import com.lzl.util.JsonUtils;
import com.lzl.util.timeutils.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: lzl
 * @Date: 2018/09/09 9:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjServiceTest {

    @Autowired
    private ObjMapper objMapper;

    @Test
    public void testSelectByName(){
        List<Obj> objList =  objMapper.selectByName("",23);
        printList(objList);
    }

    @Test
    public void updateObj(){
        Obj obj = objMapper.getById(1L);
        System.out.println(JsonUtils.toJson(obj));
        obj.setName(obj.getName()+
                LocalDateTime.now().toInstant(TimeUtils.ZONE_OFFSET).toEpochMilli());
        objMapper.updateByObj(obj);
        Obj objMew = objMapper.getById(1L);
        System.out.println(JsonUtils.toJson(objMew));
    }

    @Test
    public void  selectByListInteger(){
        List<Integer> list = Arrays.asList(1,3,4,5,6,7);
        List<Obj> objList = objMapper.selectByList(list);
        printList(objList);
    }

    @Test
    public void selectByListObj(){
        List<Integer> list = Arrays.asList(1,3,4,5,6,7);
        List<Obj> objListOld = objMapper.selectByList(list);
        Map<Long,Obj> objMap = new HashMap<>();
        for(Obj obj : objListOld){
            objMap.put(obj.getId(),obj);
        }
        List<Obj> objList = objMapper.selectByMap(objMap);
        printList(objList);
    }

    @Test
    public void insertByList(){
        List<Obj> objList = new ArrayList<>();
        objMapper.insertByList(assembleObjList(objList));
    }

    @Test
    public void updateByList(){
        List<Obj> objList = objMapper.listByLikeName("obj:");
        objMapper.updateByListObj(objList);
    }

    @Test
    public void testBind(){
        List<Obj> objList = objMapper.selectUseBind("obj:");
        printList(objList);
    }


    private void printList(List<Obj> list){
        for (Obj obj : list){
            System.out.println(obj.getId()+" : "+obj.getName() +" : "+obj.getAge());
        }
    }

    private List<Obj> assembleObjList(List<Obj> objList){
        for( int i = 0;i < 5;i++){
            Obj obj = new Obj();
            obj.setName("obj:"+i);
            obj.setDeleted(false);
            obj.setMyAddress(obj.getName()+" myAddress");
            obj.setSalary(23L);
            obj.setScore(34d);
            obj.setFlag(true);
            obj.setAge(23+i);
            obj.setCreatedAt(LocalDateTime.now());
            obj.setUpdatedAt(obj.getCreatedAt());
            obj.setTime(obj.getCreatedAt());
            objList.add(obj);
        }
        return objList;
    }

}
