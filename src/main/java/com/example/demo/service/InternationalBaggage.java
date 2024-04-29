package com.example.demo.service;

import com.example.demo.dao.Info;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class InternationalBaggage {
    Double[] weight_list;
    //计算国际行李托运额
    public double calculateInternationalBaggageAllowance(Info info){
        int[] discount = calculate_discount(info);
        double baggage_price = 0;
        //记录待处理包裹的顺序，按照重量递减的顺序
        List pending_collection = new ArrayList();
        Integer[] baagage1_type = info.getBaggage1Type();
        Integer[] baagage2_type = info.getBaggage2Type();
        Integer[] baagage3_type = info.getBaggage3Type();
        weight_list = new Double[]{info.getWeight1(), info.getWeight2(), info.getWeight3()};
        if(baagage1_type.length > 0) {
            baggage_price += getPendingCollection(baagage1_type, info.getWeight1(), 0, pending_collection);
        }
        if(baagage2_type.length > 0) {
            baggage_price += getPendingCollection(baagage2_type, info.getWeight2(), 1, pending_collection);
        }
        if(baagage3_type.length > 0) {
            baggage_price += getPendingCollection(baagage3_type, info.getWeight3(), 2, pending_collection);
        }
        baggage_price += calculate_normal_baggage(discount, pending_collection, info);
        return baggage_price;
    }
    //计算普通行李的花费
    private double calculate_normal_baggage(int[] discount, List<Integer> pending_collection, Info info) {
        double normal_baggage_price = 0;
        for(int i = 0; i < pending_collection.size(); i++){
            int index = pending_collection.get(i);
            double weight = weight_list[index];
            if(weight <= discount[i])continue;
            else{
                //超重
                //分区域计算
                if(info.getArea() == 1){
                    //判断是否属于额外行李收费
                    if(discount[i] == 0){
                        //超出的第一件行李
                        if(i == 1){
                            normal_baggage_price += 1400;
                        }else if(i == 2){
                            //超出的第二件行李
                            normal_baggage_price += 2000;
                        }
                        continue;
                    }
                    //超重
                    if(weight > 23 && weight <= 28){
                        normal_baggage_price += 380;
                    }else{
                        normal_baggage_price += 980;
                    }
                } else if (info.getArea() == 2) {
                    //判断是否属于额外行李收费
                    if(discount[i] == 0){
                        //超出的第一件行李
                        //超出的第二件行李
                        normal_baggage_price += 1100;
                        continue;
                    }
                    //超重
                    if(weight > 23 && weight <= 28){
                        normal_baggage_price += 280;
                    }else{
                        normal_baggage_price += 680;
                    }
                }else if (info.getArea() == 3) {
                    //判断是否属于额外行李收费
                    if(discount[i] == 0){
                        //超出的第一件行李
                        //超出的第一件行李
                        normal_baggage_price += 1170;
                        continue;
                    }
                    //超重
                    if(weight > 23 && weight <= 32){
                        normal_baggage_price += 520;
                    }
                }else if (info.getArea() == 4) {
                    //判断是否属于额外行李收费
                    if(discount[i] == 0){
                        //超出的第一件行李
                        //超出的第一件行李
                        normal_baggage_price += 1380;
                        continue;
                    }
                    //超重
                    if(weight > 23 && weight <= 28){
                        normal_baggage_price += 690;
                    }else{
                        normal_baggage_price += 1040;
                    }
                }else{
                    //判断是否属于额外行李收费
                    if(discount[i] == 0){
                        //超出的第一件行李
                        if(i == 1){
                            normal_baggage_price += 800;
                        }else if(i == 2){
                            //超出的第二件行李
                            normal_baggage_price += 1100;
                        }
                        continue;
                    }
                    //超重
                    if(weight > 23 && weight <= 28){
                        normal_baggage_price += 210;
                    }else{
                        normal_baggage_price += 520;
                    }
                }
            }
        }
        return normal_baggage_price;
    }
    //获取待处理的list
    public double getPendingCollection(Integer[] baagage_type, Double weight, int index, List pending_collection){
        if(baagage_type[0] == 1) {
            //免费运输的特殊行李
            if(baagage_type[1] == 1)return 0;
            if (baagage_type[1] == 2 && baagage_type[2] == 1) {//运动器具第一类
                //加入候选集
                addPendingCollection(index,pending_collection);
            } else if(baagage_type[1] == 3 && baagage_type[2] == 1){//其他类中第一类
                //加入候选集
                addPendingCollection(index,pending_collection);
            }else{
                //计算特殊行李的费用
                return calculate_special_baggage(baagage_type, weight);
            }
        }else {
            //加入候选集
            addPendingCollection(index,pending_collection);
        }
        return 0;
    }
    //计算特殊行李的收费
    private double calculate_special_baggage(Integer[] baagage_type, Double weight) {
        double price = 0;
        //2. 运动器械类
        if(baagage_type[1] == 2){//2.2运动器械第二类
            if(baagage_type[2] == 2){
                if(weight >= 2 && weight <=23){
                    price = 2600;
                }else if(weight >= 23 && weight <= 32){
                    price = 3900;
                }else{
                    price = 5200;
                }
            }else{//运动器械第三类
                if(weight >= 2 && weight <=23){
                    price = 1300;
                }else if(weight >= 23 && weight <= 32){
                    price = 2600;
                }else {
                    price = 3900;
                }
            }
        }else{
            //3.其他类
            if(baagage_type[2] == 2){
                if(weight >= 2 && weight <=23){
                    price = 490;
                }else if(weight >= 23 && weight <= 32){
                    price = 3900;
                }
            }else if(baagage_type[2] == 3){
                if(weight >= 2 && weight <=23){
                    price = 1300;
                }else if(weight >= 23 && weight <= 32){
                    price = 2600;
                }
            }else if(baagage_type[2] == 4){
                if(weight >= 2 && weight <=5){
                    price = 1300;
                }
            }else if(baagage_type[2] == 5){
                if(weight >= 2 && weight <=23){
                    price = 3900;
                }else{
                    price = 5200;
                }
            }
        }
        return price;
    }

    //获取待处理的包裹列表
    public void addPendingCollection(int index, List<Integer> pending_collection) {
        if (pending_collection.isEmpty()) {
            pending_collection.add(index);
        } else {
            int insertIndex = -1; // 初始化插入位置为-1，表示要插入到末尾
            for (int i = 0; i < pending_collection.size(); i++) {
                if (weight_list[index] > weight_list[pending_collection.get(i)]) {
                    insertIndex = i; // 更新插入位置
                    break; // 找到插入位置后立即终止循环
                }
            }
            if (insertIndex == -1) {
                pending_collection.add(index); // 如果未找到插入位置，则将索引添加到末尾
            } else {
                pending_collection.add(insertIndex, index); // 在指定位置插入索引
            }
        }
    }

    public int[] calculate_discount(Info info){
        //定义一个数组，用于记录当前用户的免费额度
        int[] discount = new  int[3];
        int membershipType = info.getMembershipType();
        //成人/儿童票
        if(info.getTravellerType() == 1){
            if(info.getCockpitType() == 1 || info.getCockpitType() == 2){
                if(membershipType == 1 || membershipType == 2){
                    Arrays.fill(discount, 32);
                }else if (membershipType == 3){
                    discount[0] = 32;
                    discount[1] = 32;
                    discount[2] = 23;
                }else {
                    discount[0] = 32;
                    discount[1] = 32;
                    discount[2] = 0;
                }
            }else if(info.getCockpitType() == 3){
                if(membershipType == 4){
                    discount[0] = 23;
                    discount[1] = 23;
                }else{
                    Arrays.fill(discount, 23);
                }
            }else{
                //经济舱
                //区域1
                if(info.getArea() == 1 || info.getArea() == 2 || info.getArea() == 3){
                    //普通用户
                    if(membershipType == 4){
                        discount[0] = 23;
                     }else{
                        discount[0] = 23;
                        discount[1] = 23;
                    }
                }else{
                    //区域2
                    if(membershipType == 4){
                        discount[0] = 23;
                        discount[1] = 23;
                    }else {
                        Arrays.fill(discount, 23);
                    }
                }
            }
        }else {//婴儿票
            if(info.getCockpitType() == 1 || info.getCockpitType() == 2){
                if(membershipType == 1 || membershipType == 2){
                    discount[0] = 32;
                    discount[1] = 23;
                }else if(membershipType == 3){
                    discount[0] = 23;
                    discount[1] = 23;
                }else{
                    discount[0] = 23;
                }
            }else {
                if(membershipType == 4){
                    discount[0] = 23;
                }else {
                    discount[0] = 23;
                    discount[1] = 23;
                }
            }
        }
        return  discount;
    }
}
